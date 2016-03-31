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
 * @Description: �Կ����Ʒ�Ĳ���
 * @date: 2016��3��10�� ����10:53:49 
 * @author LiDongSheng
 * @version
 */

public class StoreDAO {

	public static final String TABLE_NAME = "store";
	
	
	/**
	 * 
	 * getRemainPrdts:��ѯ���. <br/>
	 * @author LiDongSheng
	 * @return	null: �޲�Ʒ<br>
	 * 			list: ��Ʒlist
	 */
	public static List<Product> getRemainPrdts(){
		List<Product> list = null;

		Connection con = null;
		String sql = "select * from " + TABLE_NAME + " ;";
		
		try{
			con = DBPool.getConnection();
			Statement stat = con.createStatement();
			//��ȡ�����
			ResultSet rs = stat.executeQuery(sql);
			if(!rs.first()){
				return null;
			}
			
			//��ʼ��list
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
	 * addPrdtToStore:�����Ʒ�ı�����. <br/>
	 * @author LiDongSheng
	 * @param list ��Ʒlist
	 */
	public static void addPrdtToStore(List<Product> list){
		for(Product p: list){
			addPrdtToStore(p);
		}
	}
	
	/**
	 * 
	 * addPrdtToStore:�����������Ʒ. <br/>
	 * @author LiDongSheng
	 * @param prdt ��Ʒ
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
	 * delPrdtFromStore:�ӿ����ɾ����Ʒ. <br/>
	 * @author LiDongSheng
	 * @param prdt: ��Ʒ
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
	 * delPrdFromStore:�ӿ��ɾ����Ʒ�ı�������. <br/>
	 * @author LiDongSheng
	 * @param list	��Ʒ��
	 */
	public static void delPrdtFromStore(List<Product> list){
		for(Product p: list){
			delPrdtFromStore(p);
		}
	}
	
	/**
	 * idPrdtExist:��ѯ�ض���Ʒ�Ƿ�����ڿ����. <br/>
	 *
	 * @author LiDongSheng
	 * @param prdt: ��Ʒ
	 * @return true: ����<br> false: ������
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
	 * updatePrdtFromStore:�޸���Ʒ����. <br/>
	 * @author LiDongSheng
	 * @param prdt: ��Ʒ
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
	 * updatePrdtFromStore:�����޸���Ʒ����. <br/>
	 *
	 * @author LiDongSheng
	 * @param list: ��Ʒ��
	 */
	public static void updatePrdtFromStore(List<Product> list){
		for(Product p: list){
			updatePrdtFromStore(p);
		}
	}
	
}
