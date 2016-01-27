package servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.UserDAO;
import ejb.UserItf;
import entities.User;

@WebServlet("/deconnection")
public class Deconnexion extends HttpServlet {
	public static final String VUE = "/WEB-INF/Connection.jsp";

	@EJB
	UserItf user_dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* R�cup�ration et destruction de la session en cours */
		HttpSession session = request.getSession();
		updateDeconnexion((User) session.getAttribute("user"));
		session.invalidate();

		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void updateDeconnexion(User user) {
		System.out.println("le user est" + user.getEmail());
		Calendar calendar = Calendar.getInstance();
		Date dt =  calendar.getTime();
		user_dao.updateDateDeco(user, dt);
	}
}
