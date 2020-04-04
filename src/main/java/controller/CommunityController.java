package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.WorkDao;
import po.NormalUser;
import po.Work;

//用户社区模块controller
@Controller
@RequestMapping("/user/community")
public class CommunityController {
	@Autowired
	private WorkDao workDao;
	/*
	 * 进入发布作品
	 */
	@RequestMapping(value="/addWork")
	public String addWork(Model model,HttpSession session) {
		if(!checkLogin(model,session))//是否登录，未登录进入登录页面
			return "login";
		return "normalUser/community_addWork";
	}
	
	/*
	 * 发布作品
	 */
	@RequestMapping(value="/doAddWork")
	public String doAddWork(Model model,HttpSession session,Work work) {
		if(!checkLogin(model,session))//未登录返回登录
			return "login";
		return "community";
	}
	
	/*
	 * 添加到草稿箱
	 */
	@RequestMapping(value="/addDraft")
	public String addDraft() {
		return "community";
	}
	
	/*
	 * 点赞
	 */
	@RequestMapping(value="/addLike")
	public String addLike() {
		return "community";
	}
	
	/*
	 * 取消点赞
	 */
	@RequestMapping(value="/deleteLike")
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
	
	
	/*
	 * 检验是否已登录
	 */
	public boolean checkLogin(Model model,HttpSession session) {
		NormalUser normalUser = (NormalUser) session.getAttribute("normalUser");
		if(null == normalUser)
			return false;
		else
			return true;
	}
}
