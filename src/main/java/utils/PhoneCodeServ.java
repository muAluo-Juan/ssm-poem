package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Result;


/**
 * 手机验证码servlet
 */

public class PhoneCodeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PhoneCodeServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PhoneCode code = new PhoneCode();
		String phoneCode = code.getPhoneCode();
		System.out.println("手机验证码是"+phoneCode);
		Result result = new Result(1,"验证码获取成功",phoneCode,"");
		request.getSession().setAttribute("phoneCode", phoneCode);//放到session里面去
		JSONObject object = new JSONObject(result);
		PrintWriter write = response.getWriter();
		write.print(object);
	}
	public void destroy() {
		super.destroy(); 
	}
}
