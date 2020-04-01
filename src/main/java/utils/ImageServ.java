package utils;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageServ extends HttpServlet {
	public ImageServ() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CodeImage cImage = new CodeImage();
		BufferedImage image = cImage.getImage();
		String codeString = cImage.getTextCode();
		request.getSession().setAttribute("textCode", codeString);
		System.out.println("验证码为："+request.getSession().getAttribute("textCode"));
		cImage.out(image,response.getOutputStream());
	}

	public void init() throws ServletException {
		
	}
}
