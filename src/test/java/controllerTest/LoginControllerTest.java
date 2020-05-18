package controllerTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import controller.LoginController;
import model.LoginForm;
import model.RegisterForm;
import model.Result;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
class LoginControllerTest {

	private MockHttpServletRequest request;  
    private MockHttpServletResponse response; 
    
    @Autowired  
    private LoginController loginController ;  
	
	@BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse(); 
	}
	
	@Test
	void testLogin() throws Exception{
		try {  
        	LoginForm requestLoginUser = new LoginForm();
        	requestLoginUser.setUserName("15979510874");
        	requestLoginUser.setPassword("123456");
        	requestLoginUser.setRole("普通用户");
        	Result result = loginController.login(request, requestLoginUser);
        	assertAll("login",
        			()->assertEquals(result.getCode(),6),
        			()->assertEquals(result.getMessage(),"登录成功"));
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	@Test
	void testRegister() throws Exception{
		RegisterForm requestRegisterUser = new RegisterForm();
		requestRegisterUser.setUserName("15979510874");
		requestRegisterUser.setPassword("123");
		requestRegisterUser.setConfirmPassword("123");
		requestRegisterUser.setPenName("柔");
		Result result = loginController.register(request,requestRegisterUser);
		assertAll("register",
				()->assertEquals(result.getCode(), 15),
				()->assertEquals(result.getMessage(),"用户已存在，注册失败"));
	}
} 
