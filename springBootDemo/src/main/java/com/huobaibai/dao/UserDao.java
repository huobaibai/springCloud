package com.huobaibai.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huobaibai.domain.entry.UserEO;



@CacheConfig(cacheNames="users")
public interface UserDao  extends JpaRepository<UserEO, Long>{
	
	
	@Query("select u from UserEO u where u.name=:uname")
	public List<UserEO> findByName(@Param("uname")String name);
	
	@Query("select u from UserEO u where u.userId=:uid")
	public UserEO findByUserId(@Param("uid")String uid);
	
	@Query("select u from UserEO u where u.name=:uname")
	public Page<UserEO> findByNamePage(@Param("uname")String name,Pageable page);
	
	
	
	
	
	
}
