package com.datasure.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.datasure.shop.User;

/**
 * 
 * @ClassName: UserDAO 
 * @Description: 查询数据库的操作<br>
 * 				主要包括用户的增删改查				
 * @date: 2016年3月9日 下午4:28:07 
 * @author LiDongSheng
 * @version
 */

public class UserDAO {
	
	/**所需要查询的表的名称*/
	private static final String TABLE_NAME = "users";
	
	/**
	 * 
	 * isUserExist:判断用户是否存在. <br/>
	 *
	 * @author LiDongSheng
	 * @param user 用户
	 * @return
	 * @throws SQLException 
	 */
	public static boolean isUserExist(final User user){
		//获取数据库连接
		Connection con = DBPool.getConnection();
		String sql = "select name from " + TABLE_NAME + " where name=?;";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			//获取结果集
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
	 * addUser:添加用户. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	用户
	 */
	public static void addUser(final User user){
		//获取数据库连接
		Connection con = DBPool.getConnection();
		String sql = "insert into " + TABLE_NAME + " (name, password) values (?,?);";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, user.getName());
			ps.setNString(2, user.getPassword());
			//执行
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
	 * delUser:删除用户. <br/>
	 * @author LiDongSheng
	 * @param user	用户
	 */
	public static void delUser(final User user){
		Connection con = DBPool.getConnection();
		String sql = "delete from " + TABLE_NAME + " where name = ?;";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setNString(1, user.getName());
			//执行
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
	 * updataPass:修改密码. <br/>
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
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
	

}
