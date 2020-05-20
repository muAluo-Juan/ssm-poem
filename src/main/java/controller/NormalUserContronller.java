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
import org.springframework.web.bind.annotation.RestController;

import annotation.NormalToken;
import model.Result;
import model.WorkResult;
import po.Attention;
import po.Collection;
import po.Draft;
import po.NormalUser;
import po.Work;
import service.AttentionService;
import service.CollectionService;
import service.DraftService;
import service.NormalUserService;
import service.WorkService;
import utils.JWTUtil;

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
	
	@CrossOrigin
	@GetMapping("/user/getTest")
	public Result getTest() {
		System.out.println("test");
		return new Result(0, "出现未知错误", null, null);
	}

	/*
	 * 获取当前登入用户的个人信息
	 */
	@CrossOrigin
	@NormalToken
	@GetMapping("/user/getuserinfomation")
	public Result getNormalUserInformation(@PathVariable("uid") long uid,HttpServletRequest request) {
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
	 * 未测试
	 */	
	@CrossOrigin
	@NormalToken
	@PostMapping("/user/updateuserinfomation")
	public Result updateNormalInformation(NormalUser user) {
		try {
			normalUserService.modifyNormalUserInfo(user);
			return new Result(2, "修改信息成功", null, null);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(0, "出现未知错误", null, null);
		}
	}

	/*
	 * 修改个人密码
	 * 
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping("/user/changepassword/{password}")
	public Result changePassword(HttpServletRequest request, @PathVariable("password") String password) {
		try {
 
			String token = request.getHeader("token");
			System.out.print("token:");
			System.out.println(token);
			String userName = JWTUtil.getUsername(token);
			NormalUser User = normalUserService.getNormalUserByUserName(userName);
			if(password != ""&& User != null) {
				User.setPassword(password);
				normalUserService.modifyNormalUserInfo(User);
				return new Result(3, "修改密码成功", null, null);
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
	 * 未测试
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
				System.out.println("？？？");
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
			Work work = workService.getWorkByWrokId(wId);
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

}
