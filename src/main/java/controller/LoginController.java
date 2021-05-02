package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import model.FindPasswordForm;
import model.LoginForm;
import model.RegisterForm;
import model.Result;
import po.Administrator;
import po.NormalUser;
import service.AdministratorService;
import service.NormalUserService;
import utils.CodeImage;
import utils.JWTUtil;
import utils.PhoneCode;
import utils.SHA256Util;

//登录模块controller
@RestController
public class LoginController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private NormalUserService normalUserService;
	
	/*
	 * 登录
	 */
	@CrossOrigin
	@PostMapping("/login")
	public Result doLogin(HttpServletRequest request,@RequestBody LoginForm requestLoginUser) {
		System.out.println(requestLoginUser.getUserName());
		System.out.println(requestLoginUser.getPassword());
		String pwd = SHA256Util.getSHA256Str(requestLoginUser.getUserName()+requestLoginUser.getPassword());
		requestLoginUser.setPassword(pwd);
		try {
			if(normalUserService.getNormalUserByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword())!=null) {
				NormalUser normalUser = normalUserService.getNormalUserByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword());
				//给用户token
				String token = JWTUtil.generateToken(normalUser.getUserName(), "normal");
				System.out.println("用户token为"+token);
				ArrayList<Object> list=new ArrayList<>();
				normalUser.setPassword(null);
				list.add(normalUser);
				list.add(token);
				return new Result(200,"登录成功",list,null);
			}
			else {
				return new Result(201,"用户名或密码错误",requestLoginUser,null);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 注册
	 */
	@CrossOrigin
	@PostMapping("/register")
	public Result register(HttpServletRequest request,@RequestBody RegisterForm requestRegisterUser) {
		if(normalUserService.getNormalUserByUserName(requestRegisterUser.getUserName())!=null)
			return new Result(201,"该手机已注册，可以直接登录喔！",null,null);
		if(!requestRegisterUser.getPassword().equals(requestRegisterUser.getConfirmPassword()))
			return new Result(202,"确认密码不一致！",null,null);
		//构建新对象添加用户
		NormalUser normalUser = new NormalUser();
		normalUser.setUserName(requestRegisterUser.getUserName());
		normalUser.setPenName(requestRegisterUser.getPenName());
		//将用户名和密码作为加密条件
		String pwd = SHA256Util.getSHA256Str(requestRegisterUser.getUserName()+requestRegisterUser.getPassword());
		normalUser.setPassword(pwd);
		normalUser.setHeadPicPath("https://gitee.com/muAluo/rainyNightPoemsVue/raw/master/img/w3.jpg");
		normalUserService.addNormalUser(normalUser);
		return new Result(200,"注册成功！",null,null);
	}
	
	/*
	 * 判断用户是否存在
	 */
	@CrossOrigin
	@GetMapping("/judgeuser/{userName}")
	public Result judgeUserExist(@PathVariable("userName") String userName) {
		try {
			NormalUser normal = normalUserService.getNormalUserByUserName(userName);
			if(normal != null)
				return new Result(200,"用户存在",null,null);
			else
				return new Result(201,"用户不存在",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(202,"出现未知错误！",null,null);
		}
	}
	
	/*
	 * 生成验证码
	 */
	@CrossOrigin
	@GetMapping("/code")
	public Result returnCode() {
		try {
			//生成验证码返回
			String code = "";
			for(int i = 0 ; i < 4 ; i ++) {
				int temp = new Random().nextInt(10);
				code += temp + "";
			}
			return new Result(200,"返回验证码",code,null);		
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(202,"出现未知错误！",null,null);
		}
	}
	
	
	/*
	 * 找回密码
	 */
	@CrossOrigin
	@PostMapping("/findpassword")
	public Result findPassword(@RequestBody FindPasswordForm findPasswordUser) {
		//根据用户名获取用户对象
		NormalUser normal = normalUserService.getNormalUserByUserName(findPasswordUser.getUserName());
		if(normal != null)
		{
			//密码加密
			String pwd = SHA256Util.getSHA256Str(findPasswordUser.getUserName()+findPasswordUser.getPassword());
			normal.setPassword(pwd);
			normalUserService.modifyNormalUserInfo(normal);
			//验证码正确，重置密码
			return new Result(200,"密码重置成功",null,null);
		}else {
			return new Result(201,"该手机号未注册！",null,null);
		}
	}
	
	/*
	 * 退出登录
	 */
	@CrossOrigin
	@PostMapping("/login/loginout")
	public Result loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return new Result(13,"退出登录成功",null,null);
	}
}
