package process;

import helper.DBConnectionHelper;
import helper.ProjectHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import dao.GossipDao;
import dao.PicDao;

public class LikeProcess {
	GossipDao gd = new GossipDao();
	public void updateByMap(Map<String, Object> map){
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator<Entry<String, Object>> i = set.iterator();
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		//PicDao p_dao = new PicDao();
		
		while(i.hasNext()){
			Map.Entry<String, Object> me= (Map.Entry<String, Object>)i.next();
			String key_id = me.getKey();
			int value_isLike = ((Integer)me.getValue()).intValue();
			gd.updateLike(con, key_id, value_isLike);
		}
		
		DBConnectionHelper.closeConnection(con);
		
	}
	
	public void update(String id, int isLike ){
		Connection con = new DBConnectionHelper().connectDatabase();
		gd.updateLike(con, id, isLike);
		DBConnectionHelper.closeConnection(con);
	}

}
