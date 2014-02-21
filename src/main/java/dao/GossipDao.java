package dao;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.GossipBean;

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
				gb.setIsInteresting(rs.getString("isInteresting"));
				gb.setKey_word(rs.getString("key_word"));
				gb.setMovie_id(rs.getString("movie_id"));
				gb.setMovie_name(rs.getString("movie_name"));
				gb.setTitle(rs.getString("title"));
				
				empty.add(gb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empty;
	}

}
