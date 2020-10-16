package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class Util {
	
	/*
	 *	Map<String, Object> 출력함수 
	 * 
	 * */
	public static void printParam (Map<String, Object> param) {
		Iterator<String> keys = param.keySet().iterator();
		while( keys.hasNext() ){
	        String key = keys.next();
	        String value = param.get(key).toString();
	        System.out.println("input : " +key+",  "+value);
	    }
	}
	
	
	/*
	 * 해당 포맷의 현재날짜 return 함수 
	 * input : 날짜 포맷 
	 * output : 현재날짜 
	 * 
	 * format example : "yyyyMMddHHmmss" 
	 * */
	public static String getToDay(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		
		String today = dateFormat.format(date);
		
		return today;
	}
    
}
