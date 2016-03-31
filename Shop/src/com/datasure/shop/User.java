package com.datasure.shop;

/**
 * 
 * @ClassName: User 
 * @Description: 用于用户信息的封装
 * @date: 2016年3月9日 下午4:29:48 
 * @author LiDongSheng
 * @version
 */

public class User {
	
	private String name;
	private String password;
	
	public User(String name, String pass) {
		this.name = name;
		this.password = pass;
	}
	
	public User(String name){
		this.name = name;
		this.password = "123456";	//默认密码
	}

	
	/******getter and setter*******/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[" + name + ", " + password + "]";
	}
	
}
