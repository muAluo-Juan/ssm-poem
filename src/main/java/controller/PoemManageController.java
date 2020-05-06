package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import annotation.AdminToken;
import annotation.NormalToken;
import model.PoemForm;
import model.Result;
import po.Administrator;
import po.Author;
import po.Dynasty;
import po.ErrorInfo;
import po.Poem;
import po.Poem_Ext;
import po.Poem_Type;
import po.Type;
import service.AdministratorService;
import service.AuthorService;
import service.DynastyService;
import service.ErrorInfoService;
import service.PoemService;
import service.Poem_ExtService;
import service.Poem_TypeService;
import service.TypeService;
import utils.JWTUtil;

//管理员诗歌管理模块controller
@RestController
public class PoemManageController {
	@Autowired
	private ErrorInfoService errorInfoService;
	@Autowired
	private PoemService poemService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private DynastyService dynastyService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private Poem_ExtService poem_extService;
	@Autowired
	private Poem_TypeService poem_typeService;
	//-----------------------------------勘误管理-----------------------------------
	/*
	 * 查看勘误信息列表
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poem/admin_geterrorlist")
	public Result doGetErrorInfoList() {
		try {
			List<ErrorInfo> errorInfoList = errorInfoService.getAllErrorInfos();
			return new Result(1,"勘误信息列表",errorInfoList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看某条勘误信息
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poem/admin_geterrorinfo/{errorId}")
	public Result doGetErrorInfo(@PathVariable("errorId") int errorId)
	{
		try {
			ErrorInfo errorInfo = errorInfoService.getErrorInfoById(errorId);
			return new Result(2,"勘误信息内容",errorInfo,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 审核勘误信息
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/poem/admin_verifyerrorinfo/{errorId}")
	public Result doVerifyErrorInfo(@PathVariable("errorId") int errorId,HttpServletRequest request) {
		try {
			ErrorInfo errorInfo = errorInfoService.getErrorInfoById(errorId);
			if(errorInfo != null) {
				errorInfo.setState(1);//改变为已审核状态
				
				String token = request.getHeader("token");
				String userName = JWTUtil.getUsername(token);
				Administrator admin = administratorService.getAdministratorByUserName(userName);
				int adminId = admin.getAdministratorId();
				
				errorInfo.setVerifyAdministratorId(adminId);//填入审核管理员的id
				
				errorInfoService.updateErrorInfo(errorInfo);
				return new Result(3,"审核成功",errorInfo,null);
			}
			else
				return new Result(4,"勘误信息不存在",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除勘误信息
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poem/admin_deleteerrorinfo/{errorId}")
	public Result doDeleteErrorInfo(@PathVariable("errorId") int errorId) {
		try {
			if(errorInfoService.getErrorInfoById(errorId)!=null)
			{
				errorInfoService.deleteErrorInfo(errorId);
				return new Result(5,"删除成功",errorInfoService.getAllErrorInfos(),null);
			}
			else
				return new Result(6,"勘误信息不存在,删除失败",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	
	//-----------------------------------诗词类别管理-----------------------------------
	/*
	 * 查看诗人列表
	 */
	@CrossOrigin
	@GetMapping("/poets")
	public Result doGetAuthorList() {
		try {
			List<Author> authorList = authorService.getAllAuthors();
			return new Result(7,"诗人列表",authorList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看某个诗人的具体信息
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poem/admin_getauthor/{authorId}")
	public Result doGetAuthor(@PathVariable("authorId") int authorId)
	{
		try {
			Author author = authorService.getAuthorByAuthorId(authorId);
			if(author != null)
				return new Result(8,"该诗人信息",author,null);
			else
				return new Result(9,"该诗人不存在",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 根据诗人名字查找诗人
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poem/admin_getauthorbyname/{authorName}")
	public Result doGetAuthorByName(@PathVariable("authorName") String authorName)
	{
		try {
			List<Author> authorList = authorService.getAuthorsByAuthorName(authorName);
			return new Result(10,"按诗人名字查询的诗人列表",authorList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 根据朝代查找诗人
	 */
	@CrossOrigin
	@GetMapping("/poem/admin_getauthorbydynasty/{dynastyId}")
	public Result doGetAuthorByDynasty(@PathVariable("dynastyId") int dynastyId)
	{
		try {
			List<Author> authorList = authorService.getAuthorsByDynastyId(dynastyId);
			return new Result(11,"按朝代查找的诗人列表",authorList,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 添加诗人
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poem/admin_addauthor")
	public Result doAddAuthor(@RequestBody Author author) {
		try {
			authorService.addAuthor(author);
			return new Result(14,"添加成功",authorService.getAllAuthors(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除诗人
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poem/admin_deleteauthor/{authorId}")
	public Result doDeleteAuthor(@PathVariable("authorId") int authorId)
	{
		try {
			Author author = authorService.getAuthorByAuthorId(authorId);
			if(author == null)
				return new Result(15,"该诗人不存在",null,null);
			if(poemService.getPoemsByAuthorUId(author.getUid()).size() > 0)
				return new Result(16,"删除失败，该诗人已被引用！",poemService.getPoemsByAuthorUId(author.getUid()),null);
			authorService.deleteAuthor(authorId);
			return new Result(17,"删除成功",authorService.getAllAuthors(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 更新诗人信息
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/poem/admin_updateauthor/{authorId}")
	public Result doUpdateAuthor(@PathVariable("authorId") int authorId,@RequestBody Author author) {
		try {
			authorService.updateAuthor(author);
			return new Result(18,"诗人信息更新成功！",authorService.getAuthorByAuthorId(authorId),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 添加朝代
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poem/admin_adddynasty")
	public Result doAddDynasty(@RequestBody Dynasty dynasty) {
		try {
			dynastyService.addDynasty(dynasty);
			return new Result(20,"朝代添加成功！",dynastyService.getAllDynastys(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除朝代
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poem/admin_deletedynasty/{dynastyId}")  //传入朝代编号
	public Result doDeleteDynasty(@PathVariable("dynastyId") int dynastyId)
	{
		try {
			Dynasty dynasty = dynastyService.getDynastyById(dynastyId);
			if(dynasty == null)
				return new Result(21,"该朝代不存在，删除失败",null,null);
			if((authorService.getAuthorsByDynastyId(dynastyId)).size() > 0)
				return new Result(22,"删除失败，该朝代已被引用",null,null);
			dynastyService.deleteDynasty(dynastyId);
			return new Result(23,"朝代删除成功",dynastyService.getAllDynastys(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 更新朝代信息
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/poem/admin_updatedynasty/{dynastyId}")
	public Result doUpdateDynasty(@PathVariable("dynastyId") int dynastyId,@RequestBody Dynasty dynasty) {
		try {
			dynastyService.updateDynasty(dynasty);
			return new Result(24,"朝代更新成功！",dynastyService.getDynastyById(dynastyId),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看所有朝代
	 */
	@CrossOrigin
	@GetMapping("/poem/admin_getdyanstylist")
	public Result doGetDynastyList() {
		try {
			List<Dynasty> dynasties = dynastyService.getAllDynastys();
			return new Result(25,"朝代列表",dynasties,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看某个朝代的具体信息（根据朝代id）
	 */
	@CrossOrigin
	@GetMapping("/poem/getdynasty/{dynastyId}")
	public Result doGetDynasty(@PathVariable("dynastyId") int dynastyId)
	{
		try {
			Dynasty dynasty = dynastyService.getDynastyById(dynastyId);
			return new Result(26,"该朝代信息",dynasty,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 增加诗歌类型
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poem/admin_addtype")
	public Result doAddType(@RequestBody Type type) {
		try {
			typeService.addType(type);
			return new Result(28,"诗歌类型添加成功",typeService.getAllTypes(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除诗歌类型
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poem/admin_deletetype/{typeId}")
	public Result doDeleteType(@PathVariable("typeId") int typeId)
	{
		try {
			Type type = typeService.getTypeById(typeId);
			if(type == null)
				return new Result(29,"该诗歌类型不存在",null,null);
			if((poemService.getPoemsByTypeId(typeId)).size() > 0)
				return new Result(30,"删除失败，该诗歌类型已被引用！",typeService.getAllTypes(),null);
			typeService.deleteType(typeId);
			return new Result(31,"删除成功",typeService.getAllTypes(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 更新诗歌类型
	 */
	@CrossOrigin
	@AdminToken
	@PutMapping("/poem/admin_updatetype/{typeId}")
	public Result doUpdateType(@PathVariable("typeId") int typeId,@RequestBody Type type) {
		try {
			typeService.update(type);
			return new Result(32,"类型更新成功！",typeService.getTypeById(typeId),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看所有类型
	 */
	@CrossOrigin
	@GetMapping("/poem/gettypelist")
	public Result doGetTypeList() {
		try {
			List<Type> typies = typeService.getAllTypes();
			return new Result(33,"类型列表",typies,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看某个类型的具体信息
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poem/admin_gettype/{typeId}")
	public Result doGetType(@PathVariable("typeId") int typeId)
	{
		try {
			Type type = typeService.getTypeById(typeId);
			return new Result(34,"该类型信息",type,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	//-----------------------------------诗词管理-----------------------------------
	/*
	 * 查看诗歌列表
	 */
	@CrossOrigin
	@GetMapping("/poem/getpoemlist")
	public Result doGetPoemList() {
		try {
			List<Poem> poemList = poemService.getAllPoems();
			List<PoemForm> poemForms = new ArrayList<PoemForm>();//包含了诗歌的基本信息和附加信息
			for(int i = 0 ; i < poemList.size() ; i ++)
			{
				Poem poem = poemList.get(i);
				List<Poem_Type> types = poem_typeService.getPoem_ThemeByPoemId(poem.getId());
				List<Integer> typeids = new ArrayList<Integer>();
				for(int j = 0 ; j < typeids.size() ; j ++)
				{
					int temp = (int) types.get(j).getPtId();
					typeids.add(temp);
				}
				
				Poem_Ext poem_ext = poem_extService.getPoem_ExtByPoemuid(poem.getUid());
				PoemForm poemform = null;
				if(poem_ext!=null)
					poemform = new PoemForm(poem.getName(),poem.getDynastyid(),typeids,poem.getAuthoruid(),poem.getContent(),poem.getAnnotation(),poem.getTranslation(),poem.getWorkintro(),poem.getRichtext(),
							poem_ext.getEngContent(),poem_ext.getEngIntro(),poem_ext.getAudio(),poem_ext.getVedio(),poem_ext.getYiwen_audio(),poem_ext.getPic(),poem_ext.getAppreciation(),
							poem_ext.getBackground(),poem_ext.getVersion(),poem_ext.getAnalyse(),poem_ext.getStory());
				else
					poemform = new PoemForm(poem.getName(),poem.getDynastyid(),typeids,poem.getAuthoruid(),poem.getContent(),poem.getAnnotation(),poem.getTranslation(),poem.getWorkintro(),poem.getRichtext());
				poemForms.add(poemform);
			}
			return new Result(35,"诗歌列表",poemForms,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看某一首诗歌
	 */
	@CrossOrigin
	@GetMapping("/poem/getpoem/{poemId}")
	public Result doGetPoem(@PathVariable("poemId") long poemId) {
		try {
			Poem poem = poemService.getPoemByPoemId(poemId);
			List<Poem_Type> types = poem_typeService.getPoem_ThemeByPoemId(poem.getId());
			List<Integer> typeids = new ArrayList<Integer>();
			for(int j = 0 ; j < typeids.size() ; j ++)
			{
				int temp = (int) types.get(j).getPtId();
				typeids.add(temp);
			}
			PoemForm poemForm = new PoemForm(poem.getName(),poem.getDynastyid(),typeids,poem.getAuthoruid(),poem.getContent(),poem.getAnnotation(),poem.getTranslation(),poem.getWorkintro(),poem.getRichtext()); 
			return new Result(36,"该诗歌内容",poemForm,null);
		}catch(Exception e) { 
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 添加诗歌（同时传入json和MultipartFile）
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poem/admin_addpoem")
	public Result doAddPoem(PoemForm poemForm,@RequestPart("file") MultipartFile file,HttpServletRequest request) {
		try{
			Poem poem = new Poem(poemForm.getName(),poemForm.getDynastyid(),poemForm.getAuthoruid(),poemForm.getContent(),poemForm.getAnnotation(),poemForm.getTranslation(),poemForm.getWorkintro(),poemForm.getRichtext());
			poemService.addPoem(poem);
			long poemId = poem.getId();//自动注入
			System.out.println(poemId);
			Poem newPoem = poemService.getPoemByPoemId(poemId);
			
			String nowFileName = null;
			//存储音频文件
			if(file!=null) {
				//获取文件在服务器的存储位置
				String path = request.getServletContext().getRealPath("/upload/poem_audio");
				File filePath = new File(path);
				System.out.println("文件的保存路径"+path);
				if(!filePath.exists() && !filePath.isDirectory())
				{
					System.out.println("目录不存在，创建目录"+filePath);
					filePath.mkdir();
				}
				//获取原始文件名称（有格式）
				String originalFileName = file.getOriginalFilename();
				System.out.println("原始文件名为"+originalFileName);
				//获取文件类型
				String type = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
				System.out.println("文件类型为"+type);
				//获取原始文件名称（无格式）
				String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));
				//设置文件新名称
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String dateStr = sdf.format(date);
				String fileName = dateStr + name + "." + type;
				System.out.println("新文件名称"+fileName);
				
				//在指定路径创建该文件
				File targetFile = new File(path,fileName);
				//将文件保存到服务器指定位置
				file.transferTo(targetFile);
				System.out.println("音频上传成功");
				nowFileName = "/upload/poem_audio"+fileName;
			}
			
			//增加诗歌附加信息
			Poem_Ext poem_ext= new Poem_Ext(poemForm.getEngContent(),poemForm.getEngIntro(),nowFileName,poemForm.getVedio(),poemForm.getYiwen_audio(),poemForm.getPic(),poemForm.getAppreciation(),poemForm.getBackground(),poemForm.getVersion(),poemForm.getAnalyse(),poemForm.getStory());
			poem_ext.setPoemuid(newPoem.getUid());
			poem_extService.addPoem_Ext(poem_ext);
			
			//增加诗歌与类型的对应关系
			List<Integer> poem_types = poemForm.getTypeids();  //诗歌对应的类型
			List<Poem_Type> types = new ArrayList<Poem_Type>();
			for(int i = 0 ; i < poem_types.size() ; i ++)
			{
				Poem_Type type = new Poem_Type();
				type.setPoemId(poemId);
				type.setThemeId(poem_types.get(i));
				types.add(type);
			}
			poem_typeService.addManyPoem_Type(types);//批量添加诗歌和类型的对应关系
			
			return new Result(37,"添加成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 删除诗歌
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poem/admin_deletepoem/{poemId}")
	public Result doDeletePoem(@PathVariable("poemId") long poemId) {
		try {
			Poem poem = poemService.getPoemByPoemId(poemId);
			if(poem == null)
				return new Result(38,"该诗歌不存在",null,null);
			//删除诗歌的附加信息
			if(poem_extService.getPoem_ExtByPoemuid(poem.getUid())!=null)
				poem_extService.deletePoem_Ext(poem.getUid());
			//删除诗歌_类型信息
			poem_typeService.deleteAllPoem_Type(poemId);
			//删除诗歌
			poemService.deletePoem(poemId);
			return new Result(39,"删除成功！",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 修改诗歌
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poem/admin_updatepoem/{poemId}")
	public Result doUpdatePoem(@PathVariable("poemId") long poemId,PoemForm poemForm,@RequestPart("file") MultipartFile file,HttpServletRequest request) {
		try {
			Poem poem = poemService.getPoemByPoemId(poemId);
			if(poem==null)
				return new Result(40,"该诗歌不存在",null,null);
			//更新诗歌t_poems_poem表
			poem.setAnnotation(poemForm.getAnnotation());
			poem.setAuthoruid(poemForm.getAuthoruid());
			poem.setContent(poemForm.getContent());
			poem.setDynastyid(poemForm.getDynastyid());
			poem.setName(poemForm.getName());
			poem.setRichtext(poemForm.getRichtext());
			poem.setTranslation(poemForm.getTranslation());
			poem.setWorkintro(poemForm.getWorkintro());
			poemService.updatePoem(poem);
			//更新诗歌附加表t_poems_poem_ext
			Poem_Ext poem_ext = poem_extService.getPoem_ExtByPoemuid(poem.getUid());
			poem_ext.setAnalyse(poemForm.getAnalyse());
			poem_ext.setAppreciation(poemForm.getAppreciation());
			poem_ext.setBackground(poemForm.getBackground());
			poem_ext.setEngContent(poemForm.getEngContent());
			poem_ext.setEngIntro(poemForm.getEngIntro());
			poem_ext.setPic(poemForm.getPic());
			poem_ext.setStory(poemForm.getStory());
			poem_ext.setVedio(poemForm.getVedio());
			poem_ext.setVersion(poemForm.getVersion());
			poem_ext.setYiwen_audio(poemForm.getYiwen_audio());
			String nowFileName = null;
			//删除之前的旧音频
			if(poem_ext.getAudio()!="" || poem_ext.getAudio()!=null) {
				String oldAudioPath = poem_ext.getAudio();
				String path = request.getServletContext().getRealPath(oldAudioPath);
				File filePath = new File(path);
				if(filePath.exists()&&filePath.isFile())
				{
					filePath.delete();
				}
			}
			//存储新音频文件
			if(file!=null) {
				//获取文件在服务器的存储位置
				String path = request.getServletContext().getRealPath("/upload/poem_audio");
				File filePath = new File(path);
				System.out.println("文件的保存路径"+path);
				if(!filePath.exists() && !filePath.isDirectory())
				{
					System.out.println("目录不存在，创建目录"+filePath);
					filePath.mkdir();
				}
				//获取原始文件名称（有格式）
				String originalFileName = file.getOriginalFilename();
				System.out.println("原始文件名为"+originalFileName);
				//获取文件类型
				String type = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
				System.out.println("文件类型为"+type);
				//获取原始文件名称（无格式）
				String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));
				//设置文件新名称
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String dateStr = sdf.format(date);
				String fileName = dateStr + name + "." + type;
				System.out.println("新文件名称"+fileName);
				
				//在指定路径创建该文件
				File targetFile = new File(path,fileName);
				//将文件保存到服务器指定位置
				file.transferTo(targetFile);
				System.out.println("音频上传成功");
				nowFileName = "/upload/poem_audio"+fileName;
			}
			poem_ext.setAudio(nowFileName);
			poem_extService.updatePoem_Ext(poem_ext);
			//更新诗歌_类型表（t_poem_theme）----该表来自自己的数据库
			poem_typeService.deleteAllPoem_Type(poemId);//先删除所有的该诗歌的poem_theme
			List<Integer> poem_types = poemForm.getTypeids();  //诗歌对应的类型
			List<Poem_Type> types = new ArrayList<Poem_Type>();
			for(int i = 0 ; i < poem_types.size() ; i ++)
			{
				Poem_Type type = new Poem_Type();
				type.setPoemId(poemId);
				type.setThemeId(poem_types.get(i));
				types.add(type);
			}
			poem_typeService.addManyPoem_Type(types);//批量添加诗歌和类型的对应关系
			
			return new Result(41,"修改成功",null,null);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0,"出现未知错误",null,null);
		}
	}
}
