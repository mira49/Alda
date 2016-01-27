package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import ejb.AnnouncementDAO;
import ejb.AnnouncementItf;
import ejb.MessageDAO;
import ejb.MessageItf;
import ejb.UserDAO;
import ejb.UserItf;
import entities.Annonces;
import entities.Messages;
import entities.User;

@WebServlet("/myAnnouncements")
public class AnnouncementUser extends HttpServlet {

	public static final String VUE = "/WEB-INF/AnnouncementUser.jsp";
	public static final String VUE_Update = "/WEB-INF/AddAnnouncement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	private static final String VUE_VISU = "/WEB-INF/Visualisation.jsp";

	@EJB
	private AnnouncementItf dao;

	@EJB
	private UserItf user_dao;

	@EJB
	private MessageItf message_dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = user_dao.findByUser((User) session.getAttribute("user"));
		
		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}
		
		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String idA = request.getParameter("delete");
		User user = user_dao.findByUser((User) session.getAttribute("user"));
		List<Annonces> annoucements = new ArrayList<>();
		
		if (idA != null) {
			Long id = Long.parseLong(idA);
			dao.delete(id);
		}

		if (request.getParameter("sold") != null) {
			sendMessages(dao.setAnnounceSold(request.getParameter("sold")), user);
		}
		
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}

		session.setAttribute("user", user);
		
		if (request.getParameter("edit") != null){
			Annonces annonce = dao.findById(request.getParameter("edit"));
			
			request.setAttribute("current_annonce", annonce);
			this.getServletContext().getRequestDispatcher(VUE_Update).forward(request, response);
		}
		else
			if (request.getParameter("view") != null){
				Annonces annonce = dao.findById(request.getParameter("view"));
				
				request.setAttribute("current_annonce", annonce);
				this.getServletContext().getRequestDispatcher(VUE_VISU).forward(request, response);
			}
			else{
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public void sendMessages(Annonces annonce, User utmp) {
		
		List <User> tmp = dao.findFavorite(annonce.getId());
			for (User u : tmp) {
				Messages m = new Messages("L'annonce de " + utmp.getName() + " " +utmp.getFirstName() +": " + annonce.getName() + " est vendu","No reply", u , 1);
				message_dao.create(m);
		}
		dao.resetFavorite(annonce.getId());
	}
}
