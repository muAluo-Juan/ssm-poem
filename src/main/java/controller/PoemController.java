package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import annotation.NormalToken;
import model.Result;
import po.Author;
import po.Collection;
import po.ErrorInfo;
import po.NormalUser;
import po.Poem;
import po.Poem_Ext;
import po.Poem_Type;
import service.AuthorService;
import service.CollectionService;
import service.ErrorInfoService;
import service.NormalUserService;
import service.PoemService;
import service.Poem_ExtService;
import service.Poem_TypeService;
import utils.JWTUtil;

//用户诗歌鉴赏controller
@RestController
public class PoemController {
	@Autowired 
	private PoemService poemService;
	@Autowired
	private NormalUserService normalUserService;
	@Autowired
	private ErrorInfoService errorInfoService;
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private Poem_TypeService poem_typeService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private Poem_ExtService poem_extService;
	
	/*
	 * 获取诗歌类型列表（访问PoemManageController,游客即可，无权限控制）
	 */
	
	
	/*
	 * ！！！根据诗歌的uid去poem_ext表中查找诗歌的附加信息（如诗歌赏析信息、诗歌音频文件路径）
	 */
	@CrossOrigin
	@GetMapping("/poem/user_getpoem_ext/{poemUId}")
	public Result getPoem_Ext(@PathVariable("poemUId") String poemUId) {
		try {
			return new Result(10,"该诗歌对应的附加信息",poem_extService.getPoem_ExtByPoemuid(poemUId),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	
	
	/*
	 * 查看诗歌列表（前往PoemManageController的查看诗歌列表方法，该方法未设置管理员权限，只要是游客即可查看）
	 */
	
	/*
	 * 赏析某一首诗歌（前往PoemManageController的查看某一首诗歌方法，该方法未设置管理员权限，只要是游客即可查看）
	 */
	
	
	/*
	 * 语音听诗（用网上的音频文件http://XXXX，不使用自己的本地文件）
	 */
	@CrossOrigin
	@GetMapping("/poem/user_getaudio/{poemUId}")
	public Result doGetPoemAudio(@PathVariable("poemUId") String poemUId) {
		try {
			Poem_Ext poem_ext = poem_extService.getPoem_ExtByPoemuid(poemUId);
			if(poem_ext.getAudio()!=null && !(poem_ext.getAudio()).equals(""))
				return new Result(8,"该诗歌对应音频文件路径",poem_ext.getAudio(),null);
			else
				return new Result(9,"该音频暂未收录",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	
	/*
	 * 复制诗歌内容（vue前端实现）
	 */
	
	/*
	 * 用户提交勘误信息（记住必须要传入用户当前看的poem的id，否则不知道提交的勘误信息是哪一首诗歌的）
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/poem/user_adderrorinfo")  
	public Result doAddErrorInfo(@RequestBody ErrorInfo errorInfo,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			if(errorInfo != null && user != null) {
				java.sql.Date inputTime = new java.sql.Date(System.currentTimeMillis());
				//errorInfo.setInputTime(inputTime);//勘误提交时间
				errorInfo.setUserId(user.getUserId());//勘误提交人
				errorInfoService.addErrorInfo(errorInfo);
				return new Result(1,"已提交管理员审核，感谢您的意见",errorInfo,null);
			}
			else
				return new Result(0,"出现未知错误",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 用户收藏诗歌
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/poem/user_addcollection/{poemId}")
	public Result doAddCollection(@PathVariable("poemId") long poemId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Poem poem = poemService.getPoemByPoemId(poemId);
			if(poem != null && user != null)
			{
				Collection collection = new Collection();
				collection.setPoemId(poem.getId());
				collection.setUserId(user.getUserId());
				collectionService.addCollection(collection);
				return new Result(2,"收藏成功！",collection,null);
			}
			else
				return new Result(0,"出现未知错误",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	/*
	 * 用户取消收藏
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/poem/user_deletecollection/{poemId}")
	public Result doDeleteCollection(@PathVariable("poemId") long poemId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Poem poem = poemService.getPoemByPoemId(poemId);
			if(poem != null && user != null) {
				collectionService.deleteReferCollection(user.getUserId(), poemId);
				return new Result(3,"删除成功！",null,null);
			}else {
				return new Result(0,"出现未知错误",null,null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 分类搜索：按诗歌类型搜索诗歌
	 */
	@CrossOrigin
	@GetMapping("/poem/searchbytype/{id}")
	public Result doGetPoemsByType(@PathVariable("id") int typeId) {
		try {
			System.out.println("要查找的诗歌的诗歌类型是"+typeId);
			List<Poem> poems = poemService.getPoemsByTypeId(typeId);
			return new Result(4,"对应的诗歌List",poems,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 搜索框：按诗歌名模糊搜索
	 */
	@CrossOrigin
	@GetMapping("/poem/searchbyname/{name}")
	public Result doGetPoemsByName(@PathVariable("name") String name) {
		try {
			List<Poem> poems = poemService.getPoemsByName(name);
			return new Result(5,"诗歌名模糊搜索结果",poems,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 分类搜索：按诗人UID搜索
	 */
	@CrossOrigin
	@GetMapping("/poems/{authorUId}")
	public Result doGetPoemsByAuthor(@PathVariable("authorUId") String authorUId) {
		try {
			List<Poem> poems = poemService.getPoemsByAuthorUId(authorUId);
			return new Result(6,"该诗人的诗",poems,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	/*
	 * 按诗人名搜索诗人
	 */
	@CrossOrigin
	@GetMapping("/poets/{name}")
	public Result doGetPoetByName(@PathVariable String name) {
		try {
			 List<Author> poets = authorService.getAuthorsByAuthorName(name);
			 System.out.println(poets.size());
			return new Result(6,"success",poets,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	/*
	 * 通过诗人uid获取诗人
	 */
	@CrossOrigin
	@GetMapping("/getpoetbyuid/{uid}")
	public Result doGetPoetByUid(@PathVariable String uid) {
		try {
			 Author poet = authorService.getAuthorByUid(uid);
			return new Result(14,"success",poet,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 分类搜索：按朝代搜索诗人（诗人按照朝代排列好，用户点击某个诗人后可查看到该诗人的所有诗）
	 */
	@CrossOrigin
	@GetMapping("/poem/searchauthorbydynasty/{dynastyId}")
	public Result doGetAuthorsByDynasty(@PathVariable("dynastyId") int dynastyId) {
		try {
			List<Author> authors = authorService.getAuthorsByDynastyId(dynastyId);
			return new Result(7,"该朝代下的诗人",authors,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
}
