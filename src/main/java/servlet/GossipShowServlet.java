package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GossipBean;
import beans.MovieBean;
import process.GossipProcess;

/**
 * Servlet implementation class GossipShowServlet
 */
@WebServlet("/showgossips")
public class GossipShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int NUMBER_OF_PER_PAGE = 10;
	GossipProcess pp = new GossipProcess();
	MovieBean current_movie = null;
	static int records_number, pages_num, current_page;

    public GossipShowServlet() {
        super();
        setPageNum();
        current_page = 1;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GossipProcess gp = new GossipProcess();
		//HttpSession hs = request.getSession();
		//hs.setAttribute("gossips", gp.getAllGossips());
		String movie_id = request.getParameter("movie_id");
		String operation = request.getParameter("operation");
		List<MovieBean>  movies = pp.getMovies();
		List<GossipBean> gossips = null;
		HttpSession hs = request.getSession();
		
		if (operation != null && operation.equals("next")) {
			if (GoodOrNotServlet.memory != null) {
				GoodOrNotServlet.memory.clear();
			}

			GossipShowServlet.current_page++;

		} else if (operation != null && operation.equals("prev")) {
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.memory.clear();
			}
			GossipShowServlet.current_page--;
		} else if (operation != null && operation.equals("saveNext")) {
			GossipShowServlet.current_page++;
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.lp.updateByMap(GoodOrNotServlet.memory);
				GoodOrNotServlet.memory.clear();
			}

		}
		if (movie_id != null && operation.equals("chooseMovie")) {
			this.current_page = 1;
			setPageNum(movie_id);
			for (MovieBean m : movies) {
				if (movie_id.equals(m.getMovie_id())) {
					current_movie = m;
					hs.setAttribute("current_movie", m);
					//request.setAttribute("current_movie", current_movie);
					//hs.setAttribute("movie_name", m.getMovie_name());
					break;
				}
			}
			gossips = pp.getGossipsByPage(current_page, NUMBER_OF_PER_PAGE,
					current_movie.getMovie_id());

		} else {
			gossips = pp.getGossipsByPage(current_page, NUMBER_OF_PER_PAGE);
		}
		request.setAttribute("movies", movies);
		request.setAttribute("gossips", gossips);
		request.getRequestDispatcher("main.jsp?pages_num="+pages_num+"&current_page="+current_page).forward(request,response);
	}
	
	
	public void setPageNum() {
		records_number = pp.getRecords();
		set(records_number);
	}

	public void setPageNum(String movie_id) {
		records_number = pp.getRecords(movie_id);
		set(records_number);
	}

	public void set(int records_number) {

		if (records_number < NUMBER_OF_PER_PAGE) {
			pages_num = 1;
		} else {
			if (records_number % NUMBER_OF_PER_PAGE == 0) {
				pages_num = records_number / NUMBER_OF_PER_PAGE;
			} else {
				pages_num = records_number / NUMBER_OF_PER_PAGE + 1;
			}

		}
	}


}
