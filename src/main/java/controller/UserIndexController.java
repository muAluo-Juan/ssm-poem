package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserIndexController {
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String toRegister() {
		return "register";
	}
}
