package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import po.NormalUser;

@Controller
@RequestMapping("/index")
public class IndexController {
	@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value="/test",method= RequestMethod.GET)
	public @ResponseBody
	NormalUser what(){
		System.out.print("好开心啊有人访问我！");
		NormalUser no = new NormalUser();
		no.setPenName("我靠");
		return no;
	}
}