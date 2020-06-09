package com.practice.weightMng.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.weightMng.domain.model.User;
import com.practice.weightMng.domain.repository.UserRepository;

// ログイン時のユーザー情報判定用コントローラ
@Controller
public class LoginController {
	
//	@Autowired
//	UserRepository repository;
//	
//	@RequestMapping(value="/loginResult", method=RequestMethod.POST)
//	public ModelAndView loginJudge(@RequestParam Integer id, @RequestParam String password) {
//		ModelAndView mav = new ModelAndView();
//		User user = repository.getOne(id);
//		mav.addObject("user", user);
//		mav.setViewName("loginResult");
//		return mav; 
//	}

}
