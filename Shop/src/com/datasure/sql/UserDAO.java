package com.datasure.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.datasure.shop.User;

/**
 * 
 * @ClassName: UserDAO 
 * @Description: ��ѯ���ݿ�Ĳ���<br>
 * 				��Ҫ�����û�����ɾ�Ĳ�				
 * @date: 2016��3��9�� ����4:28:07 
 * @author LiDongSheng
 * @version
 */

public class UserDAO {
	
	/**����Ҫ��ѯ�ı������*/
	private static final String TABLE_NAME = "users";
	
	/**
	 * 
	 * isUserExist:�ж��û��Ƿ����. <br/>
	 *
	 * @author LiDongSheng
	 * @param user �û�
	 * @return
	 * @throws SQLException 
	 */
	public static boolean isUserExist(final User user){
		//��ȡ���ݿ�����
		Connection con = DBPool.getConnection();
		String sql = "select name from " + TABLE_NAME + " where name=?;";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			//��ȡ�����
			ResultSet rs = ps.executeQuery();
			return rs.first();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		
		return false;
	}
	
	/**
	 * 
	 * addUser:����û�. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	�û�
	 */
	public static void addUser(final User user){
		//��ȡ���ݿ�����
		Connection con = DBPool.getConnection();
		String sql = "insert into " + TABLE_NAME + " (name, password) values (?,?);";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, user.getName());
			ps.setNString(2, user.getPassword());
			//ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		
	}
	
	
	/**
	 * 
	 * delUser:ɾ���û�. <br/>
	 * @author LiDongSheng
	 * @param user	�û�
	 */
	public static void delUser(final User user){
		Connection con = DBPool.getConnection();
		String sql = "delete from " + TABLE_NAME + " where name = ?;";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, user.getName());
			//ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
	
	/**
	 * 
	 * updataPass:�޸�����. <br/>
	 *
	 * @author LiDongSheng
	 * @param user
	 */
	public static void updataPass(final User user){
		Connection con = DBPool.getConnection();
		String sql = "update " + TABLE_NAME + " set password = ? where name=?;";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, user.getPassword());
			ps.setNString(2, user.getName());
			//ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
	

}
