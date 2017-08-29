package com.huobaibai.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.huobaibai.dao.UserDao;
import com.huobaibai.dao.UserDaoCustom;
import com.huobaibai.domain.entry.UserEO;
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
    @Autowired
    private UserDaoCustom userDaoCustom;
    
    @Cacheable(value="users", key="#user.id")
	public List<UserEO> findUserByName(String name) {

		return userDao.findByName(name);

	}
	
	
	public UserEO findUserByUserId(String uid) {

//		return userDao.findByUserId(uid);
//		UserEO  user=userDaoCustom.findUserByUserid(uid);
		UserEO  user=userDaoCustom.findUserByUseridDymaic(uid);
		return user;

	}
	
	public Page<UserEO> findUserByUserNamePage(String name) {

		return userDao.findByNamePage(name, new PageRequest(1,2,new Sort(Direction.ASC,"name")));

	}
	
	public   Page<UserEO> findAll( ) {
		return userDao.findAll(new PageRequest(1,2));

	}
	
	
	
	 public   void addUser(Map<String,Object> userinfo ) {
		
		 userDaoCustom.insertUserInfoByMap( userinfo);

	}
	 
	 @CachePut(value="users", key="#user.id")
	 public  void saveUser(UserEO user) {
		 userDao.save(user);
	 }
	 
	 @CacheEvict(value="users")
	 public void removeUser(Long id) {
		 userDao.delete(id);
	 }
}
