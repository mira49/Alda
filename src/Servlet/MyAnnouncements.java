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

import org.apache.commons.lang.StringUtils;

import DAO.AnnouncementDAO;
import DAO.MessageDAO;
import DAO.UserDAO;
import Entities.Annonces;
import Entities.Messages;
import Entities.User;

@WebServlet("/myAnnouncements")
public class MyAnnouncements extends HttpServlet {

	public static final String VUE = "/WEB-INF/MyAnnouncements.jsp";
	public static final String VUE_Update = "/WEB-INF/Add_Announcement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	private static final String VUE_VISU = "/WEB-INF/visualisation.jsp";

	@EJB
	private AnnouncementDAO dao;

	@EJB
	private UserDAO user_dao;

	@EJB
	private MessageDAO message_dao;

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
			sendMessages(dao.setAnnounceSold(request.getParameter("sold")));
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

	public void sendMessages(Annonces annonce) {

		String[] favorites = annonce.getFavorite().split(";");
		for (String f : favorites) {
			if (!(StringUtils.isBlank(f))) {
				Messages m = new Messages();
				m.setMessage("L'annonce de " + annonce.getUser().getName() + ": " + annonce.getName() + " � �t� vendu");
				m.setReceiver_message(f);
				m.setSender_message("No reply");
				m.setNotification(1);
				message_dao.create(m);
			}
		}
	}
}
