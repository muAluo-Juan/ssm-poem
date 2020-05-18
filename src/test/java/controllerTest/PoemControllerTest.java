package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.LoginController;
import controller.PoemController;
import model.LoginForm;
import model.Result;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Rollback(value = true)  
@Transactional(transactionManager="txManager")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  //使得BeforeAll注释的实例方法在所有实例方法前执行
class PoemControllerTest {
	
	private MockHttpServletRequest request;  
    private MockHttpServletResponse response; 
    private String token = null;
    @Autowired  
    private LoginController loginController;
    @Autowired
    private PoemController poemController;
    
    @BeforeAll
    void login() {
    	LoginForm requestLoginUser = new LoginForm();
    	requestLoginUser.setUserName("15979510874");
    	requestLoginUser.setPassword("123456");
    	requestLoginUser.setRole("普通用户");
    	Result result = loginController.login(request, requestLoginUser);
    	List a = (List) result.getData();
    	System.out.println(a.get(1));
    	token = (String) a.get(1);
    }
    
    @BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        //request.addHeader("token", token);
        response = new MockHttpServletResponse(); 
	}
	
    //测试用户语音听诗
	@Test
	void testDoGetPoemAudio() {
		assertNotNull(poemController.doGetPoemAudio(434).getData());
	}
	
	//断言测试用户收藏诗歌
	@Test
	void testDoAddCollection() {
		request.addHeader("token", token);
		assertEquals(2,poemController.doAddCollection(434, request).getCode());
	}
}
