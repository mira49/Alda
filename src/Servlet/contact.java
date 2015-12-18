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

import DAO.AnnouncementDAO;
import DAO.MessageDAO;
import DAO.UserDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/contact")
public class contact extends HttpServlet{
	
	public static final String VUE = "/WEB-INF/contact.jsp";
	public static final String VUE_retour = "/WEB-INF/Home_user.jsp";
	
	@EJB
	AnnouncementDAO dao = new AnnouncementDAO();
	
	@EJB
	UserDAO user_dao = new UserDAO();
	
	@EJB
	MessageDAO msg = new MessageDAO();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String annonce = request.getParameter("contact");
		
		
		Annonces annonces = dao.findById(annonce);
		System.out.println("Le propriétaire de l'annonce est" + annonces.getUser().getName());
		
		request.setAttribute("current_announce", annonces);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String messages = request.getParameter("Message");
		
		Annonces annonce = dao.findById(request.getParameter("annonces"));
		
		User user = user_dao.findByUser((User)session.getAttribute("user"));
		
		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}
		
		msg.create(user, messages, annonce);
		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE_retour).forward(request, response);
	}
}
