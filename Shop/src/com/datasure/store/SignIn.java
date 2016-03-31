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
 * @Description: 处理登陆事务
 * @date: 2016年3月14日 下午2:40:32 
 * @author LiDongSheng
 * @version
 */

public class SignIn extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断数据有效性
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		
		if(userName == null || password == null
				|| userName.length() == 0 || password.length() == 0){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("姓名或密码不能为空，请重新输入~");
			out.flush();
			out.close();
			return;
		}
		
		//判断该用户是否已经存在
		User user = new User(userName, password);
		if(UserDAO.isUserExist(user)){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("该用户已经存在，请重新输入~");
			out.flush();
			out.close();
			return;
		}
		else{
			//用户不存在，登记
			UserDAO.addUser(user);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			out.println("注册成功，请重新登陆~");
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
