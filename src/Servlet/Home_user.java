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
import DAO.UserDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/home_user")
public class Home_user extends HttpServlet {

	public static final String VUE = "/WEB-INF/Home_user.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";

	@EJB
	private AnnouncementDAO dao = new AnnouncementDAO();
	
	@EJB
	private UserDAO user_dao = new UserDAO();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* R�cup�ration de la session depuis la requ�te */
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
			dao.setAnnounceSold(request.getParameter("sold"));
		}
		
		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(user);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}
		
		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}
}