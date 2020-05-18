package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import annotation.AdminToken;
import model.CommentResult;
import model.Result;
import model.WorkResult;
import po.Comment;
import po.Work;
import service.CommentService;
import service.WorkService;

//管理员社区管理模块controller
@RestController
public class CommunityManageController {
	@Autowired
	private WorkService workService;
	@Autowired
	private CommentService commentService;
	
	/*
	 * 查看作品列表
	 */
	@CrossOrigin
	@GetMapping("/community/getworklist")
	public Result doGetWorkList() {
		try {
			List<WorkResult> workList = workService.getAllWorks();
			return new Result(1,"作品列表",workList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 获取某个用户的作品列表
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/community/admin_getworklist/{userId}")
	public Result doGetUserWorkList(@PathVariable("userId") int userId) {
		try {
			List<Work> workList = workService.getWorksByUserId(userId);
			return new Result(2,"该用户作品列表",workList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查询某个作品
	 */
	@CrossOrigin
	@GetMapping("/community/getwork/{workId}")
	public Result doGetWork(@PathVariable("workId") int workId) {
		try {
			Work work = workService.getWorkByWrokId(workId);
			return new Result(3,"该作品信息",work,null);
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
	 * 获取作品的评论列表
	 */
	@CrossOrigin
	@GetMapping("/community/admin_getworkcomments/{workId}")
	public Result doGetWorkComments(@PathVariable("workId") int workId) {
		try {
			List<CommentResult> comments = commentService.getCommentByWorkId(workId);
			return new Result(5,"该作品的评论列表",comments,null);
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
			return new Result(6,"获取评论",comment,null);
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
			return new Result(7,"删除评论成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
}
