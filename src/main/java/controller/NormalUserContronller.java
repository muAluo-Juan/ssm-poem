package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import annotation.AdminToken;
import annotation.NormalToken;
import model.Result;
import model.WorkResult;
import po.Administrator;
import po.Attention;
import po.Collection;
import po.Draft;
import po.NormalUser;
import po.PointsGrade;
import po.Work;
import service.AdministratorService;
import service.AttentionService;
import service.CollectionService;
import service.DraftService;
import service.NormalUserService;
import service.PointsGradeService;
import service.WorkService;
import utils.JWTUtil;
import utils.SHA256Util;

@RestController
public class NormalUserContronller {
	@Autowired
	private NormalUserService normalUserService;
	@Autowired
	private DraftService draftServices;
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private WorkService workService;
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private AdministratorService adminService;
	@Autowired
	private PointsGradeService pointsGradeService;
	
	/*
	 * 根据积分返回用户的头衔
	 */
	@CrossOrigin
	@GetMapping("/user/getgrade/{points}")
	public Result getGrade(@PathVariable("points") int points) {
		try {
			List<PointsGrade> pointsGrade = pointsGradeService.getAllPointsGrades();
			int i;
			for(i = 0 ; i < pointsGrade.size()-1 ; i ++) {
				if(points >= pointsGrade.get(i).getMiniPoints() && points < pointsGrade.get(i+1).getMiniPoints())
					return new Result(20,"头衔是",pointsGrade.get(i).getGradeName(),null);
			}
			return new Result(21,"头衔是",pointsGrade.get(pointsGrade.size()-1).getGradeName(),null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(0, "出现未知错误", null, null);			
		}
	}
	
	/*
	 * 根据userName获取管理员信息
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/admin/getadminbyusername/{userName}")
	public Result getAdminByUserName(@PathVariable("userName") String userName) {
		try {
			Administrator admin = adminService.getAdministratorByUserName(userName);
			return new Result(15,"该管理员的信息",admin,null);
		}catch(Exception e) {
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	/*
	 * 根据id获取管理员信息
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/admin/getadminbyid/{id}")
	public Result getAdminById(@PathVariable("id") int id) {
		try {
			Administrator admin = adminService.getAdministratorById(id);
			return new Result(17,"该管理员的信息",admin,null);
		}catch(Exception e) {
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	
	/*
	 * 获取当前登入用户的个人信息
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getuserinfomation")
	public Result getNormalUserInformation(HttpServletRequest request) {
		try {
			//从Token中获取用户名
			String token = request.getHeader("token");
//			System.out.print("token:");
//			System.out.println(token);
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			System.out.println("222"+User.getUserName());

   
			return new Result(1, "获取个人信息", normalUserService.getNormalUserByUid(User.getUserId()), null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
		
	}

	/*
	 * 修改个人信息
	 */	
	@CrossOrigin
	@NormalToken
	@PutMapping("/user/updateuserinfomation")
	public Result updateNormalInformation(@RequestBody NormalUser user,HttpServletRequest request) {
		try {
			System.out.println(user.getUserName());
			normalUserService.modifyNormalUserInfo(user);
			System.out.println(normalUserService.getNormalUserByUserName(user.getUserName()));
			return new Result(2, "修改信息成功", normalUserService.getNormalUserByUserName(user.getUserName()), null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	/*
	 * 修改密码之判断用户输入的旧密码是否正确
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/user/isOldRight/{password}")
	public Result isOldPwdRight(HttpServletRequest request, @PathVariable("password") String password) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			String oldPwd = user.getPassword();
			//给用户输入的密码进行加密然后比对
			String userPwd = SHA256Util.getSHA256Str(user.getUserName()+password);
			if(!oldPwd.equals(userPwd))
			{
				return new Result(15,"密码错误，拒绝密码修改",false,null);
			}
			else
				return new Result(16,"密码正确，可继续修改",true,null);
		}catch(Exception e) {
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 修改个人密码
	 * 
	 */
	@CrossOrigin
	@NormalToken
	@PutMapping("/user/changepassword/{password}")
	public Result changePassword(HttpServletRequest request, @PathVariable("password") String password) {
		try {
 
			String token = request.getHeader("token");
			System.out.print("token:");
			System.out.println(token);
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if(password != ""&& User != null) {
				//密码加密
				String pwdNew = SHA256Util.getSHA256Str(User.getUserName()+password);
				User.setPassword(pwdNew);
				normalUserService.modifyNormalUserInfo(User);
				return new Result(3, "修改密码成功", normalUserService.getNormalUserByUserName(userName), null);
			}else {
				return new Result(0, "出现未知错误", null, null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 显示用户的草稿 全部的草稿信息
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/showdraftlist")
	public Result showDraftList(HttpServletRequest request) {
		try {

			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if (User != null) {
				List<Draft> draftList = draftServices.getAllDraftByUserId(User.getUserId());
				return new Result(4, "获取个人草稿列表", draftList, null);
			} else {
				return new Result(0, "出现未知错误", null, null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 用户获取草稿信息 单个草稿
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getdraft/{dId}")
	public Result getDraft(@PathVariable("dId") int dId) {
		try {
			Draft draft = draftServices.getDraftByDraftId(dId);
			return new Result(5, "获取草稿信息", draft, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 用户删除草稿 根据dId删除
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/user/deletedraft/{dId}")
	public Result deleteDraft(@PathVariable("dId") int dId) {
		try {
			draftServices.deleteDraft(dId);
			return new Result(6, "获取草稿成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}

	}

	/*
	 * 用户编辑草稿
	 * 更新草稿信息
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/user/updatedraft")
	public Result updateDraft(Draft draft) {
		try {
			draftServices.updateDraft(draft);
			return new Result(7, "编辑草稿成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 用户保存草稿
	 * 添加新的草稿
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/user/savedraft")
	public Result saveDraft(Draft draft) {
		try {
			draftServices.addDraft(draft);
			return new Result(8, "保存草稿成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	/*
	 * 通过id查询某个用户的收藏列表
	 */
	@CrossOrigin
	@GetMapping("/user/getcollectionlistbyid/{id}")
	public Result getCollectionListByUserId(@PathVariable("id") int id) {
		try {
			List<Collection> collectionList = collectionService.getCollectionsAllByUserId(id);
			return new Result(13, "获取某个人的收藏列表", collectionList, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	

	/*
	 * 查询个人收藏列表
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getcollectionlist")
	public Result getCollectionList(HttpServletRequest request) {

		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if (User != null) {
				List<Collection> collectionList = collectionService.getCollectionsAllByUserId(User.getUserId());
				return new Result(9, "获取个人收藏列表", collectionList, null);
			} else {
				return new Result(0, "用户不存在", null, null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 删除收藏
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/user/deletecollection/{cId}")
	public Result deleteCollection(@PathVariable("cId") int cId) {
		try {
			collectionService.deleteCollection(cId);
			return new Result(10, "删除收藏成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 删除个人作品
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/user/deletework/{wId}")
	public Result deleteWork(@PathVariable("wId") int wId) {
		try {
			workService.deleteWork(wId);
			return new Result(11, "删除收藏成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 获取作品列表
	 *
	 */ 
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getworklist")
	public Result getWorkList(HttpServletRequest request) {

		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if (User != null) {
				List<WorkResult> workList = workService.getWorksByUserId(User.getUserId());
				return new Result(12, "获取个人作品列表", workList, null);
			} else {
				return new Result(0, "用户不存在", null, null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	 
	/* 
	  * 查询作品
	 * 
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getwork/{wId}")
	public Result getWork(@PathVariable("wId") int wId) {
		try {
			WorkResult work = workService.getWorkByWrokId(wId);
			return new Result(13, "删除收藏成功", work, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	/*
	  *  删除关注 
	 */
	@CrossOrigin
	@NormalToken
	@DeleteMapping("/user/deleteattention/{aId}")
	public Result deleteAttention(@PathVariable("aId") int aId,HttpServletRequest request) {
		try {
			//获取token
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			attentionService.deleteAttention(user.getUserId(), aId);
			return new Result(14, "删除关注成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	
	/*
	 * 根据id获取某个用户的关注列表
	 */
	@CrossOrigin
	@GetMapping("/user/getattentionlistbyid/{id}")
	public Result getAttentionListById(@PathVariable("id") int id) {
		try {
			List<Attention> attentionList = attentionService.getAttentions(id);
			return new Result(14, "获取某个用户的个人作品列表", attentionList, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	
	/*
	 * 获取关注列表
	 *
	 */ 
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getattentionlist")
	public Result getAttentionList(HttpServletRequest request) {

		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if (User != null) {
				List<Attention> attentionList = attentionService.getAttentions(User.getUserId());
				return new Result(12, "获取个人作品列表", attentionList, null);
			} else {
				return new Result(0, "用户不存在", null, null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}
	/* 
	  * 根据id获取用户 
	 */
	@CrossOrigin
	@GetMapping("/user/getuserbyid/{userId}")
	public Result getUserByid(@PathVariable("userId") int userId) {

		try {
			
			NormalUser user = normalUserService.getNormalUserByUid(userId);
			if (user != null) {
				
				return new Result(12, "获取用户信息", user, null);
			} else {
				return new Result(0, "用户不存在", null, null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

}
