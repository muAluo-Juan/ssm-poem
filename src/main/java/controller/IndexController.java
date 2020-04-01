package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping("/login")
	public String handleRequest() {
		return "login";  //返回login.jsp
	}
	@RequestMapping("/register")
	public String register() {
		return "register";//返回register.jsp
	}
	@RequestMapping("/findPassword")
	public String findPassword() {
		return "findPassword";//返回findPassword.jsp
	}
	@RequestMapping("/user/community")
	public String entercommunity() {
		return "/normalUser/community";
	}
}