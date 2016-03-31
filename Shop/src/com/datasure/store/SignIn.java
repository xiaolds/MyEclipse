package com.datasure.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datasure.shop.User;
import com.datasure.sql.UserDAO;

/**
 * 
 * @ClassName: SignIn 
 * @Description: �����½����
 * @date: 2016��3��14�� ����2:40:32 
 * @author LiDongSheng
 * @version
 */

public class SignIn extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�ж�������Ч��
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		
		if(userName == null || password == null
				|| userName.length() == 0 || password.length() == 0){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("���������벻��Ϊ�գ�����������~");
			out.flush();
			out.close();
			return;
		}
		
		//�жϸ��û��Ƿ��Ѿ�����
		User user = new User(userName, password);
		if(UserDAO.isUserExist(user)){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("���û��Ѿ����ڣ�����������~");
			out.flush();
			out.close();
			return;
		}
		else{
			//�û������ڣ��Ǽ�
			UserDAO.addUser(user);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("ע��ɹ��������µ�½~");
			out.flush();
			out.close();
			return;
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	
}
