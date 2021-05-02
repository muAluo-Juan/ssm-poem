package controller;

import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Stmnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import annotation.AdminToken;
import annotation.NormalToken;
import model.Result;
import po.NormalUser;
import po.PointsGrade;
import service.NormalUserService;
import service.PointsGradeService;


@RestController
public class UserManageContronller {
   @Autowired
   private NormalUserService normalUserService;
   @Autowired
   private PointsGradeService pointsGradeService;
   
   //---------------------积分等级管理---------------------
   /*
    * 查看所有积分等级
    */
   @CrossOrigin
   @AdminToken
   @GetMapping("/getpointsgradelist")
   public Result getAllPointsGrades() {
	   try {
		   return new Result(10,"积分等级信息",pointsGradeService.getAllPointsGrades(),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 根据分数查找积分等级
    */
   @CrossOrigin
   @AdminToken
   @GetMapping("/getpointsgradesbypoints/{miniPoints}")
   public Result getPointsGradeByPoints(@PathVariable("miniPoints") int miniPoints) {
	   try {
		   return new Result(11,"相应积分的积分等级信息",pointsGradeService.getPointsGradesByPoints(miniPoints),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 根据等级查找积分等级
    */
   @CrossOrigin
   @AdminToken
   @GetMapping("/getpointsgradesbygrade/{gradeName}")
   public Result getPointsGradeByPoints(@PathVariable("gradeName") String gradeName) {
	   try {
		   return new Result(12,"相应等级的积分等级信息",pointsGradeService.getPointsGradesByGrade(gradeName),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 根据id查找积分等级
    */
   @CrossOrigin
   @AdminToken
   @GetMapping("/getpointsgradebyid/{gradeId}")
   public Result getPointsGradeById(@PathVariable("gradeId") int gradeId) {
	   try {
		   return new Result(13,"相应等级的积分等级信息",pointsGradeService.getPointsGradeById(gradeId),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 删除积分等级
    */
   @CrossOrigin
   @AdminToken
   @DeleteMapping("/deletegradepoints/{gradeId}")
   public Result deleteGradePoints(@PathVariable("gradeId") int gradeId) {
	   try {
		  pointsGradeService.deletePointsGrade(gradeId);
		  return new Result(14,"删除后的积分等级信息",pointsGradeService.getAllPointsGrades(),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 添加积分等级
    */
   @CrossOrigin
   @AdminToken
   @PostMapping("/addgradepoints")
   public Result addGradePoints(@RequestBody PointsGrade pointsGrade) {
	   try {
		  pointsGrade.setGradeId(0);
		  pointsGradeService.addPointsGrade(pointsGrade);
		  return new Result(15,"添加后的积分等级信息",pointsGradeService.getAllPointsGrades(),null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }
   
   /*
    * 更新积分等级
    */
   @CrossOrigin
   @AdminToken
   @PutMapping("/updategradepoints")
   public Result updateGradePoints(@RequestBody PointsGrade pointsGrade) {
	   try {
		  pointsGradeService.updatePointsGrade(pointsGrade);
		  return new Result(16,"更新后的积分等级信息",null,null);
	   }catch(Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	   }
   }

   
   //---------------------用户管理-----------------------
   /*
	 * 通过用户名获取用户
	 */
//	@CrossOrigin
//	@NormalToken
//	@GetMapping("/user/{userName}")
//	public Result getUserByUserName(@PathVariable("userName") String username) {
//		try {
//			NormalUser user= normalUserService.getNormalUserByUserName(username);
//			user.setPassword(null);
//			return new Result(200, "用户信息",user, null);
//		} catch (Exception e) {
//			// TODO: handle exception
//			return new Result(-1,"error",e,null);
//		}
//	}

 
	/*
	 * 注解@CrossOrigin 跨域  @AdminToken 管理员权限   
	 * 删除用户
	 */
   @CrossOrigin
   @AdminToken
   @DeleteMapping("/deletenormaluser/{userName}")
   public Result deleteNormalUser(@PathVariable("userName") String userName) {
	   try {
		   normalUserService.deleteNormalUser(userName);
		 
		   return new Result(1, "删除成功", null, null);
	   }catch (Exception e) {
		   return new Result(0, "出现未知错误", null, null);
	}
	   
   }
   
   
	/*
	 * 获取用户列表
	 */   
   @CrossOrigin
   
   @GetMapping("/showuserlist")
   public Result showNormalUserList() {
	   try {
		   System.out.println("getUserList");
		   return new Result(1, "用户列表", normalUserService.getAllNormalUser(), null);
	} catch (Exception e) {
		return new Result(0, "出现未知错误", null, null);
	}
	
   }

	/*
	 * 获取某个用户
	 */
   @CrossOrigin
   @AdminToken
   @GetMapping("/searchuser")
   public Result searchNormalUser(NormalUser normalUser) {
	  try {
		  return new Result(1, "", normalUserService.getNormalUserByUserName(normalUser.getUserName()), null);
	} catch (Exception e) {
		return new Result(0, "出现未知错误", null, null);
	}
   }	

	/*
	 * 更新用户信息
	 */
   @CrossOrigin
   @AdminToken
   @PostMapping("/updateuser")
   public Result setNormalUserDisabled(@RequestBody NormalUser normanUser) {
	try {
		normalUserService.modifyNormalUserInfo(normanUser);
		return new Result(1, "修改成功",null, null);
	} catch (Exception e) {
		return new Result(0, "出现未知错误", null, null);
	}
	   
   }
 
}
