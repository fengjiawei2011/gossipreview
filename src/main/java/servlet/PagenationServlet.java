package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.GossipProcess;

@WebServlet("/pagenation")
public class PagenationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GossipProcess pp = new GossipProcess();
	public PagenationServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		String operation = request.getParameter("operation");
//		int current_page = Integer.parseInt(request.getParameter("current_page"));
//		String pages_num = request.getParameter("pages_num");
//		
//		if (operation != null && operation.equals("next")) {
//			if (GoodOrNotServlet.memory != null) {
//				GoodOrNotServlet.memory.clear();
//			}
//
//			current_page++;
//
//		} else if (operation != null && operation.equals("prev")) {
//			if (GoodOrNotServlet.memory != null
//					&& !GoodOrNotServlet.memory.isEmpty()) {
//				GoodOrNotServlet.memory.clear();
//			}
//			current_page = current_page--;
//		} else if (operation != null && operation.equals("saveNext")) {
//			current_page = current_page++;
//			if (GoodOrNotServlet.memory != null
//					&& !GoodOrNotServlet.memory.isEmpty()) {
//				GoodOrNotServlet.lp.updateByMap(GoodOrNotServlet.memory);
//				GoodOrNotServlet.memory.clear();
//			}
//
//		}
//		request.setAttribute("gossips", pp.getGossipsByPage(current_page, GossipShowServlet.NUMBER_OF_PER_PAGE));
//		//response.sendRedirect("main.jsp");
//		request.getRequestDispatcher("/showgossips").forward(request,response);
		
	}

}
