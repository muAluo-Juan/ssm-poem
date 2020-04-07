package interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.mysql.jdbc.Util;

import annotation.AdminToken;
import annotation.NormalToken;
import utils.JWTUtil;


public class AuthenInterceptor implements HandlerInterceptor {
	 
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
       
        System.out.println("处理");
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        
        

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NormalToken.class)) {
        	String username;
        	try {
            username = JWTUtil.getUsername(token);
        	}
        	catch (Exception e) {
        		return false;
        	}
            
        	if(JWTUtil.validateToken(token, username)) {
            	return true;
            }
        	else {
        		return false;
        	}
            
        }
      //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AdminToken.class)) {
        	System.out.println("deal 2");
        	String username;
        	try {
            username = JWTUtil.getUsername(token);
        	}
        	catch (Exception e) {
        		return false;
        	}
            
        	if(JWTUtil.validateToken(token, username)) {
            	return true;
            }
        	else {
        		return false;
        	}
            
        }
        return true;
    }
   
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
 
    }
}

