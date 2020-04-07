package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//跨域拦截
public class Interceptor extends HandlerInterceptorAdapter{
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 	String url = request.getHeader("Origin");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json; charset=utf-8");
	        response.setHeader("Access-Control-Allow-Origin", url);
	        response.addHeader("Access-Control-Allow-Credentials", "true");//session传值
	        return true;
	}
}
