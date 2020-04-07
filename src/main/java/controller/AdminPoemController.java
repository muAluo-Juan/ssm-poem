package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import annotation.AdminToken;
import model.LoginForm;
import model.Result;
import po.Poem;
import service.PoemService;
import utils.JWTUtil;


@RestController
public class AdminPoemController {
	@Autowired
	private PoemService poemService;
	
	/*
	 * 返回诗词列表
	 * admin
	 */
	@CrossOrigin
	@AdminToken
	@GetMapping("/poems")
	public Result getAllPoems(){
		Result result=new Result(1,"诗词列表",poemService.getAllPoems(), "");
		return result;
	}
	
	/*
	 * 测试用
	 */
	@CrossOrigin
	@GetMapping("/test")
	public String test(){
		String token=JWTUtil.generateToken("abcd","admin");
		System.out.println(JWTUtil.getAuthor(token));
		System.out.println(JWTUtil.getUsername(token));
		System.out.println(token);
		return token;
	}
	
	/*
	 * 删除诗词
	 * admin
	 */
	@CrossOrigin
	@AdminToken
	@DeleteMapping("/poems/{id}")
	public Result deletePoem(@PathVariable("id") int id) {
		poemService.deletePoem(id);
		return new Result(1, "删除成功", null, null);
	}
	
	/*
	 * 添加诗词
	 * admin
	 */
	@CrossOrigin
	@AdminToken
	@PostMapping("/poems")
	public Result addPoem(Poem poem) {
		poemService.addPoem(poem);
		return new Result(1, "添加成功", null, null);
	}
	

}
