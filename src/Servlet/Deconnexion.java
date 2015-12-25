package Servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Entities.User;

@WebServlet("/deconnection")
public class Deconnexion extends HttpServlet {
	public static final String VUE = "/WEB-INF/Connection.jsp";

	@EJB
	UserDAO user_dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération et destruction de la session en cours */
		HttpSession session = request.getSession();
		updateDeconnexion((User) session.getAttribute("user"));
		session.invalidate();

		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void updateDeconnexion(User user) {
		String format = "dd/MM/yy H:mm:ss";
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
		java.util.Date dt = new java.util.Date();
		user_dao.updateDateDeco(user, dt);
	}
}
