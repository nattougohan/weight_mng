package com.practice.weightMng.app.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.practice.weightMng.domain.model.User;
import com.practice.weightMng.domain.repository.UserRepository;

/**
 *  ログイン時のユーザー情報判定用コントローラ
 */
@Controller
//@SessionAttributes(value = "user") 
public class LoginController {
	
	@Autowired
	UserRepository repository;

//	// TODO どうやらsessionには格納できた模様。ただし中身はない模様。DBとの連携はどうするのか？
//	@ModelAttribute(value = "user")
//	public User setUser(User user) {
//		return user;
//	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/loginResult", method=RequestMethod.GET) // POSTにするには？
	public ModelAndView loginResult(@RequestParam("id") Integer id, HttpSession session, ModelAndView mav) {
		// DBからユーザー情報をとってくる
		User user = repository.getOne(id);
		session.setAttribute("user", user);
		
		String sex = "";
		
		// 誕生日から現在の年齢を算出
		LocalDate birthday = LocalDate.parse(user.getBirthday());
		LocalDate currentDate = LocalDate.now();
		int age = (int) ChronoUnit.YEARS.between(birthday, currentDate);
		
		// 性別を判定
		if("0".equals(user.getSex())) {
			sex = "男性";
		} else if("1".equals(user.getSex())) {
			sex = "女性";
		} else {
			sex = "その他";
		}
		
		mav.addObject("nickname", user.getNickname());
		mav.addObject("age", age);
		mav.addObject("sex", sex);
		mav.addObject("height", user.getHeight());
		mav.setViewName("loginResult");
		return mav;
		
		// DBから取ってきた情報（テーブルのレコード情報まるごと）をsessionに入れる→htmlではsessionの情報から各情報を出力する
		// DBの情報を直接やり取りするのはControllerとの間だけ、情報の表示はsessionに入ったものを利用する
	}
	
	@RequestMapping(value="/weightRecord", method=RequestMethod.GET)
	public ModelAndView weightRecord(ModelAndView mav) {
		// loginResultのリンクから遷移するときにuserの情報を持ち越す方法が必要
		//　それによって、レコード結果の表示時の処理でDBへの書き込みと一覧表示を行うのにも使えるはず
		mav.setViewName("weightRecord");
		return mav;
	}

}
