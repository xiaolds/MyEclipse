package com.datasure.sql;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class DBPoolTest {

	Connection con;
	PreparedStatement ps;

	@Test
	public void test() {
		con = DBPool.getConnection();
		try {
			ps = con.prepareStatement("select * from users;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = ps.executeQuery();
			rs.next();
			rs.next();
			assertEquals("xiaolds", rs.getString(2));
			assertEquals("fuck", rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBPool.close(con);
		}
	}

}
