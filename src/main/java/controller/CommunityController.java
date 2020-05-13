package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import model.Result;
import po.Attention;
import po.Collection;
import po.Comment;
import po.Draft;
import po.Like;
import po.NormalUser;
import po.ReportInfo;
import po.Work;
import service.AttentionService;
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
	private DraftService draftService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private CommentService commentService;
	
	/*
	 * 查看作品列表（访问CommunityManageController（是游客即可查看））
	 */
	
	/*
	 * 获取某个作品的详细信息（访问CommunityManageController（是游客即可查看））
	 */
	
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
				long points = user.getRewardPoints();
				points ++;
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
	@NormalToken
	@PostMapping("/community/user_uploadpic")
	public Result uploadPic(@RequestBody MultipartFile picture, HttpServletRequest request) {
		//获取文件在服务器的存储位置
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
	public Result addDraft(@RequestBody Draft draft,HttpServletRequest request) {
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
	 * 获取用户所有点赞的列表（进入社区可以看到哪些作品是用户点赞了的）
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping(value="/community/user_getalllikes")
	public Result getAllLikes(HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			List<Like> likes = likeService.getLikesByUserId(user.getUserId());
			return new Result(12,"所有该用户点赞的信息",likes,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 判断是否点过赞
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping(value="/community/islike/{workId}")
	public Result isLike(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Like like = likeService.getLikeByUserIdAndWorkId(user.getUserId(), workId);
			if(like == null)
				return new Result(14,"没有点过它赞",false,null);
			else
				return new Result(15,"给它点过赞",true,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 点赞
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/community/user_addlike/{workId}")
	public Result addLike(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Like like = new Like();
			like.setUserId(user.getUserId());
			like.setWorkId(workId);
			//java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
			//like.setInputTime(inputTime);
			likeService.addLike(like);
			return new Result(5,"点赞成功",workService.getWorkByWrokId(workId).getLikeNum(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 取消点赞
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping(value="/community/user_deletelike/{workId}")
	public Result deleteLike(@PathVariable("workId") int workId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			likeService.deleteLike(user.getUserId(), workId);
			return new Result(6,"删除成功",workService.getWorkByWrokId(workId).getLikeNum(),null);
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
			Attention attention = attentionService.getAttentionByUserIdAndAttentedId(userId, user.getUserId());
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
	 * 关注用户
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/community/user_addattention/{beAttentedId}")
	public Result addAttention(@PathVariable("beAttentedId") int beAttentedId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			attentionService.addAttention(user.getUserId(),beAttentedId);
			return new Result(7,"关注成功",attentionService.getAttentions(user.getUserId()),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 取消关注
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping(value="/community/user_deleteattention/{beAttentedId}")
	public Result deleteAttention(@PathVariable("beAttentedId") int beAttentedId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			attentionService.deleteAttention(user.getUserId(), beAttentedId);
			return new Result(8,"已取消关注",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 举报用户
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/community/user_report/{beReportedUserId}")
	public Result report(@PathVariable("beReportedUserId") int beReportedUserId,@RequestBody ReportInfo reportInfo,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			reportInfo.setUserId(user.getUserId());
			//java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
			//reportInfo.setInputTime(inputTime);
			reportInfo.setBeReportedUserId(beReportedUserId);
			reportService.addReportInfo(reportInfo);
			return new Result(9,"已发送举报信息至管理员",reportService.getAllReportInfo(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 查看某个作品的所有评论（前往CommunityManageController,是游客即可查看）
	 */
	
	/*
	 * 发表评论
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/community/user_comment/{workId}")
	public Result comment(@PathVariable("workId") int workId,@RequestBody Comment comment,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			comment.setUserId(user.getUserId());
			comment.setWorkId(workId);
			//java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
			//comment.setInputTime(inputTime);
			commentService.addComment(comment);
			return new Result(10,"发表成功",commentService.getCommentByWorkId(workId),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"发生未知错误",null,"");
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
	
}
