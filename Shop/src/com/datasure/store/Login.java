package com.datasure.store;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datasure.shop.User;
import com.datasure.sql.UserDAO;



/**
 * 
 * @ClassName: Login 
 * @Description: �����½����ʹ��Cookies��¼
 * @date: 2016��3��14�� ����11:30:30 
 * @author LiDongSheng
 * @version
 */
@WebServlet(name="Login", urlPatterns="/login")
public class Login extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * �����
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����½����
		HttpSession session = request.getSession();
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		
		if(userName == null || password == null
				|| userName.length() == 0 || password.length() == 0){
			request.getRequestDispatcher("/index.jsp").include(request, response);
			out.println("���������벻��Ϊ�գ�����������~");
			out.flush();
			out.close();
			return;
		}
		
		User user = new User(userName, password);
		if(UserDAO.isUserExist(user)){
			//���session
			session.setAttribute("name", userName);
			request.getRequestDispatcher("/store.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/index.jsp").include(request, response);
			out.println("�����������������������~");
			out.flush();
			out.close();
			return;
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����ע��������䰴ť
		//ת��������ҳ��
		
		String signIn = request.getParameter("signin");
		String hangUp = request.getParameter("hangup");
		
		//���ݲ�����ת
		if(signIn != null && hangUp == null){
			//��ת��ע��ҳ��
			request.getRequestDispatcher("/sign_in.jsp").forward(request, response);
		}
		else if(signIn == null && hangUp != null){
			//�����
			request.getRequestDispatcher("/store.jsp").forward(request, response);
		}
		else{
			//
		}
		
		
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// TODO
	}
	
	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
