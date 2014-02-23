package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GossipBean;
import beans.MovieBean;
import process.GossipProcess;
import process.LikeProcess;

/**
 * Servlet implementation class GossipShowServlet
 */
@WebServlet("/showgossips")
public class GossipShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int NUMBER_OF_PER_PAGE = 15;
	GossipProcess pp = new GossipProcess();
	//MovieBean current_movie = null;
	//int records_number, pages_num, current_page;

	public GossipShowServlet() {
		super();
		//setPageNum();
		//current_page = 1;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		myPost(request,response);
		/*GossipProcess gp = new GossipProcess();
		// HttpSession hs = request.getSession();
		// hs.setAttribute("gossips", gp.getAllGossips());

		String operation = request.getParameter("operation");
		List<MovieBean> movies = pp.getMovies();
		List<GossipBean> gossips = null;
		HttpSession hs = request.getSession();

		if (operation != null && operation.equals("next")) {
			if (GoodOrNotServlet.memory != null) {
				GoodOrNotServlet.memory.clear();
			}

			current_page++;

		} else if (operation != null && operation.equals("prev")) {
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.memory.clear();
			}
			current_page--;
		} else if (operation != null && operation.equals("saveNext")) {
			current_page++;
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.lp.updateByMap(GoodOrNotServlet.memory);
				GoodOrNotServlet.memory.clear();
			}
		}
		if (operation != null && operation.equals("chooseMovie")) {
			String movie_id = request.getParameter("movie_id");
			this.current_page = 1;
			setPageNum(movie_id);
			for (MovieBean m : movies) {
				if (movie_id.equals(m.getMovie_id())) {
					current_movie = m;
					hs.setAttribute("current_movie", m);
					// request.setAttribute("current_movie", current_movie);
					// hs.setAttribute("movie_name", m.getMovie_name());
					break;
				}
			}
			gossips = pp.getGossipsByPage(current_page, NUMBER_OF_PER_PAGE,
					current_movie.getMovie_id());
		}

		if (current_movie != null) {
			gossips = pp.getGossipsByPage(current_page, NUMBER_OF_PER_PAGE,
					current_movie.getMovie_id());
		} else {
			gossips = pp.getGossipsByPage(current_page, NUMBER_OF_PER_PAGE);
		}
		// request.setAttribute("movies", movies);
		// request.setAttribute("gossips", gossips);
		// request.getRequestDispatcher("main.jsp?pages_num="+pages_num+"&current_page="+current_page).forward(request,response);
		hs.setAttribute("movies", movies);
		hs.setAttribute("gossips", gossips);
		response.sendRedirect("main.jsp?pages_num=" + pages_num
				+ "&current_page=" + current_page);*/
	}

	public void myPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GossipBean> gossips = null;
		String url = "main.jsp";
		String operation = request.getParameter("operation");
		HttpSession hs = request.getSession();
		LikeProcess lp = new LikeProcess();
		
		if(operation != null && operation.equals("next")) {
			
			if (request.getSession().getAttribute("memory") != null) {
				((Map<String, Object>)request.getSession().getAttribute("memory")).clear();
			}
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			hs.setAttribute("current_page",++currentPage);
			//get next page gossip
			MovieBean mb = (MovieBean)hs.getAttribute("current_movie");
			if(mb != null){
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE,mb.getMovie_id());
			}else{
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE);
			}
			
		}else if (operation != null && operation.equals("prev")) {

			if (request.getSession().getAttribute("memory") != null) {
				((Map<String, Object>)request.getSession().getAttribute("memory")).clear();
			}
			
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			hs.setAttribute("current_page",--currentPage );
			//get prev page gossip
			MovieBean mb = (MovieBean)hs.getAttribute("current_movie");
			if(mb != null){
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE,mb.getMovie_id());
			}else{
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE);
			}
			
		}else if (operation != null && operation.equals("saveNext")) {
			
			Map<String, Object> memory =(Map<String, Object>) request.getSession().getAttribute("memory");
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			hs.setAttribute("current_page",++currentPage);
			if (memory != null && ! memory.isEmpty()) {
				lp.updateByMap(memory);
				memory.clear();
			}
			
			MovieBean mb = (MovieBean)hs.getAttribute("current_movie");
			if(mb != null){
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE,mb.getMovie_id());
			}else{
				gossips = pp.getGossipsByPage(currentPage, NUMBER_OF_PER_PAGE);
			}
			
		}else if(operation != null && operation.equals("showAll")){
			
			Map<String, Object> memory = new LinkedHashMap<String, Object>();
			
			List<MovieBean> movies = pp.getMovies();
			hs.setAttribute("movies",movies);
			hs.setAttribute("current_page", 1);
			hs.setAttribute("pages_num", getPages());
			hs.setAttribute("memory", memory);
			gossips = pp.getGossipsByPage(1, NUMBER_OF_PER_PAGE);
			
		}else if (operation != null && operation.equals("chooseMovie")) {
			hs.setAttribute("current_page",1);
			String movie_id = request.getParameter("movie_id");
			hs.setAttribute("pages_num", getPages(movie_id));
			MovieBean cm = getCurrentMovie(movie_id, (List<MovieBean>)hs.getAttribute("movies"));
			hs.setAttribute("current_movie", cm );
			
			gossips = pp.getGossipsByPage(1, NUMBER_OF_PER_PAGE, movie_id);
			//get movie related gossip 
		}
		request.setAttribute("gossips", gossips);
		request.getRequestDispatcher(url).forward(request,response);
	}

//	public void setPageNum() {
//		records_number = pp.getRecords();
//		set(records_number);
//	}
	
	
	public MovieBean getCurrentMovie(String movie_id , List<MovieBean> movies){
		MovieBean currentM = null;
		for (MovieBean m : movies) {
			if (movie_id.equals(m.getMovie_id())) {
				currentM = m;break;
			}
		}
		
		return currentM;
	}
	public int getPages(String movie_id){
		int recordsNum = pp.getRecords(movie_id);
		int pagesNum = 0;
		if (recordsNum < NUMBER_OF_PER_PAGE) {
			pagesNum = 1;
		} else {
			if (recordsNum % NUMBER_OF_PER_PAGE == 0) {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE;
			} else {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE + 1;
			}

		}
		return pagesNum;
	}
	
	public int getPages(){
		int recordsNum = pp.getRecords();
		int pagesNum = 0;
		if (recordsNum < NUMBER_OF_PER_PAGE) {
			pagesNum = 1;
		} else {
			if (recordsNum % NUMBER_OF_PER_PAGE == 0) {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE;
			} else {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE + 1;
			}

		}
		return pagesNum;
	}

//	public void setPageNum(String movie_id) {
//		records_number = pp.getRecords(movie_id);
//		set(records_number);
//	}
//
//	public void set(int records_number) {
//
//		if (records_number < NUMBER_OF_PER_PAGE) {
//			pages_num = 1;
//		} else {
//			if (records_number % NUMBER_OF_PER_PAGE == 0) {
//				pages_num = records_number / NUMBER_OF_PER_PAGE;
//			} else {
//				pages_num = records_number / NUMBER_OF_PER_PAGE + 1;
//			}
//
//		}
//	}

}
