package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AdministratorDAO;
import DAO.AnnouncementDAO;
import Entities.Administrator;
import Entities.Annonces;
import Entities.User;

@WebServlet("/dashboardconnection")
public class ConnectionDashboard extends HttpServlet {

	public static final String VUE = "/WEB-INF/ConnectionDashboard.jsp";
	public static final String VUESucess = "/WEB-INF/Dashboard.jsp";

	@EJB
	AdministratorDAO dao;

	boolean instance = false;
	Administrator admin = new Administrator();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = request.getParameter("Pseudo");
		String password = request.getParameter("Password");

		if (pseudo.equals(password) && pseudo.equals("admin")) {

			if (!instance) {
				admin.setPassword(password);
				admin.setPseudo(pseudo);
				dao.create(admin);
				instance = true;
			}
		}

		Administrator user_connexion = dao.findAdministrator(pseudo, password);

		if (user_connexion != null) {
			session.setAttribute("admin", user_connexion);

			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			String error = "Bad email or bad password";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}
