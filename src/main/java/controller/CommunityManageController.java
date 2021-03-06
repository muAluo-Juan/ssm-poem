package controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import annotation.AdminToken;
import model.CommentResult;
import model.Result;
import model.WorkResult;
import po.Administrator;
import po.Comment;
import po.ReportInfo;
import po.Work;
import service.AdministratorService;
import service.CommentService;
import service.ReportService;
import service.WorkService;
import utils.JWTUtil;

//管理员社区管理模块controller
@RestController
public class CommunityManageController {
	@Autowired
	private WorkService workService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private AdministratorService administratorService;
	
	/*
	 * 获取举报列表
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/community/admin_getreportlist")
	public Result doGetReportInfoList() {
		try {
			List<ReportInfo> reportList = reportService.getAllReportInfo();
			return new Result(9,"举报信息列表",reportList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 审核举报信息（置state为1）
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/community/admin_confirmreportlist/{id}")
	public Result doConfirmReportInfo(@PathVariable("id") int id,HttpServletRequest request) {
		try {
			ReportInfo reportInfo = reportService.getReportInfo(id);
			reportInfo.setState(1);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			reportInfo.setVerifyTime(time);
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			Administrator admin = administratorService.getAdministratorByUserName(userName);
			int adminId = admin.getAdministratorId();
			
			reportInfo.setVerifyAdministratorId(adminId);//填入审核管理员的id
			reportService.updateReportInfo(reportInfo);
			return new Result(10,"审核成功",reportService.getAllReportInfo(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 忽略举报信息（置state为2）
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/community/admin_ignorereportlist/{id}")
	public Result doIgnoreReportInfo(@PathVariable("id") int id,HttpServletRequest request) {
		try {
			ReportInfo reportInfo = reportService.getReportInfo(id);
			reportInfo.setState(2);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			reportInfo.setVerifyTime(time);
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			Administrator admin = administratorService.getAdministratorByUserName(userName);
			int adminId = admin.getAdministratorId();
			
			reportInfo.setVerifyAdministratorId(adminId);//填入审核管理员的id
			reportService.updateReportInfo(reportInfo);
			return new Result(12,"忽略成功",reportService.getAllReportInfo(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	//删除某条举报信息（即忽略某条举报信息）
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/community/admin_confirmreportlist/{id}")
	public Result doDeleteReportInfo(@PathVariable("id") int id) {
		try {
			reportService.deleteReportInfo(id);
			return new Result(11,"删除成功",reportService.getAllReportInfo(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	
	
	/*
	 * 获取某个用户的作品列表
	 */
	@CrossOrigin
	@GetMapping("/community/admin_getworklist/{userId}")
	public Result doGetUserWorkList(@PathVariable("userId") int userId) {
		try {
			List<WorkResult> workList = workService.getWorksByUserId(userId);
			return new Result(2,"该用户作品列表",workList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除作品
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/community/admin_deletework/{workId}")
	public Result doDeleteWork(@PathVariable("workId") int workId) {
		try {
			workService.deleteWork(workId);
			return new Result(4,"删除作品成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	/*
	 * 获取评论列表
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/community/admin_getworkcomments")
	public Result doGetWorkComments() {
		try {
			List<CommentResult> comments = commentService.getAllComment();
			return new Result(5,"评论列表",comments,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	
	
	/*
	 * 获取某条评论
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/community/admin_getworkcomment/{commentId}")
	public Result doGetworkComment(@PathVariable("commentId") int commentId) {
		try {
			Comment comment = commentService.getCommentByCommentId(commentId);
			return new Result(7,"获取评论",comment,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除某条评论
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/community/admin_deleteworkcomment/{commentId}")
	public Result doDeleteWorkComment(@PathVariable("commentId") int commentId) {
		try {
			commentService.deleteComment(commentId);
			return new Result(8,"删除评论成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
}
