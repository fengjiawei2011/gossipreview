package process;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.GossipDao;
import dao.MovieDao;
import beans.GossipBean;
import beans.MovieBean;
import beans.PictureBean;

public class GossipProcess {
	GossipDao gd = new GossipDao();
	public List<GossipBean> getAllGossips(){
		
		return gd.getAllEntries();
	}
	
	public List<GossipBean> getGossipsByPage(int whichPage, int pages,
			String movie_id) {

		int begin = pages * (whichPage - 1);
		return gd.getPageEntries(begin, pages, movie_id);
	}

	public List<GossipBean> getGossipsByPage(int whichPage, int pages) {

		int begin = pages * (whichPage - 1);
		return gd.getPageEntries(begin, pages);
	}
	
	public int getRecords(){
		
		return gd.getRecords();
	}
	
	public int getRecords(String movie_id){
		
		return gd.getRecords(movie_id);
	}
	
	MovieDao md = new MovieDao();
	DBConnectionHelper helper = new DBConnectionHelper();
	public List<MovieBean> getMovies(){
		Connection con = helper.connectDatabase();
		List<MovieBean> movies = md.getMovies(con);
		DBConnectionHelper.closeConnection(con);
		return movies;
	}

}
