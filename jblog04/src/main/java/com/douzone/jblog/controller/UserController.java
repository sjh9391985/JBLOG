package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.insert(vo);
		blogService.insert(vo.getId());
		categoryService.insert(vo.getId());
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess",method = RequestMethod.GET)
	public String joinSuccess() {
		
		return "user/joinsuccess";
		
	}

}
