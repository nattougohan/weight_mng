package com.practice.weightMng.app.controller;

/*
 * Configで認証の設定は行うため、当クラスは不要となる予定
 */
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
//		
//		if(password.equals(user.getPassword())) { // ここでエラーになってる
//			mav.addObject(user);
//			mav.setViewName("loginResult");
//		}else {
//			mav.setViewName("index/?error=true");
//		}
//
//		
//		// ログイン情報がDBに登録されているユーザーと合致すればログイン成功画面を表示
//		return mav; 
//	}

}
