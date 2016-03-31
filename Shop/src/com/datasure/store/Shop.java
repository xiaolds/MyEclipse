package com.datasure.store;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: Shop 
 * @Description: 负责处理添加商品
 * @date: 2016年3月15日 上午10:14:23 
 * @author LiDongSheng
 * @version
 */
@WebServlet(name="shop", urlPatterns="/shop")
public class Shop extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 判断是否登录，登录了直接将商品持久化，
		 * 未登录，从cookie中读取数据显示
		 */
		//判断是是否登录
		boolean isOnline = false;
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("name");
		
		System.out.println("Name:" + userName);
		if(userName != null){
			isOnline = true;
			//将product直接添加到数据库
			addPrdtToDB(req, resp);
		}
		
	}
	
	private void addPrdtToDB(HttpServletRequest req, HttpServletResponse resp){
		Enumeration<String> prdts = req.getAttributeNames();
		
		while(prdts.hasMoreElements()){
			System.out.println(prdts.toString());
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
