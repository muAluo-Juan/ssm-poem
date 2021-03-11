package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static java.time.Duration.ofMillis;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import controller.LoginController;
import controller.PoemManageController;
import po.Dynasty;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
/*@TestInstance(TestInstance.Lifecycle.PER_CLASS)  //使得BeforeAll注释的实例方法在所有实例方法前执行*/
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@Rollback(value = true)  
@Transactional(transactionManager="txManager")
class PoemManageControllerTest {

	private MockHttpServletRequest request;  
    private MockHttpServletResponse response; 
    
    /*@Autowired  
    private LoginController loginController;*/
    @Autowired
    private PoemManageController poemanageController;
 
    
    /*@BeforeAll
    void login() {
    	LoginForm requestLoginUser = new LoginForm();
    	requestLoginUser.setUserName("15979510874");
    	requestLoginUser.setPassword("123456");
    	requestLoginUser.setRole("普通用户");
    	Result result = loginController.login(request, requestLoginUser);
    	List a = (List) result.getData();
    	System.out.println(a.get(1));
    }*/
    
    @BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse(); 
	}
    
    //测试根据朝代id查找诗人
	@Test
	void testDoGetAuthorByDynasty() {
		assertTimeout(ofMillis(200), ()->{
			poemanageController.doGetAuthorByDynasty(20);
		});
	}
	
	//测试添加朝代
//	@Test
//	void testDoAddDynasty() {
//		Dynasty dynasty = new Dynasty();
//		dynasty.setName("人工智能时代");
//		dynasty.setStart("20世纪");
//		dynasty.setEnd("未知");
//		dynasty.setIntro("一个智慧的时代");
//		List a = (List) poemanageController.doGetDynastyList().getData();
//		poemanageController.doAddDynasty(dynasty);
//		List b = (List) poemanageController.doGetDynastyList().getData();
//		assertTrue(a.size()+1 == b.size(), () -> "测试插入成功");
//	}
	
	//测试删除诗歌类型
//	@Test
//	void testDoDeleteType() {
//		List a = (List) poemanageController.doGetTypeList().getData();
//		poemanageController.doDeleteType(1);
//		List b = (List) poemanageController.doGetTypeList().getData();
//		assertTrue(a.size()-1 == b.size(), () -> "诗歌类型删除成功");
//	}
}
