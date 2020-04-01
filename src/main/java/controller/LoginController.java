package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.LoginForm;
import model.RegisterForm;
import model.Result;
import po.Administrator;
import po.NormalUser;
import service.AdministratorService;
import service.NormalUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private NormalUserService normalUserService;
	/*
	 * 注册
	 */
	@RequestMapping("/register")
	public String register(@ModelAttribute("user1") RegisterForm user,Model model,HttpSession session) {
		//先验证用户输入的手机验证
		String phoneCode = (String)session.getAttribute("phoneCode");
		if(!phoneCode.equals(user.getVerifyCode()))
		{
			model.addAttribute("phoneCodeError","验证码错误");
			model.addAttribute("user",user);
			return "register";
		}
		//构建新对象添加用户
		NormalUser normalUser = new NormalUser();
		normalUser.setUserName(user.getUserName());
		normalUser.setPenName(user.getPenName());
		normalUser.setPassword(user.getPassword());
		if(user.getSex() == 0)
			normalUser.setHeadPicPath("girl.jpg");
		else
			normalUser.setHeadPicPath("boy.jpg");
		normalUser.setPersonalizedSig("这个家伙很懒，没有留下签名?");
		normalUser.setSex(user.getSex());
		Date date = new Date();
		normalUser.setBirth(new java.sql.Date(date.getDate()));
		normalUser.setDisableTime(0);
		normalUser.setRewardPoints(0);
		normalUserService.addNormalUser(normalUser);
		model.addAttribute("registerSuccess","欢迎成为夜雨时的一员！");
		session.removeAttribute("phoneCode");
		return "register";
	}
	
	/*
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(@ModelAttribute("user") LoginForm user,HttpSession session,Model model) {
		//验验证码的正确性
		String realCode = (String) session.getAttribute("textCode");
		if(!realCode.equals(user.getVerifyCode()))
		{
			model.addAttribute("verifyCodeError","验证码错误");
			model.addAttribute(user);
			return "login";
		}
		//验证用户名和密码正确�?
		//普�?�用�?
		if(user.getRole().equals("normalUser"))
		{
			if(normalUserService.getNormalUserByUserNameAndPwd(user.getUserName(), user.getPassword())!=null) {
				NormalUser normalUser = normalUserService.getNormalUserByUserNameAndPwd(user.getUserName(), user.getPassword());
				model.addAttribute("loginSuccessInfo","登录成功");
				model.addAttribute("normalUser",normalUser);
				//放入session
				session.setAttribute("normalUser", normalUser);
				session.removeAttribute("textCode");
				return "/normalUser/index";
			}
			else {
				model.addAttribute("loginErrorInfo","用户名或密码错误");
				model.addAttribute("user",user);
				return "login";
			}
		}
		//管理�?
		else {
			if(administratorService.getAdministratorByUserNameAndPwd(user.getUserName(), user.getPassword())!=null) {
				Administrator admin = administratorService.getAdministratorByUserNameAndPwd(user.getUserName(), user.getPassword());
				model.addAttribute("loginSuccessInfo","登录成功");
				model.addAttribute("admin",admin);
				//放入session
				session.setAttribute("admin", admin);
				session.removeAttribute("textCode");
				return "/admin/index";
			}
			else {
				model.addAttribute("loginErrorInfo","用户名或密码错误");
				model.addAttribute("user",user);
				return "login";
			}
		}
	}
	
	/*
	 * 判断用户是否存在
	 */
	@RequestMapping(value="/judgeUserExist",method=RequestMethod.POST)
	@ResponseBody
	public void judgeUserExist(@RequestParam("userName") String userName,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		//查询用户名是否已存在（一个手机号只能注册�?个账号，要么管理员要么普通用户）
		if(normalUserService.getNormalUserByUserName(userName)==null && administratorService.getAdministratorByUserName(userName) == null)
		{
			JSONObject obj = new JSONObject(new Result(2,"手机号未注册","",""));
			out.print(obj);
		}
		else{
			JSONObject obj = new JSONObject(new Result(3,"该手机号已注册","",""));
			out.print(obj);
		};
	}
	
	
	/*
	 * 找回密码
	 */
	@RequestMapping(value="/verifyPhoneCode",method=RequestMethod.POST)
	@ResponseBody
	public void findPassword(@RequestParam("phoneCode") String phoneCode,@RequestParam("userName") String userName,@RequestParam("password") String password,HttpServletResponse response,HttpSession session) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		//判断验证码正确�??
		String realCode = (String)session.getAttribute("phoneCode");
		if(!phoneCode.equals(realCode))
		{
			JSONObject obj = new JSONObject(new Result(4,"验证码错误","",""));
			out.print(obj);
		}
		else
		{
			//根据用户名获取用户对象（管理员或普通用户）
			Administrator admin = administratorService.getAdministratorByUserName(userName);
			NormalUser normal = normalUserService.getNormalUserByUserName(userName);
			if(admin != null)
			{
				admin.setPassword(password);
				administratorService.modifyAdministratorInfo(admin);
				//验证码正确，重置密码
				JSONObject obj = new JSONObject(new Result(5,"密码重置成功?","",""));
				out.print(obj);
			}
			else {
				if(normal != null)
				{
					normal.setPassword(password);
					normalUserService.modifyNormalUserInfo(normal);
					//验证码正确，重置密码
					JSONObject obj = new JSONObject(new Result(5,"密码重置成功","",""));
					out.print(obj);
				}else {
					JSONObject obj = new JSONObject(new Result(6,"密码重置失败","",""));
					out.print(obj);
				}
			}
		}
	}
	
	/*
	 * 退出登录
	 */
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "../../index";   //index.jsp
	}
}
