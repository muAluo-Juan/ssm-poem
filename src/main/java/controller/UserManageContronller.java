package controller;

import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Stmnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import annotation.AdminToken;
import model.Result;
import po.NormalUser;
import service.NormalUserService;


@RestController
public class UserManageContronller {
   @Autowired
   private NormalUserService normalUserService;
 
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
   @GetMapping("/updateuser")
   public Result setNormalUserDisabled(NormalUser normanUser) {
	try {
		normalUserService.modifyNormalUserInfo(normanUser);
		return new Result(1, "修改成功",null, null);
	} catch (Exception e) {
		return new Result(0, "出现未知错误", null, null);
	}
	   
   }
 
}