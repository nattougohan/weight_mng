package com.practice.weightMng.app.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.weightMng.domain.model.User;
import com.practice.weightMng.domain.repository.UserRepository;

// 入力された体重と登録されている身長を基にBMIを算出する。ついでに性別・年齢からの適性度を表示する

@Controller
public class WeightToBMIController {
	
	@Autowired
	UserRepository repository;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/loginResult", method=RequestMethod.GET)
	public ModelAndView loginResult(@RequestParam("id") Integer id, ModelAndView mav) {
		// DBからユーザー情報をとってきて、表示させる
//		String username = httpServletRequest.getRemoteUser();
		User user = repository.getOne(id);
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
		
		mav.addObject("user", user.getNickname());
		mav.addObject("age", age);
		mav.addObject("sex", sex);
		mav.addObject("height", user.getHeight());
		mav.setViewName("loginResult");
		return mav;
	}
//	
//	@RequestMapping("/home")
//	public String home() {
//		return "forward:/";
//	}
//	

//	
//	@RequestMapping(value="/", method=RequestMethod.POST)
//	public ModelAndView send(
//		@RequestParam(value="check1", required=false)boolean check1,
//		@RequestParam(value="radio1", required=false)String radio1,
//		@RequestParam(value="select1", required=false)String select1,
//		@RequestParam(value="select2", required=false)String[] select2,
//		ModelAndView mav) {
//		
//		String res = "";
//		try {
//			res = "check:" + check1 +
//				  " radio:" + radio1 +
//				  " select:" + select1 +
//				  "\nselect2:";
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		try {
//			res += select2[0];
//			for(int i = 1; i < select2.length; i++) {
//				res += ", " + select2[i];
//			}
//		} catch (NullPointerException e) {
//			res += "null";
//		}
//		mav.addObject("msg", res);
//		mav.setViewName("index");
//		return mav;
//	}
}
