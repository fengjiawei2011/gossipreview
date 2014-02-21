package process;

import java.util.List;

import dao.GossipDao;
import beans.GossipBean;

public class GossipProcess {
	
	public List<GossipBean> getAllGossips(){
		GossipDao gd = new GossipDao();
		return gd.getAllEntries();
	}

}
