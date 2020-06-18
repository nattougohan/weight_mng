package com.practice.weightMng.app.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.weightMng.app.utility.Utility;
import com.practice.weightMng.domain.model.User;
import com.practice.weightMng.domain.repository.UserRepository;

/**
 *  ログイン時のユーザー情報表示用コントローラ
 */
@Controller
public class LoginController {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/loginResult", method=RequestMethod.GET) // POSTにするには？
	public ModelAndView loginResult(@RequestParam("id") Integer id, ModelAndView mav) {
		
		// DBからユーザー情報をとってくる
		User user = repository.getOne(id);
		
		// ユーザー情報をsessionスコープに格納
		session.setAttribute("user", user);
		
		// 誕生日から現在の年齢を算出
		int age = Utility.calculateAge(user.getBirthday());
		
		// 性別を判定
		String sex = Utility.determineSex(user.getSex());
		
		mav.addObject("nickname", user.getNickname());
		mav.addObject("age", age);
		mav.addObject("sex", sex);
		mav.addObject("height", user.getHeight());
		return mav;
		
		// DBから取ってきた情報（テーブルのレコード情報まるごと）をsessionに入れる→htmlではsessionの情報から各情報を出力する
		// DBの情報を直接やり取りするのはControllerとの間だけ、情報の表示はsessionに入ったものを利用する
		// DBの情報を加工して表示するものはJavaで処理してmavの情報を表示させる。使い分けしてるつもりだが定石的にはどうなんだろうか？
	}
	
	@RequestMapping(value="/weightRecord", method=RequestMethod.GET)
	public String weightRecord() {
		return "weightRecord";
	}

}
