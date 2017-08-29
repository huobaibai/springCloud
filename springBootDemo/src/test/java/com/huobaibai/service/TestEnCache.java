package com.huobaibai.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.huobaibai.dao.UserDao;
import com.huobaibai.domain.entry.UserEO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnCache {
	
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private UserService userService;
	@Before
	public void before() {
		userService.saveUser(new UserEO("100010001","李四",32,"天津"));
		
	}
	@Test
	public void test() throws Exception {
		System.out.println("this is adduser");
		List<UserEO> ListUser=new ArrayList<UserEO>();
		List<UserEO> ListUser2=new ArrayList<UserEO>();
//		userService.saveUser(new UserEO("100010001","李四",32,"天津"));
		System.out.println("0:"+ListUser.size());
		ListUser= userService.findUserByName("张三");
		System.out.println("1:"+ListUser.size());
		ListUser2= userService.findUserByName("张三");
		ListUser.addAll(ListUser2);
		System.out.println("2:"+ListUser.size());
		
		for(UserEO user:ListUser) {
			System.out.println("11!!!:"+user.getUserId());
		}
	}

}
