package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import annotation.NormalToken;
import model.Result;
import po.NormalUser;
import po.Work;
import service.NormalUserService;
import service.WorkService;
import utils.JWTUtil;

//用户社区模块controller
@RestController
public class CommunityController {
	@Autowired
	private WorkService workService;
	
	@Autowired
	private NormalUserService normalUserService;
	
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
				work.setLikeNum(0);
				java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
				work.setInputTime(inputTime);
				workService.addWork(work);
				//增加用户积分
				long points = user.getRewardPoints();
				points ++;
				user.setRewardPoints(points);
				normalUserService.modifyNormalUserInfo(user);
				return new Result(3,"发表成功！",null,null);
			}catch(Exception e) {
				e.printStackTrace();
				return new Result(4,"发生未知错误",null,"");
			}
		}
		return new Result(4,"发生未知错误",null,"");
	}
	
	/*
	 * 图片上传（问题：重启服务器后图片就不见了）
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/community/user_uploadpic")
	public Result uploadPic(@RequestBody MultipartFile picture, HttpServletRequest request) {
		//获取文件在服务器的存储位置
		String path = request.getServletContext().getRealPath("/upload");
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
			return new Result(1,"上传成功","/upload/"+fileName,"");
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
	public Result addDraft() {
		
		return null;
	}
	
	/*
	 * 点赞
	 */
	@RequestMapping(value="/community/user_addlike")
	public String addLike() {
		return "community";
	}
	
	/*
	 * 取消点赞
	 */
	@RequestMapping(value="/community/user_deletelike")
	public String deleteLike() {
		return "community";
	}
	
	/*
	 * 关注用户
	 */
	
	
	/*
	 * 取消关注
	 */
	
	
	/*
	 * 举报用户
	 */
	
	
	/*
	 * 发表评论
	 */
	
	
	/*
	 * 删除评论（自己的）
	 */
}
