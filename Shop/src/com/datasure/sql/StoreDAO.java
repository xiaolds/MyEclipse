package com.datasure.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.datasure.shop.Product;

/**
 * 
 * @ClassName: StoreDAO 
 * @Description: 对库存商品的操作
 * @date: 2016年3月10日 上午10:53:49 
 * @author LiDongSheng
 * @version
 */

public class StoreDAO {

	public static final String TABLE_NAME = "store";
	
	
	/**
	 * 
	 * getRemainPrdts:查询库存. <br/>
	 * @author LiDongSheng
	 * @return	null: 无产品<br>
	 * 			list: 产品list
	 */
	public static List<Product> getRemainPrdts(){
		List<Product> list = null;

		Connection con = null;
		String sql = "select * from " + TABLE_NAME + " ;";
		
		try{
			con = DBPool.getConnection();
			Statement stat = con.createStatement();
			//获取结果集
			ResultSet rs = stat.executeQuery(sql);
			if(!rs.first()){
				return null;
			}
			
			//初始化list
			list = new ArrayList<>();
			do{
				list.add(new Product(rs.getNString("good"),//
						rs.getInt("number"),rs.getInt("price")));
			}
			while(rs.next());
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
	 * addPrdtToStore:添加商品的便利类. <br/>
	 * @author LiDongSheng
	 * @param list 产品list
	 */
	public static void addPrdtToStore(List<Product> list){
		for(Product p: list){
			addPrdtToStore(p);
		}
	}
	
	/**
	 * 
	 * addPrdtToStore:向库存里添加商品. <br/>
	 * @author LiDongSheng
	 * @param prdt 产品
	 * 
	 */
	public static void addPrdtToStore(Product prdt){
		Connection con = null;
		String sql = "insert into " + TABLE_NAME//
				+ " (good, number, price) values (?,?,?);";
		
		try{
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, prdt.getName());
			ps.setInt(2, prdt.getNumbers());
			ps.setInt(3, prdt.getPrice());
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
	 * delPrdtFromStore:从库存里删除商品. <br/>
	 * @author LiDongSheng
	 * @param prdt: 产品
	 */
	public static void delPrdtFromStore(Product prdt){
		Connection con = null;
		String sql = "delete from " + TABLE_NAME//
				+ " where good = ?;";
		try{
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, prdt.getName());
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
	 * delPrdFromStore:从库存删除商品的便利方法. <br/>
	 * @author LiDongSheng
	 * @param list	产品集
	 */
	public static void delPrdtFromStore(List<Product> list){
		for(Product p: list){
			delPrdtFromStore(p);
		}
	}
	
	/**
	 * idPrdtExist:查询特定商品是否存在于库存中. <br/>
	 *
	 * @author LiDongSheng
	 * @param prdt: 产品
	 * @return true: 存在<br> false: 不存在
	 */
	public static boolean isPrdtExist(Product prdt){
		Connection con = null;
		String sql = "select * from " + TABLE_NAME//
				+ " where good = ?;";
		try{
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, prdt.getName());
			ResultSet rs = ps.executeQuery();
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
	 * updatePrdtFromStore:修改商品数量. <br/>
	 * @author LiDongSheng
	 * @param prdt: 产品
	 */
	public static void updatePrdtFromStore(Product prdt){
		Connection con = null;
		String sql = "update " + TABLE_NAME//
				+ " set number = ? where good = ?;";
		try{
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, prdt.getNumbers());
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
	 * updatePrdtFromStore:批量修改商品数量. <br/>
	 *
	 * @author LiDongSheng
	 * @param list: 产品集
	 */
	public static void updatePrdtFromStore(List<Product> list){
		for(Product p: list){
			updatePrdtFromStore(p);
		}
	}
	
}
