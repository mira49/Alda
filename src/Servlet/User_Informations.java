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

@WebServlet("/user_Informations")
public class User_Informations extends HttpServlet{
	
	 public static final String VUE              = "/WEB-INF/User_Informations.jsp";
	 public static final String VUESucess       			  = "/WEB-INF/Connection.jsp";
	 public static final String VUEAfter              = "/WEB-INF/Home_user.jsp";
	 
	 @EJB
	 private UserDAO user_dao = new UserDAO();
	 
	 @EJB
	 private AnnouncementDAO dao;
	 
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

       
            /* Affichage de la page restreinte */
            this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
        
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User u = new User();
		
		u.setName(request.getParameter("Name"));
		u.setFirstName(request.getParameter("FirstName"));
		u.setEmail(request.getParameter("email"));
		u.setAddress(request.getParameter("Address"));
		u.setPhone(request.getParameter("Phone"));
		
		u = user_dao.update_or_insert((User)session.getAttribute( "user" ),u);
		
		List<Annonces> annoucements = new ArrayList<>();
		annoucements = dao.getAnnoucement_user(u);
		if (!annoucements.isEmpty()) {
			request.setAttribute("annoucement_user", annoucements);
		}
		
		session.setAttribute("user", u);
		
		this.getServletContext().getRequestDispatcher(VUEAfter).forward( request, response );

	}
}
