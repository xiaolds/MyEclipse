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
 * @Description: �ṩ�˹�����Ʒ��������ݿ����
 * @date: 2016��3��10�� ����9:54:30 
 * @author LiDongSheng
 * @version
 */

public class ProductDAO {
	
	private static final String TABLE_NAME = "goods";
	
	/**
	 * 
	 * getProduction:��ѯ�ض��û��������еĲ�Ʒ. <br/> Ӧ��֤����ѯ�û�ȷʵ����
	 * @author LiDongSheng
	 * @param user
	 * @return ������û�û�в�Ʒ,����null<br>
	 * 			���򷵻ز�Ʒ���ɵ�list<br>
	 */
	public static List<Product> getProduct(final User user){
		List<Product> list = null;
		
		Connection con = null;
		String sql = "select good , number, price from " + TABLE_NAME//
				+ " where name = ?;";
		try{
			//��ȡ���ݿ�����
			con = DBPool.getConnection();
			//��ȡStatement
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			//��ȡ�����
			ResultSet rs = ps.executeQuery();
			//�жϽ�����Ƿ�Ϊ��
			if(!rs.first()){	//�����Ϊ�գ�������û�����û�в�Ʒ
				return null;
			}
			//��ʼ��list
			list = new ArrayList<>();
			//�������
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
	 * isOwnPrdt:��ѯ�ض��û��Ƿ����ض���Ʒ. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	�û���
	 * @param prdt	��Ʒ��
	 * @return	true: ����<br> false: ������
	 */
	public static boolean isOwnPrdt(final User user, final Product prdt){
		
		Connection con = null;
		String sql = "select * from " + TABLE_NAME + " where name = \""//
				+ user.getName() + "\" and good = \"" + prdt.getName() + "\";";
		
		try{
			//��ȡ����
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
	 * isEmpty:��ѯ�ض��û��˻����Ƿ�Ϊ��. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	�û�
	 * @return true: �գ��޲�Ʒ<br>false�� �ǿ� <br>
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
	 * getPrdtNumber:�����ض��û����ض���Ʒ������. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	�û���
	 * @param prdt	��Ʒ��
	 * @return	���ز�Ʒ������-1��ʾ��ѯ����
	 */
	public static int getPrdtNumber(User user, Product prdt){
		Connection con = null;
		String sql = "select number from " + TABLE_NAME//
				+ " where name = \"" + user.getName()//
				+ "\" and good = \"" + prdt.getName() + "\";";
		try{
			//��ȡ����
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
	 * addPrdtToUser:���ض��û�����ض���Ʒ. <br/>
	 * @author LiDongSheng
	 * @param user	�û���
	 * @param prdt	��Ʒ��
	 */
	public static void addPrdtToUser(User user, Product prdt){
		Connection con = null;
		String sql = "insert into " + TABLE_NAME//
				+ " (name, good, number, price) values "//
				+ "(?,?,?,?);";
		try{
			//��ȡ����
			con = DBPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, user.getName());
			ps.setNString(2, prdt.getName());
			ps.setInt(3, prdt.getNumbers());
			ps.setInt(4, prdt.getPrice());
			//�ύ
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
	 * addPrdtToUser:�������������ض��û���Ӷ�����Ʒ. <br/>
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
	 * delProductFromUser:���ض��û��˻���ɾ���ض���Ʒ. <br/>
	 *
	 * @author LiDongSheng
	 * @param user	�û���
	 * @param prdt	��Ʒ
	 */
	public static void delPrdtFromUser(User user, Product prdt){
		Connection con = null;
		String sql = "delete from " + TABLE_NAME//
				+ " where name = ? and good = ?";
		try{
			//��ȡ����
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
	 * delProductFromUser:�����࣬����ɾ��. <br/>
	 *
	 * @author LiDongSheng
	 * @param user��	�û�
	 * @param list�� ��Ʒ��
	 */
	public static void delPrdtFromUser(User user, List<Product> list){
		for(Product p: list){
			delPrdtFromUser(user, p);
		}
	}
	
	/**
	 * 
	 * updatePrdtFromUser:�޸��ض��û��ض���Ʒ������. <br/>
	 *
	 * @author LiDongSheng
	 * @param user: �û�
	 * @param prdt�� ��Ʒ
	 */
	public static void updatePrdtFromUser(User user, Product prdt){
		Connection con = null;
		String sql = "update " + TABLE_NAME + " set number = ?"//
				+ " where name = ? and good = ?;";
		
		try{
			//��ȡ����
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
