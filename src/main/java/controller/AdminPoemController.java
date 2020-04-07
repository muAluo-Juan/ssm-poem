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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import annotation.AdminToken;
import model.LoginForm;
import po.Poem;
import service.PoemService;


@RestController
public class AdminPoemController {
	@Autowired
	private PoemService poemService;
	
	@CrossOrigin
	@AdminToken
	@GetMapping("/poems")
	public List<Poem> getAllPoems(){
		return poemService.getAllPoems();
	}

}
