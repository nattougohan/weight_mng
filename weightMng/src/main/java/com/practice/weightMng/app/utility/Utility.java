package com.practice.weightMng.app.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 
 * @author NakanoRyo
 * @category 各クラスで共通して使えるメソッドを定義するUtilityクラス。
 *
 */

public class Utility {
	
	// 性別判定用の定数
	final static String MAN = "0";
	final static String WOMAN = "1";
	
	// ローレル指数の肥満度結果用の定数
	final static String ROHRER_LOWER_WEIGHT = "やせすぎ";
	final static String ROHRER_LOW_WEIGHT = "やせてる";
	final static String ROHRER_NORMAL_WEIGHT = "ふつう";
	final static String ROHRER_OVER_WEIGHT = "ふとっている";
	final static String ROHRER_MORE_OVER_WEIGHT = "ふとりすぎ";
	
	// BMIの肥満度結果用の定数
	final static String LOW_WEIGHT = "低体重（痩せ型）";
	final static String NORMAL_WEIGHT = "普通体重";
	final static String OVER_WEIGHT_1 = "肥満（1度）";
	final static String OVER_WEIGHT_2 = "肥満（2度）";
	final static String OVER_WEIGHT_3 = "肥満（3度）";
	final static String OVER_WEIGHT_4 = "肥満（4度）";
	
	/**
	 * 登録日付表示の整形
	 * @param date (yyyy-mm-dd)
	 * @return yyyy年mm月dd日
	 */
	public static StringBuilder dateJapaneseNotation(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("年")
			.append(month).append("月")
			.append(day).append("日");
		return sb;
	}

	/**
	 * 幼児（3ヶ月〜5歳）の場合、カウプ指数の計算　体重kg / (身長m)^2
	 * @param weight
	 * @param height
	 * @return カウプ指数(少数第二位まで)
	 */
	public static double calculateKaupIndex(double weight, double height) {
		double kaupIndex = weight / ((height / 100) * (height / 100));
		double roundKaupIndex =((double) Math.round(kaupIndex * 100)) / 100;
		return roundKaupIndex;
	}
	
	
	/**
	 * 小中学生（6〜15歳）の場合、ローレル指数の計算　体重kg / (身長m)^3 * 10
	 * @param weight
	 * @param height
	 * @return ローレル指数(少数第二位まで)
	 */
	public static double calculateRohrerIndex(double weight, double height) {
		double rohrerIndex = weight / ((height / 100) * (height / 100) * (height / 100)) * 10;
		double roundRohrerIndex =((double) Math.round(rohrerIndex * 100)) / 100;
		return roundRohrerIndex;
	}

	/**
	 * 成人（16歳以上）の場合、BMIの計算　体重kg / (身長m)^2
	 * 
	 * @param weight
	 * @param height
	 * @return BMI(少数第二位まで)
	 */
	public static double calculateBMI(double weight, double height) {
		double bmi = weight / ((height / 100) * (height / 100));
		double roundBmi =((double) Math.round(bmi * 100)) / 100;
		return roundBmi;
	}

	/**
	 * 誕生日から現在の年齢を算出する
	 * @param birthday
	 * @return age
	 */
	public static int calculateAge(String birthday) {
		int age = (int) ChronoUnit.YEARS.between(LocalDate.parse(birthday), LocalDate.now());
		return age;
	}
	
	/**
	 * 性別を判定する
	 * @param sex (0 or 1)
	 * @return 性別
	 */
	public static String determineSex(String sex) {
		if(MAN.equals(sex)) {
			return "男性";
		} else if(WOMAN.equals(sex)) {
			return "女性";
		} else {
			return "その他";
		}
	}

	/**
	 * ローレル指数から学童（6〜15歳）の肥満度を算出する
	 * @param rohrerIndex
	 * @return 肥満度に応じた結果文字列
	 */
	public static String schoolChildObesity(double rohrerIndex) {
		if(rohrerIndex >= 0.0 && rohrerIndex < 100) {
			return ROHRER_LOWER_WEIGHT;
		} else if(rohrerIndex >= 100 && rohrerIndex < 115) {
			return ROHRER_LOW_WEIGHT;
		} else if(rohrerIndex >= 115 && rohrerIndex < 145) {
			return ROHRER_NORMAL_WEIGHT;
		} else if(rohrerIndex >= 145 && rohrerIndex < 160) {
			return ROHRER_OVER_WEIGHT;
		} else if(rohrerIndex >= 160) {
			return ROHRER_MORE_OVER_WEIGHT;
		} else {
			return "ローレル指数がマイナスでござるぞ";
		}
	}
	
	/**
	 * BMIから成人用（16歳以上）の肥満度を算出する
	 * @param bmi
	 * @return 肥満度に応じた結果文字列
	 */
	public static String adultObesity(double bmi) {
		if(bmi >= 0.0 && bmi < 18.5) {
			return LOW_WEIGHT;
		} else if(bmi >= 18.5 && bmi < 25.0) {
			return NORMAL_WEIGHT;
		} else if(bmi >= 25.0 && bmi < 30.0) {
			return OVER_WEIGHT_1;
		} else if(bmi >= 30.0 && bmi < 35.0) {
			return OVER_WEIGHT_2;
		} else if(bmi >= 35.0 && bmi < 40.0) {
			return OVER_WEIGHT_3;
		} else if(bmi >= 40.0) {
			return OVER_WEIGHT_4;
		} else {
			return "bmiがマイナスでござるぞ";
		}
	}
	
	/**
	 * 成人（高校生以上）の肥満度を判定する
	 * @param age
	 * @param bmi
	 * @return 判定結果
	 */
	public static String determineObesity(int age, double index) {
		if(age >=0 && age < 6) {
			return "幼児の肥満度は一覧表となっておる";
		} else if(age >= 6 && age < 16) {
			return schoolChildObesity(index);
		} else if (age >= 16) {
			return adultObesity(index);
		} else {
			return "年齢がマイナスとは・・・お主一体・・・？";
		}
	}

}
