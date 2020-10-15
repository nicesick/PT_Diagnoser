package com.example.demo;

import java.util.Iterator;
import java.util.Map;

public class Util {
	
	public static void printParam (Map<String, Object> param) {
		Iterator<String> keys = param.keySet().iterator();
		while( keys.hasNext() ){
	        String key = keys.next();
	        String value = (String) param.get(key);
	        System.out.println("input : " +key+",  "+value);
	    }
	}
    
}
