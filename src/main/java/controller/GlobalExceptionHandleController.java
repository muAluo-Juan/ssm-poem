package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandleController {
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
