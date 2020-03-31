package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import dao.HandleDao;
import model.MyUser;
@Service
@Transactional
public class HandleServiceImpl implements  HandleService{
	@Autowired
	private HandleDao handleDao;
	
	@Override
	public String register(MyUser myUser, Model model) {
		//做业务处理
		if(handleDao.register(myUser) > 0) {
			return "login";
		}else {
			return "register";
		}
	}

	@Override
	public String login(MyUser myUser, Model model, HttpSession session) {
		if(handleDao.login(myUser).size() > 0) {
			//将用户信息保存到session中，这是一个会话，在整个网站中都能找到
			session.setAttribute("myuser", myUser);
			//重定向到控制器某个方法，实现用户查询，查询第一页
			return "redirect:/handle/selectAllUserByPage?currentPage=1";
		}
		return "login";
	}

	@Override
	public String selectAllUserByPage(Model model, int currentPage){
	    List<Map<String, Object>> allUser = handleDao.selectAll();
	    //共多少个用户
	  	int totalCount = allUser.size();
	  	//计算共多少页
	  	int pageSize = 2;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<Map<String, Object>> userByPage = handleDao.selectAllUserByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allUser", userByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "main";
	}


	
}
