package servlets;

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

import ejb.AnnouncementDAO;
import ejb.AnnouncementItf;
import ejb.MessageDAO;
import ejb.MessageItf;
import ejb.UserDAO;
import ejb.UserItf;
import entities.Annonces;
import entities.User;

@WebServlet("/contact")
public class Contact extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/contact.jsp";
	public static final String VUE_retour = "/WEB-INF/Home_user.jsp";
	private static final String VUE_VISU = "/WEB-INF/visualisation.jsp";


	@EJB
	AnnouncementItf dao;

	@EJB
	UserItf user_dao;

	@EJB
	MessageItf msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		if (request.getParameter("view") != null){
			Annonces announce = dao.findById(request.getParameter("view"));
			
			request.setAttribute("current_annonce", announce);
			this.getServletContext().getRequestDispatcher(VUE_VISU).forward(request, response);
		}else{
			String announce_contact = request.getParameter("contact");
		
		Annonces annonces = dao.findById(announce_contact);
		String con = userConnection(annonces);
		
		if (con.equals("con")){
			request.setAttribute("connexion", con);
		}
		
		request.setAttribute("current_announce", announce_contact);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String messages = request.getParameter("Message");
		Annonces announce = dao.findById(request.getParameter("annonces"));
		User user = user_dao.findByUser((User) session.getAttribute("user"));
		msg.create(user, messages, announce);

		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}

		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE_retour).forward(request, response);
	}
	
	public String userConnection(Annonces annonce) {
			String con = "";
			 if (annonce.getUser().getDate_connexion().compareTo(annonce.getUser().getDate_deconnection()) > 0){
				 con = "con";
			 }
			return con;
	}
}
