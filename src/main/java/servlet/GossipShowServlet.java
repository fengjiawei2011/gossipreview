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
       

    public GossipShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GossipProcess gp = new GossipProcess();
		HttpSession hs = request.getSession();
		hs.setAttribute("gossips", gp.getAllGossips());
		response.sendRedirect("main.jsp");
	}

}
