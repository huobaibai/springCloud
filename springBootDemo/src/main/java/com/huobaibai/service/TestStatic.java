package com.huobaibai.service;

import java.util.HashMap;
import java.util.Map;

public class TestStatic {

public TestStatic() {
		
		System.out.println("cotra");
	}
	static Map<String,String>  map1=new HashMap<String,String>();
	
	static {
		map1.put("key1","hh1");
		
	System.out.println("key1");
				}
	static {
		map1.put("key2","hh2");
		System.out.println("key2");
	}
	
	
			


}
