package com.huobaibai.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.huobaibai.domain.entry.UserEO;

@Mapper
public interface UserDaoCustom {
	@Results({
//		@Result(property="id",column="id"),
//		@Result(property="name",column="name"),
		@Result(property="userId",column="user_id"),
//		@Result(property="address",column="address")
	})
	@Select("select name,address,user_id,age from t_user where user_id=#{uid}")	
	public UserEO findUserByUserid(@Param("uid") String uid);
	
	
	/**
	 * use dynamic sql
	 * @param uid
	 * @return
	 */
	@Results({
//		@Result(property="id",column="id"),
//		@Result(property="name",column="name"),
		@Result(property="userId",column="user_id"),
//		@Result(property="address",column="address")
	})
	@SelectProvider(type=UserDaoCustomCondition.class ,method="findUserByUseridCondition")
	 UserEO findUserByUseridDymaic( String uid);
	
	class UserDaoCustomCondition{
		public String findUserByUseridCondition(final String uid) {
		return new SQL() {{
		SELECT("name,user_id,age");	
		FROM("t_user");
		if(uid!=null) {
		WHERE(" user_id=#{uid}");
		}
		}}.toString();
		}
		
	
		
	}
	
	@Insert("insert  into  t_user (id,user_id,name,age,address) values(seq_t_user.nextval,#{uid,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER},#{address,jdbcType=VARCHAR})")
	 void insertUserInfoByMap(Map<String,Object> UserInfomap);

}
