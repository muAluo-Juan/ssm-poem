package interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

import annotation.AdminToken;
import annotation.NormalToken;
import utils.JWTUtil;


public class AuthenInterceptor implements HandlerInterceptor {
	 
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 浠� http 璇锋眰澶翠腑鍙栧嚭 token
        System.out.println("澶勭悊");
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        
        //妫�鏌ユ湁娌℃湁闇�瑕佺敤鎴锋潈闄愮殑娉ㄨВ
        if (method.isAnnotationPresent(NormalToken.class)) {
        	if(token==null) {
        		httpServletResponse.setStatus(401);
        		return false;
        	}
        	String username;
        	String author;
        	try {
            username = JWTUtil.getUsername(token);
            author=JWTUtil.getAuthor(token);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        		httpServletResponse.setStatus(401);
        		return false;
        	}
            
        	if(JWTUtil.validateToken(token, username)) {
            	return true;
            }
        	else {
        		httpServletResponse.setStatus(401);
        		return false;
        	}
            
        }
      //妫�鏌ユ湁娌℃湁闇�瑕佺敤鎴锋潈闄愮殑娉ㄨВ
        if (method.isAnnotationPresent(AdminToken.class)) {
        	if(token==null) {
        		httpServletResponse.setStatus(401);
        		return false;
        	}
        	String username;
        	String author;
        	try {
            username = JWTUtil.getUsername(token);
            author=JWTUtil.getAuthor(token);
            System.out.println(username+" "+author);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        		System.out.println("鏈惡甯oken杩斿洖鐧诲綍");
        		httpServletResponse.setStatus(401);
        		return false;
        	}
            
        	if(JWTUtil.validateToken(token, username)) {
        		if(author!=null&&author.equals("admin"))
        			return true;
        		else {
            		httpServletResponse.setStatus(401);

        			return false;
        		}
            }
        	else {
        		httpServletResponse.setStatus(401);
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

