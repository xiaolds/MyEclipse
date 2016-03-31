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
 * @Description: �����������Ʒ
 * @date: 2016��3��15�� ����10:14:23 
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
		 * �ж��Ƿ��¼����¼��ֱ�ӽ���Ʒ�־û���
		 * δ��¼����cookie�ж�ȡ������ʾ
		 */
		//�ж����Ƿ��¼
		boolean isOnline = false;
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("name");
		
		System.out.println("Name:" + userName);
		if(userName != null){
			isOnline = true;
			//��productֱ����ӵ����ݿ�
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
