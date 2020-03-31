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
		//��ҵ����
		if(handleDao.register(myUser) > 0) {
			return "login";
		}else {
			return "register";
		}
	}

	@Override
	public String login(MyUser myUser, Model model, HttpSession session) {
		if(handleDao.login(myUser).size() > 0) {
			//���û���Ϣ���浽session�У�����һ���Ự����������վ�ж����ҵ�
			session.setAttribute("myuser", myUser);
			//�ض��򵽿�����ĳ��������ʵ���û���ѯ����ѯ��һҳ
			return "redirect:/handle/selectAllUserByPage?currentPage=1";
		}
		return "login";
	}

	@Override
	public String selectAllUserByPage(Model model, int currentPage){
	    List<Map<String, Object>> allUser = handleDao.selectAll();
	    //�����ٸ��û�
	  	int totalCount = allUser.size();
	  	//���㹲����ҳ
	  	int pageSize = 2;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<Map<String, Object>> userByPage = handleDao.selectAllUserByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allUser", userByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "main";
	}


	
}
