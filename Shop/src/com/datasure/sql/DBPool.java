package com.datasure.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 数据库连接
 * @ClassName: DBPool 
 * @Description: 从外置文件获取数据库连接的配置
 * @date: 2016年3月9日 下午3:39:10 
 * @author Lids
 * @version
 */

public class DBPool {
//	public static void main(String[] args) {
//		DBPool.getConnection();
//	}
	
	//配置文件位置
	private static String param = DBPool.class.getResource("/DBConfig.properties").getPath().replace("%20", " "); //"Shop/WEB-INF/classes/DBConfig.properties";
	
	private static String url;
	private static String user;
	private static String password;
	
	//类加载的同时加载数据配置文件
	static{
		try {
			init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private DBPool(){
	}
	
	/**
	 * 
	 * init:初始化数据库参数. <br/>
	 *
	 * @author LiDongSheng
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void init() throws FileNotFoundException, IOException{
		//产生配置文件类
		Properties pro = new Properties();
		//加载配置文件
		pro.load(new FileInputStream(param));
		
		//获取配置
		String driver = pro.getProperty("driver");
		url = pro.getProperty("url");
		user = pro.getProperty("user");
		password = pro.getProperty("password");
		
		try {
			//加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * getConnection:获取数据库的连接. <br/>
	 * @author LiDongSheng
	 * @return 与数据库的连接
	 */
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	/**
	 * 
	 * close:关闭数据库连接<br/>
	 * @author LiDongSheng
	 */
	public static void close(Connection con){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
