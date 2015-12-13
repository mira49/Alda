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
import Entities.Annonces;
import Entities.User;

@WebServlet("/home_user")
public class Home_user extends HttpServlet {
	

    public static final String VUE              = "/WEB-INF/Home_user.jsp";
    public static final String VUESucess       			  = "/WEB-INF/Connection.jsp";
    
    private AnnouncementDAO dao = new AnnouncementDAO();
  
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /* Récupération de la session depuis la requête */
	        HttpSession session = request.getSession();
	        
	        if ( session.getAttribute( "user" ) == null ) {
	            /* Redirection vers la page publique */
	        	this.getServletContext().getRequestDispatcher(VUESucess).forward( request, response );
	        } else {
	        	
	        	List<Annonces> annoucements = new ArrayList<>();
	        	
	        	annoucements = dao.getAnnoucement_user((User)session.getAttribute("user"));
	        	
	        	if (!annoucements.isEmpty()){
	        		session.setAttribute("annoucement_user", annoucements);
	        	}
	        	
	            /* Affichage de la page restreinte */
	            this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	        }
	    }
	 
	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		 
		  HttpSession session = request.getSession();
	     
	        	User user_connexion=(User) session.getAttribute( "user" );
		 String idA =request.getParameter("delete");
		 if (idA != null) {
		 Long id = Long.parseLong(idA);
			 dao.delete(id);	        
			 session.invalidate();
		     HttpSession session2 = request.getSession();

			 session2.setAttribute("user", user_connexion);

       }
		 this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	 
	        
	 }
}