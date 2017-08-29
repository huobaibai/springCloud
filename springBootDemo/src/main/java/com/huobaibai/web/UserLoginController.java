
package com.huobaibai.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huobaibai.domain.entry.UserEO;
import com.huobaibai.service.UserService;

@RestController
public class UserLoginController {

	@Autowired 
	private UserService userService;
	
	

	
	@RequestMapping("/index")
	public UserEO UserLogin() {

		return userService.findUserByUserId("10000002");
		
		
	}
	
	@RequestMapping("/pageable")
	public Page<UserEO> testPageable(){
		return userService.findUserByUserNamePage("a");
//		return userService.findAll();
	}
	
	@RequestMapping("/list")
	public List<UserEO> testList(){
		
		return userService.findUserByName("a");
	}
	
	
	@RequestMapping("/insert")
	public List<UserEO> restfulAdduser(){
		Map<String, Object> userinfoMap = new HashMap<String, Object>();
		userinfoMap.put("uid", "10000009");
		userinfoMap.put("age", 32);
		userinfoMap.put("name", "张三");
		userinfoMap.put("address", "鞍山");
		userService.addUser(userinfoMap);
		return userService.findUserByName("张三");
	}

	@RequestMapping("/addUser")
	public List<UserEO> adduser(){
		System.out.println("this is adduser");
		List<UserEO> ListUser= userService.findUserByName("张三");
		System.out.println("1:"+ListUser.size());
		List<UserEO> ListUser2= userService.findUserByName("张三");
		List<UserEO> ListUser3= new ArrayList<UserEO>();
		System.out.println("2:"+ListUser2.size());
		ListUser3.addAll(ListUser);
		ListUser3.addAll(ListUser2);
		/**不能如下操作
		 * ListUser2.addAll(ListUser);
		 * 因为首次ListUser2已经指向了缓存user地址引用
		 * 这样操作会更改缓存引用对应的值 造成后续的userService.findUserByName("张三")对应的缓存引用存储值变动;调用缓存值发生变动
		 */
		System.out.println("3:"+ListUser3.size());
		
		return ListUser;
		
		
	}
	
	@RequestMapping(value="/remove/{id}",method=RequestMethod.GET)
	public void removeUser(Long id) {
		userService.removeUser(id);
	}
	
	@RequestMapping(value="/save")
	public void saveUser() {
		
		UserEO user=new UserEO();
		user.setName("yangjian");
		
		userService.saveUser(user);
	}
	

}
 