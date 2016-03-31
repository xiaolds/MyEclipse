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
 * @Description: 处理登陆请求，使用Cookies记录
 * @date: 2016年3月14日 上午11:30:30 
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
	 * 处理表单
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理登陆事务
		HttpSession session = request.getSession();
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		
		if(userName == null || password == null
				|| userName.length() == 0 || password.length() == 0){
			request.getRequestDispatcher("/index.jsp").include(request, response);
			out.println("姓名或密码不能为空，请重新输入~");
			out.flush();
			out.close();
			return;
		}
		
		User user = new User(userName, password);
		if(UserDAO.isUserExist(user)){
			//添加session
			session.setAttribute("name", userName);
			request.getRequestDispatcher("/store.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/index.jsp").include(request, response);
			out.println("姓名或密码错误，请重新输入~");
			out.flush();
			out.close();
			return;
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理注册与随便逛逛按钮
		//转发到其他页面
		
		String signIn = request.getParameter("signin");
		String hangUp = request.getParameter("hangup");
		
		//根据参数跳转
		if(signIn != null && hangUp == null){
			//跳转到注册页面
			request.getRequestDispatcher("/sign_in.jsp").forward(request, response);
		}
		else if(signIn == null && hangUp != null){
			//随便逛逛
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
