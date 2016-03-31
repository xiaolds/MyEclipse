package com.datasure.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.datasure.shop.Product;
import com.datasure.shop.User;

/**
 * 
 * @ClassName: ProductionDAO 
 * @Description: 提供了关于商品的相关数据库操作
 * @date: 2016年3月10日 上午9:54:30 
 * @author LiDongSheng
 * @version
 */

public class ProductDAO {
	
	private static final String TABLE_NAME = "goods";
	
	/**
	 * 
	 * getProduction:查询特定用户名下所有的产品. <br/> 应保证所查询用户确实存在
	 * @author LiDongSheng
	 * @param user
	 * @return 如果该用户没有产品,返回null<br>
	 * 			否则返回产品构成的list<br>
	 */
	public static List<Product> getProduct(final User user){
		List<Product> list = null;
		
		Connection con = null;
		String sql = "select good , number, price from " + TABLE_NAME//
				+ " where name = ?;";
		try{
			//获取数据库连接
			con = DBPool.getConnection();
			//获取Statement
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			//获取结果集
			ResultSet rs = ps.executeQuery();
			//判断结果集是否为空
			if(!rs.first()){	//结果集为空，代表该用户名下没有产品
				return null;
			}
			//初始化list
			list = new ArrayList<>();
			//遍历结果
			String good = null;
			int number = 0;
			int price = 0;
			
			do{
				good = rs.getString("good");
				number = rs.getInt("number");
				price = rs.getInt("price");
				list.add(new Product(good, number,price));
			}while(rs.next());
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		
		return list;
	}
	
	/**
	 * 
	 * isOwnPrdt:查询特定用户是否含有特定商品. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	用户名
	 * @param prdt	产品名
	 * @return	true: 含有<br> false: 不含有
	 */
	public static boolean isOwnPrdt(final User user, final Product prdt){
		
		Connection con = null;
		String sql = "select * from " + TABLE_NAME + " where name = \""//
				+ user.getName() + "\" and good = \"" + prdt.getName() + "\";";
		
		try{
			//获取连接
			con = DBPool.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs.first();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		return false;
	}
	
	/**
	 * 
	 * isEmpty:查询特定用户账户下是否为空. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	用户
	 * @return true: 空，无产品<br>false： 非空 <br>
	 */
	public static boolean isEmpty(User user){
		
		Connection con = null;
		String sql = "select * from " + TABLE_NAME //
				+ " where name = \"" + user.getName() + "\";";
		
		try{
			con = DBPool.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs.first()? false: true;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		
		return false;
	}
	
	/**
	 * 
	 * getPrdtNumber:返回特定用户，特定商品的数量. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	用户名
	 * @param prdt	产品名
	 * @return	返回产品数量，-1表示查询错误
	 */
	public static int getPrdtNumber(User user, Product prdt){
		Connection con = null;
		String sql = "select number from " + TABLE_NAME//
				+ " where name = \"" + user.getName()//
				+ "\" and good = \"" + prdt.getName() + "\";";
		try{
			//获取连接
			con = DBPool.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(!rs.first()){
				return 0;
			}
			else{
				return rs.getInt("number");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
		return -1;
	}
	
	/**
	 * 
	 * addPrdtToUser:向特定用户添加特定商品. <br/>
	 * @author LiDongSheng
	 * @param user	用户名
	 * @param prdt	产品名
	 */
	public static void addPrdtToUser(User user, Product prdt){
		Connection con = null;
		String sql = "insert into " + TABLE_NAME//
				+ " (name, good, number, price) values "//
				+ "(?,?,?,?);";
		try{
			//获取连接
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			ps.setNString(2, prdt.getName());
			ps.setInt(3, prdt.getNumbers());
			ps.setInt(4, prdt.getPrice());
			//提交
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
	
	/**
	 * 
	 * addPrdtToUser:便利方法，向特定用户添加多种商品. <br/>
	 *
	 * @author LiDongSheng
	 * @param user
	 * @param list
	 */
	public static void addPrdtToUser(User user, List<Product> list){
		for(Product p: list){
			addPrdtToUser(user, p);
		}
	}
	
	/**
	 * 
	 * delProductFromUser:从特定用户账户中删除特定商品. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	用户名
	 * @param prdt	产品
	 */
	public static void delPrdtFromUser(User user, Product prdt){
		Connection con = null;
		String sql = "delete from " + TABLE_NAME//
				+ " where name = ? and good = ?";
		try{
			//获取连接
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			ps.setNString(2, prdt.getName());
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
	
	/**
	 * 
	 * delProductFromUser:便利类，批量删除. <br/>
	 *
	 * @author LiDongSheng
	 * @param user：	用户
	 * @param list： 产品集
	 */
	public static void delPrdtFromUser(User user, List<Product> list){
		for(Product p: list){
			delPrdtFromUser(user, p);
		}
	}
	
	/**
	 * 
	 * updatePrdtFromUser:修改特定用户特定产品的数量. <br/>
	 *
	 * @author LiDongSheng
	 * @param user: 用户
	 * @param prdt： 产品
	 */
	public static void updatePrdtFromUser(User user, Product prdt){
		Connection con = null;
		String sql = "update " + TABLE_NAME + " set number = ?"//
				+ " where name = ? and good = ?;";
		
		try{
			//获取连接
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, prdt.getNumbers());
			ps.setNString(2, user.getName());
			ps.setNString(3, prdt.getName());
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}
}
