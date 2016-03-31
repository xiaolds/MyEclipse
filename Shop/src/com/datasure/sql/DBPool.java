package com.datasure.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * ���ݿ�����
 * @ClassName: DBPool 
 * @Description: �������ļ���ȡ���ݿ����ӵ�����
 * @date: 2016��3��9�� ����3:39:10 
 * @author Lids
 * @version
 */

public class DBPool {
//	public static void main(String[] args) {
//		DBPool.getConnection();
//	}
	
	//�����ļ�λ��
	private static String param = DBPool.class.getResource("/DBConfig.properties").getPath().replace("%20", " "); //"Shop/WEB-INF/classes/DBConfig.properties";
	
	private static String url;
	private static String user;
	private static String password;
	
	//����ص�ͬʱ�������������ļ�
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
	 * init:��ʼ�����ݿ����. <br/>
	 *
	 * @author LiDongSheng
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void init() throws FileNotFoundException, IOException{
		//���������ļ���
		Properties pro = new Properties();
		//���������ļ�
		pro.load(new FileInputStream(param));
		
		//��ȡ����
		String driver = pro.getProperty("driver");
		url = pro.getProperty("url");
		user = pro.getProperty("user");
		password = pro.getProperty("password");
		
		try {
			//��������
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * getConnection:��ȡ���ݿ������. <br/>
	 * @author LiDongSheng
	 * @return �����ݿ������
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
	 * close:�ر����ݿ�����<br/>
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
