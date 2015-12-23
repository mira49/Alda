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
	public static final String VUESucess = "/WEB-INF/Connection.jsp";

	@EJB
	private AnnouncementDAO dao;
	
	@EJB
	private UserDAO user_dao;
	
	@EJB
	private MessageDAO message_dao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			
			User user =user_dao.findByUser((User)session.getAttribute("user"));
			List<Annonces> annoucements = new ArrayList<>();
			annoucements = dao.getAnnoucement_user(user);
			if (!annoucements.isEmpty()) {
				request.setAttribute("annoucement_user", annoucements);
			}
			session.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		
		String idA = request.getParameter("delete");
		
		User user =user_dao.findByUser((User)session.getAttribute("user"));
		
		
		if (idA != null) {
			Long id = Long.parseLong(idA);
			dao.delete(id);	
		}
		
		if(request.getParameter("sold")!= null){
			sendMessages(dao.setAnnounceSold(request.getParameter("sold")));
		}
		
		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}
		
		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}
	
	public void sendMessages(Annonces annonce){
		
		String[] favorites = annonce.getFavorite().split(";");
			for(String f: favorites){
				if (!(StringUtils.isBlank(f))){
					Messages m = new Messages();
					m.setMessage("L'annonce de " + annonce.getUser().getName() + ": " + annonce.getName()+ " à été vendu"  );
					m.setReceiver_message(f);
					m.setSender_message("No reply");
					m.setNotification(1);
					message_dao.create(m);
				}
		}
	}
}
