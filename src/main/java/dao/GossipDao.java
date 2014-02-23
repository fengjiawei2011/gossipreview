package dao;

import helper.DBConnectionHelper;
import helper.ProjectHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.GossipBean;
import beans.PictureBean;

public class GossipDao {

	public List<GossipBean> getAllEntries() {

		List<GossipBean> gossips = null;

		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from "+dbHelper.getTable()+" limit 0, 20";
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			gossips = getGossipBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return gossips;
	}

	public List<GossipBean> getGossipBeans(ResultSet rs) {
		List<GossipBean> empty = new ArrayList<GossipBean>();
		try {
			while (rs.next()) {
				GossipBean gb= new GossipBean();
				gb.setContent(rs.getString("content"));
				gb.setDate(rs.getString("date"));
				gb.setGossip_url(rs.getString("gossip_url"));
				gb.setId(rs.getInt("id"));
				gb.setImage_add(rs.getString("image_add"));
				gb.setImage_url(rs.getString("image_url"));
				
				if(rs.getString("isInteresting") != null){
					if (rs.getString("isInteresting").equals("0")|| rs.getString("isInteresting").equals("1")) {
						gb.setIsInteresting(rs.getString("isInteresting"));
					} else {
						gb.setIsInteresting("0");
					}
				}else{
					gb.setIsInteresting("0");
				}
				
				//System.out.println("id = "+rs.getInt("id")+"   isInteresting = " + rs.getString("isInteresting"));
				//gb.setIsInteresting(rs.getString("isInteresting"));
				gb.setKey_word(rs.getString("key_word"));
				gb.setMovie_id(rs.getString("movie_id"));
				gb.setMovie_name(rs.getString("movie_name"));
				//System.out.println(rs.getString("movie_name"));
				gb.setTitle(rs.getString("title"));
				
				empty.add(gb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empty;
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
	
	public List<GossipBean> getPageEntries(int begin, int pages,  String movie_id) {
		List<GossipBean> pictures = new ArrayList<GossipBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " where  movie_id='"+movie_id+"' limit " + begin + "," + pages;
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getGossipBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public List<GossipBean> getPageEntries(int begin, int pages) {
		List<GossipBean> pictures = new ArrayList<GossipBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " limit " + begin + "," + pages;
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getGossipBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public void updateLike(Connection con, String id, int islike) {
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		// Connection con = helper.connectDatabase();
		Statement s = null;
		String sql = "update " + dbHelper.getTable() + " set isInteresting = '"
				+ islike + "'  where id = " + id;
		System.out.println(sql);
		try {
			s = con.createStatement();
			s.executeUpdate(sql);
			System.out.println("update like successful ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("update like error ");
		}

	}

}
