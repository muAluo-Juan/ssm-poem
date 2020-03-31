package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * ͳһ�쳣����
 */
@ControllerAdvice
public class GlobalExceptionHandleController {
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
