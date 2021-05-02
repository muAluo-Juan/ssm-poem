package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import annotation.NormalToken;
import model.CommentResult;
import model.Result;
import model.WorkResult;
import po.Attention;
import po.Collection;
import po.Comment;
import po.Draft;
import po.Like;
import po.NormalUser;
import po.ReportInfo;
import po.Work;
import service.AttentionService;
import service.CollectionService;
import service.CommentService;
import service.DraftService;
import service.LikeService;
import service.NormalUserService;
import service.ReportService;
import service.WorkService;
import utils.JWTUtil;

//用户社区模块controller
@RestController
public class CommunityController {
	@Autowired
	private WorkService workService;
	@Autowired
	private NormalUserService normalUserService;
	@Autowired
	private DraftService draftService;//
	@Autowired
	private LikeService likeService;
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;
	
	/*
	 * 查看作品列表（最新）
	 */
	@CrossOrigin
	@GetMapping("/works/new")
	public Result doGetNewWorkList() {
		try {
			List<WorkResult> workList = workService.getAllWorksOrderByTime();
			return new Result(200,"最新作品",workList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看作品列表（最热）
	 */
	@CrossOrigin
	@GetMapping("/works/hot")
	public Result doGetHotWorkList() {
		try {
			List<WorkResult> workList = workService.getAllWorksOrderByLike();
			return new Result(200,"最热作品",workList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查询某个作品
	 */
	@CrossOrigin
	@GetMapping("works/{workId}")
	public Result doGetWorkById(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			WorkResult work = workService.getWorkByWrokId(workId);
			String token = request.getHeader("token");
			if(token.equals("undefined")) {
				work.setCollectByLoginer(false);
				work.setLikedByLoginer(false);
			}else {
				String userName = JWTUtil.getUsername(token);
				NormalUser user = normalUserService.getNormalUserByUserName(userName);
				//判断该作品是否被登录用户收藏和点赞
				if(collectionService.getByUserIdAndBeCollectedId(user.getUserId(), workId, 1) == null)
					work.setCollectByLoginer(false);
				else
					work.setCollectByLoginer(true);
				if(likeService.getLikeByUserIdAndBeLikedId(user.getUserId(), workId, 0) == null)
					work.setLikedByLoginer(false);
				else
					work.setLikedByLoginer(true);
			
				//判断作品用户是否被用户关注
				if(work.getUserId() == user.getUserId())
					work.setAttendAuthor(2);//作者是登录用户
				else {
					if(attentionService.getAttentionByUserIdAndAttentedId(work.getUserId(), user.getUserId(), 0) == null)
						work.setAttendAuthor(0); //未关注
					else
						work.setAttendAuthor(1);  //已关注
				}
			}
			
			return new Result(200,"该作品信息",work,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 获取作品的评论列表
	 */
	@CrossOrigin
	@GetMapping("/comments/{workId}")
	public Result doGetWorkComments(@PathVariable("workId") int workId) {
		try {
			List<CommentResult> comments = commentService.getCommentByWorkId(workId);
			return new Result(500,"该作品的评论列表",comments,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(200,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 点赞/取消点赞
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/like/work/{workId}")
	public Result addLike(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Like lk = likeService.getLikeByUserIdAndBeLikedId(user.getUserId(), workId, 0);
			if(lk != null) {//取消点赞
				likeService.deleteLike(user.getUserId(), workId, 0);
			}else {//点赞
				Like like = new Like();
				like.setUserId(user.getUserId());
				like.setBeLikedId(workId);
				like.setType(0);
				Timestamp d = new Timestamp(System.currentTimeMillis());
				like.setLikeTime(d);
				likeService.addLike(like);
			}
			return new Result(200,"点赞成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 收藏/取消收藏
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/collection/work/{workId}")
	public Result addCollection(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Collection c = collectionService.getByUserIdAndBeCollectedId(user.getUserId(), workId, 1);
			if(c != null) {//取消收藏
				collectionService.deleteCollection(c.getCollectionId());
			}else {//收藏
				Collection collection = new Collection();
				collection.setBeCollectedId(workId);
				collection.setType(1);
				Timestamp d = new Timestamp(System.currentTimeMillis());
				collection.setCollectTime(d);
				collection.setUserId(user.getUserId());
				collectionService.addCollection(collection);
			}
			return new Result(200,"收藏成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 关注/取消用户
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/attention/user/{userId}")
	public Result addAttention(@PathVariable("userId") int userId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			if(attentionService.getAttentionByUserIdAndAttentedId(userId, user.getUserId(), 0) == null) {//关注
				Attention attention = new Attention();
				Timestamp s = new Timestamp(System.currentTimeMillis());
				attention.setAttentionTime(s);
				attention.setBeAttentedId(userId);
				attention.setType(0);
				attention.setUserId(user.getUserId());
				attentionService.addAttention(attention);
			}else {//取消关注
				attentionService.deleteAttention(user.getUserId(), userId, 0);
			}
			return new Result(200,"操作成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 发布作品
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/community/user_addwork")
	public Result doAddWork(@RequestBody Work work,HttpServletRequest request) {
		//获取token
		String token = request.getHeader("token");
		String userName = JWTUtil.getUsername(token);
		NormalUser user = normalUserService.getNormalUserByUserName(userName);
		if(work != null && user != null) {
			try {
				work.setUserId(user.getUserId());
				//work.setLikeNum(0);
				//java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
				//work.setInputTime(inputTime);
				workService.addWork(work);
				//增加用户积分
				int points = user.getRewardPoints();
				points += 5;
				user.setRewardPoints(points);
				normalUserService.modifyNormalUserInfo(user);
				return new Result(3,"发表成功！",null,null);
			}catch(Exception e) {
				e.printStackTrace();
				return new Result(0,"发生未知错误",null,"");
			}
		}
		return new Result(0,"发生未知错误",null,"");
	}
	
	/*
	 * 图片上传
	 */
	@CrossOrigin
	@PostMapping("/community/user_uploadpic")
	public Result uploadPic(@RequestBody MultipartFile picture, HttpServletRequest request) throws FileNotFoundException {
		//获取文件在服务器的存储位置
//		String path = ResourceUtils.getURL("classpath:").getPath();
//		File filePath = new File(path, "upload/community_pic/");
		String path = request.getServletContext().getRealPath("/upload/community_pic");
		File filePath = new File(path);
		
		System.out.println("文件的保存路径"+path);
		if(!filePath.exists() && !filePath.isDirectory())
		{
			System.out.println("目录不存在，创建目录"+filePath);
			filePath.mkdir();
		}
		
		//获取原始文件名称（有格式）
		String originalFileName = picture.getOriginalFilename();
		System.out.println("原始文件名为"+originalFileName);
		//获取文件类型
		String type = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		System.out.println("文件类型为"+type);
		//获取原始文件名称（无格式）
		String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));
		//设置文件新名称
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		String fileName = dateStr + name + "." + type;
		System.out.println("新文件名称"+fileName);
		
		//在指定路径创建该文件
		File targetFile = new File(path,fileName);
		//将文件保存到服务器指定位置
		try {
			picture.transferTo(targetFile);
			System.out.println("上传成功");
			//返回文件在服务器中的存储位置
			return new Result(1,"上传成功","/upload/community_pic"+fileName,"");
		}catch(IOException e) {
			e.printStackTrace();
			return new Result(2,"上传失败",null,"");
		}
	}
	
	
	/*
	 * 添加到草稿箱
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/community/user_adddraft")
	public Result doAddDraft(@RequestBody Draft draft,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			draft.setUserId(user.getUserId());
			draftService.addDraft(draft);
			return new Result(4,"添加成功",draftService.getAllDraftByUserId(user.getUserId()),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 删除草稿
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/community/user_deletedraft/{draftId}")
	public Result deleteDraft(@PathVariable("draftId") int draftId) {
		try {
			draftService.deleteDraft(draftId);
			return new Result(20,"删除成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	
	
	
	/*
	 * 获取关注对象的列表（一进入社区模块就知道哪些人被自己关注了）
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping(value="/community/user_getattentions")
	public Result getAttentions(HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			List<Attention> attentions = attentionService.getAttentions(user.getUserId());
			return new Result(13,"该用户关注的人有",attentions,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 查看用户是否被关注
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping(value="/community/isAttented/{userId}")
	public Result isAttented(@PathVariable("userId") int userId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			System.out.println("是"+user.getUserId());
			if(user.getUserId() == userId)
				return new Result(19,"我自己",2,null);
			Attention attention = attentionService.getAttentionByUserIdAndAttentedId(userId, user.getUserId(),0);
			if(attention == null)
				return new Result(17,"没有被关注",0,null);
			else
				return new Result(18,"被关注了",1,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 发表评论
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/comment/work")
	public Result comment(@RequestBody Comment comment,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			comment.setUserId(user.getUserId());
			Timestamp s = new Timestamp(System.currentTimeMillis());
			comment.setInputTime(s);;
			commentService.addComment(comment);
			return new Result(200,"发表成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	
	/*
	 * 删除评论（自己的）
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping(value="/community/user_deletecomment/{commentId}")
	public Result comment(@PathVariable("commentId") int commentId,HttpServletRequest request) {
		try {
			commentService.deleteComment(commentId);
			return new Result(11,"删除成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	//删除作品（自己的）
	@CrossOrigin
	@NormalToken
	@DeleteMapping(value="/community/user_deletework/{workId}")
	public Result userDeleteWork(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			workService.deleteWork(workId);
			return new Result(12,"删除成功",workService.getWorksByUserId(user.getUserId()),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
}
