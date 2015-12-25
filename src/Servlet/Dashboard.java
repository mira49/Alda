package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	public static final String VUE = "/WEB-INF/Dashboard.jsp";
	public static final String VUESucess = "/WEB-INF/ConnectionDashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
}
