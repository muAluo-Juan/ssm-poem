package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Result;

@Controller
@RequestMapping("/index")
public class IndexController {	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/login")
	public String handleRequest() {
		return "login";  //返回login.jsp
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/register")
	public String register() {
		return "register";//返回register.jsp
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/findPassword")
	public String findPassword() {
		return "findPassword";//返回findPassword.jsp
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/user/community")
	public String entercommunity() {
		return "/normalUser/community";
	}
}