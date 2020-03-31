package service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import model.MyUser;

public interface HandleService {
	public String register(MyUser myUser, Model model);
	public String login(MyUser myUser, Model model, HttpSession session);
	public String selectAllUserByPage(Model model, int currentPage);
}
