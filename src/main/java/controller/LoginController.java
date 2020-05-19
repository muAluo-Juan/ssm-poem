package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

//登录模块controller
@RestController
public class LoginController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private NormalUserService normalUserService;
	/*
	 * 注册
	 */
	@CrossOrigin
	@PostMapping("/register")
	public Result register(HttpServletRequest request,@RequestBody RegisterForm requestRegisterUser) {
		//先验证用户输入的手机验证
		/*HttpSession session = request.getSession();
		String phoneCode = (String)session.getAttribute("phoneCode");*/
		/*if(!phoneCode.equals(requestRegisterUser.getVerifyCode()))
		{
			return new Result(14,"验证码错误",null,null);
		}*/
		if(normalUserService.getNormalUserByUserName(requestRegisterUser.getUserName())!=null || administratorService.getAdministratorByUserName(requestRegisterUser.getUserName()) != null)
			return new Result(15,"用户已存在，注册失败",null,null);
		//构建新对象添加用户
		NormalUser normalUser = new NormalUser();
		normalUser.setUserName(requestRegisterUser.getUserName());
		normalUser.setPenName(requestRegisterUser.getPenName());
		normalUser.setPassword(requestRegisterUser.getPassword());
		normalUser.setHeadPicPath("https://gitee.com/muAluo/rainyNightPoemsVue/raw/master/img/w3.jpg");
		normalUser.setPersonalizedSig("这个家伙很懒，没有留下签名");
		normalUser.setSex(requestRegisterUser.getSex());
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		normalUser.setBirth(date);
		normalUser.setDisableTime(0);
		normalUser.setRewardPoints(0);
		normalUserService.addNormalUser(normalUser);
		/*session.removeAttribute("phoneCode");*/
		return new Result(2,"欢迎成为夜雨时的一员！",null,null);
	}
	
	/*
	 * 产生手机验证码
	 */
	@CrossOrigin
	@PostMapping("/login/load_phonecode")
	public Result loadPhoneCode(HttpServletRequest request) {
		PhoneCode phoneCode = new PhoneCode();
		String code = phoneCode.getPhoneCode();
		request.getSession().setAttribute("phoneCode", code);
		return new Result(1,"验证码",code,null);
	}
	
	/*
	 * 产生登录验证码
	 */
	@CrossOrigin
	@GetMapping("/login/load_icode")
	public void loadICode(String code,HttpServletRequest request,HttpServletResponse response) throws IOException {
		CodeImage cImage = new CodeImage();
		BufferedImage image = cImage.getImage(code);
		String codeString = cImage.getTextCode();
		request.getSession().setAttribute("loginCode", codeString);
		System.out.println("验证码为："+request.getSession().getAttribute("loginCode"));
		// 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
		cImage.out(image,response.getOutputStream());
	}
	
	/*
	 * 登录
	 */
	@CrossOrigin
	@PostMapping(value="/login")
	public Result login(HttpServletRequest request,@RequestBody LoginForm requestLoginUser) {
		//检验登录验证码的正确性
       /* HttpSession session = request.getSession();
		String realCode = requestLoginUser.getCode();
		System.out.println(realCode);
		if(!realCode.equals(requestLoginUser.getVerifyCode()))
		{
			return new Result(5,"验证码错误",null,null);
		}*/
		//验证用户名和密码正确性
		//普通用户
		if(requestLoginUser.getRole().equals("普通用户"))
		{
			if(normalUserService.getNormalUserByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword())!=null) {
				NormalUser normalUser = normalUserService.getNormalUserByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword());
				//给用户token
				String token = JWTUtil.generateToken(normalUser.getUserName(), "normal");
				System.out.println("用户token为"+token);
				//session.removeAttribute("loginCode");
				ArrayList<Object> list=new ArrayList<>();
				normalUser.setPassword(null);
				list.add(normalUser);
				list.add(token);
				list.add(normalUser.getPenName());
				return new Result(6,"登录成功",list,null);
			}
			else {
				return new Result(7,"用户名或密码错误",requestLoginUser,null);
			}
		}
		//管理员
		else {
			if(administratorService.getAdministratorByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword())!=null) {
				Administrator admin = administratorService.getAdministratorByUserNameAndPwd(requestLoginUser.getUserName(), requestLoginUser.getPassword());
				//给管理员token
				String token = JWTUtil.generateToken(admin.getUserName(), "admin");
				System.out.println("管理员token为"+token);
				admin.setPassword(null);
				ArrayList<Object> list=new ArrayList<>();
				list.add(token);
				list.add(admin);
				//session.removeAttribute("loginCode");
				return new Result(8,"登录成功",list,null);
			}
			else {
				return new Result(7,"用户名或密码错误",requestLoginUser,null);
			} 
		}
	}
	
	/*
	 * 判断用户是否存在
	 */
	@CrossOrigin
	@GetMapping("/login/judge_user_exist/{userName}")
	public Result judgeUserExist(@PathVariable("userName") String userName,HttpServletResponse response) {
		//查询用户名是否已存在（一个手机号只能注册一个账号，要么管理员要么普通用户）
		if(normalUserService.getNormalUserByUserName(userName)==null && administratorService.getAdministratorByUserName(userName) == null)
		{
			return new Result(3,"手机号未注册",null,null);
		}
		else{
			return new Result(4,"该手机号已注册",null,null);
		}
	}
	
	/*
	 * 找回密码
	 */
	@CrossOrigin
	@PostMapping("/login/findpassword")
	public Result findPassword(HttpServletRequest request,@RequestBody FindPasswordForm findPasswordUser) {
		//判断验证码正确性
		HttpSession session = request.getSession();
		String realCode = (String)session.getAttribute("phoneCode");
		if(realCode == null)
			return new Result(9,"未获取验证码",null,"");
		if(!(findPasswordUser.getPhoneCode()).equals(realCode))
		{
			return new Result(10,"验证码错误",null,null);
		}
		else
		{
			//根据用户名获取用户对象（管理员或普通用户）
			Administrator admin = administratorService.getAdministratorByUserName(findPasswordUser.getUserName());
			NormalUser normal = normalUserService.getNormalUserByUserName(findPasswordUser.getUserName());
			if(admin != null)
			{
				admin.setPassword(findPasswordUser.getPassword());
				administratorService.modifyAdministratorInfo(admin);
				session.removeAttribute("phoneCode");
				//验证码正确，重置密码
				return new Result(11,"密码重置成功",null,null);
			}
			else {
				if(normal != null)
				{
					normal.setPassword(findPasswordUser.getPassword());
					normalUserService.modifyNormalUserInfo(normal);
					session.removeAttribute("phoneCode");
					//验证码正确，重置密码
					return new Result(11,"密码重置成功",null,null);
				}else {
					return new Result(12,"密码重置失败",null,null);
				}
			}
		}
	}
	
	/*
	 * 退出登录
	 */
	@CrossOrigin
	@PostMapping("/login/loginout")
	public Result loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		//token失效
		
		return new Result(13,"退出登录成功",null,null);
	}
}
