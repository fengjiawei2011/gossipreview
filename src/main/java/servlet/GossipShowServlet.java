package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import process.GossipProcess;

/**
 * Servlet implementation class GossipShowServlet
 */
@WebServlet("/showgossips")
public class GossipShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final int NUMBER_OF_PER_PAGE = 15;
	GossipProcess pp = new GossipProcess();

	int records_number, pages_number, current_page;

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
		request.setAttribute("gossips", gp.getAllGossips());
		//response.sendRedirect("main.jsp");
		request.getRequestDispatcher("main.jsp?pages_number="+pages_number+"&current_page="+current_page).forward(request,response);
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
			pages_number = 1;
		} else {
			if (records_number % NUMBER_OF_PER_PAGE == 0) {
				pages_number = records_number / NUMBER_OF_PER_PAGE;
			} else {
				pages_number = records_number / NUMBER_OF_PER_PAGE + 1;
			}

		}
	}


}
