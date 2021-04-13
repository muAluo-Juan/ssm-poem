package daoTest;

import static java.time.Duration.ofMillis;
//使得BeforeAll注释的实例方法在所有实例方法前执行
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

import controller.CommunityManageController;
import controller.LoginController;
import model.LoginForm;
import model.Result;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Rollback(value = true)  
@Transactional(transactionManager="txManager")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  //使得BeforeAll注释的实例方法在所有实例方法前执行
class CommunityManageControllerTest {

	private MockHttpServletRequest request;  
    private MockHttpServletResponse response; 
    private String token = null;
    @Autowired  
    private LoginController loginController;
    @Autowired
    private CommunityManageController communityManageController;
    
    @BeforeAll
    void login() {
    	LoginForm requestLoginUser = new LoginForm();
    	requestLoginUser.setUserName("15979510879");
    	requestLoginUser.setPassword("111111");
//    	requestLoginUser.setRole("管理员");
//    	Result result = loginController.login(request, requestLoginUser);
//    	System.out.println(result.getData());
//    	token = (String) result.getData();
    }
    
    @BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        request.addHeader("token", token);
        response = new MockHttpServletResponse(); 
	}
	
    //测试获取某个用户的作品列表
	@Test
	void testDoGetUserWorkList() {
		assertTimeout(ofMillis(100), ()->{
			communityManageController.doGetUserWorkList(33);
		});
	}

}
