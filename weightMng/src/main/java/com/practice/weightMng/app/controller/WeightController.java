package com.practice.weightMng.app.controller;



import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.weightMng.app.utility.Utility;
import com.practice.weightMng.domain.model.User;
import com.practice.weightMng.domain.model.WeightHistory;
import com.practice.weightMng.domain.repository.WeightHistoryRepository;

/**
 *  入力された体重と登録されている身長を基にBMIを算出する。ついでに性別・年齢からの適性度を表示する
 */
@Controller
public class WeightController {
	
	@Autowired
	WeightHistoryRepository repository;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;

	/**
	 * weightRecord.htmlから体重と計測日を入力して登録されたときに実行されるメソッド
	 * @param weight 体重
	 * @param date 計測日
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/weightRecordResult", method=RequestMethod.POST)
	public ModelAndView weightRecordResult(@RequestParam("weight") double weight, @RequestParam("date") String date, ModelAndView mav) {
		double index = 0.0;
		String judge = "";
		
		// sessionのユーザー情報を取得
		User user = (User) session.getAttribute("user");
		
		// 登録日付表示の整形
		String measuredDay = new String(Utility.dateJapaneseNotation(date));
		
		// ユーザーの現在の年齢を算出
		int age = Utility.calculateAge(user.getBirthday());
		
		// 年齢によって、計算する指数を分岐する
		if(age >=0 && age < 6) {
			// 幼児ならカウプ指数の計算
			index = Utility.calculateKaupIndex(weight, user.getHeight());
			mav.setViewName("babyWeightRecordResult");
		} else if(age >= 6 && age < 16) {
			// 学童ならローレル指数の計算
			index = Utility.calculateRohrerIndex(weight, user.getHeight());
			judge = Utility.schoolChildObesity(index);
			mav.setViewName("schoolChildWeightRecordResult");
		} else if(age >= 16) {
			// 成人ならBMIの計算
			index = Utility.calculateBMI(weight, user.getHeight());
			judge = Utility.adultObesity(index);
		}
		
		
		// DBへ登録するインスタンス生成
		WeightHistory weightRec = new WeightHistory();
		weightRec.setId(user.getId());
		weightRec.setWeight(weight);
		weightRec.setBmi(index);
		weightRec.setMeasuredDay(date);
		
		// DBへ登録する
		repository.save(weightRec);
		
		// 登録結果画面で使用する各種情報を格納
		mav.addObject("measuredDay", measuredDay);
		mav.addObject("weight", weight);
		mav.addObject("index", index);
		mav.addObject("judge", judge);

		return mav;
	}
	
	@RequestMapping(value="/showChart", method=RequestMethod.GET)
	public String showChart(Model model) { 
		
		// sessionのユーザー情報を取得
		User user = (User) session.getAttribute("user");
		// 対象ユーザーの記録された体重を取得
		List<WeightHistory> userRecordList = repository.findByUserId(user.getId());
		String[] measuredDays = new String[userRecordList.size()];
		double[] weights = new double[userRecordList.size()];
		
		for(int i = 0; i < userRecordList.size(); i++) {
			measuredDays[i] = userRecordList.get(i).getMeasuredDay();
			weights[i] = userRecordList.get(i).getWeight();
		}
		
		model.addAttribute("measuredDays", measuredDays);
		model.addAttribute("weights", weights);
		return "showChart";
	}
}
