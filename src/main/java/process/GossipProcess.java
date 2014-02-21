package process;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.GossipDao;
import beans.GossipBean;

public class GossipProcess {
	
	public List<GossipBean> getAllGossips(){
		GossipDao gd = new GossipDao();
		return gd.getAllEntries();
	}
	
	public int getRecords(){
		
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from " + dbHelper.getTable();
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}
	
	public int getRecords(String movie_id){
		
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from " + dbHelper.getTable() +" where movie_id='"+movie_id+"'";
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}

}
