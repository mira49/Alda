package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AnnouncementDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/home_user")
public class Home_user extends HttpServlet {
	

    public static final String VUE              = "/WEB-INF/Home_user.jsp";
    public static final String VUESucess       			  = "/WEB-INF/Connection.jsp";

    
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /* Récupération de la session depuis la requête */
	        HttpSession session = request.getSession();
	        
	        if ( session.getAttribute( "user" ) == null ) {
	            /* Redirection vers la page publique */
	        	this.getServletContext().getRequestDispatcher(VUESucess).forward( request, response );
	        } else {
	        	AnnouncementDAO dao = new AnnouncementDAO();
	        	List<Annonces> annoucements = new ArrayList<>();
	        	annoucements = dao.getAnnoucement_user((User)session.getAttribute("user"));
	        	
	        	session.setAttribute("annoucement_user", annoucements);
	        	
	        	System.out.println("hello bro" + annoucements.get(0).getName());
	        	
	            /* Affichage de la page restreinte */
	            this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	        }
	    }
}
