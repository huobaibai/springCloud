package com.huobaibai.domain.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_user")

public class UserEO  {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_user_seq")
	@SequenceGenerator(name="t_user_seq",sequenceName="seq_t_user", allocationSize=1)
	private Long id;
	
	@Column(name="user_id")
	private String userId;
	private String name;
	private Integer age;
	private String address;
	
	
	public UserEO() {
		super();
	}

	public UserEO( String userId,String name, Integer age, String address) {
		super();
		this.userId=userId;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	

}
