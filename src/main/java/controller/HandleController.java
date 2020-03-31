package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.MyUser;
import service.HandleService;

@Controller
@RequestMapping("/handle")
public class HandleController {
	@Autowired
	private HandleService handleService;

	@RequestMapping("/register")
	public String register(MyUser myUser, Model model) {
		return handleService.register(myUser, model);
	}
	@RequestMapping("/login")
	public String login(MyUser myUser, Model model, HttpSession session) {
		return handleService.login(myUser, model, session);
	}
	@RequestMapping("/selectAllUserByPage")
	public String selectAllUserByPage(Model model, int currentPage) {
		return handleService.selectAllUserByPage(model, currentPage);
	}
}
