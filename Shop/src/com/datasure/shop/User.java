package com.datasure.shop;

/**
 * 
 * @ClassName: User 
 * @Description: �����û���Ϣ�ķ�װ
 * @date: 2016��3��9�� ����4:29:48 
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
		this.password = "123456";	//Ĭ������
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
