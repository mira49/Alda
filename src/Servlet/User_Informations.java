package Servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Entities.User;

@WebServlet("/User_Informations")
public class User_Informations extends HttpServlet{
	
	 public static final String VUE              = "/WEB-INF/User_Informations.jsp";
	 public static final String VUESucess       			  = "/WEB-INF/Connection.jsp";
	 public static final String VUEAfter              = "/WEB-INF/Home_user.jsp";
	 
	 @EJB
	 private UserDAO user_dao;
	    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if ( session.getAttribute( "user" ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + VUESucess );
        } else {
            /* Affichage de la page restreinte */
            this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
        }
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String Name = request.getParameter("Name");
		String FirstName = request.getParameter("FirstName");
		String Address = request.getParameter("Address");
		String Phone = request.getParameter("Phone");
		
		
		user_dao = new UserDAO();

		User u = new User();
		
		u.setName(Name);
		u.setFirstName(FirstName);
		u.setEmail(email);
		u.setAddress(Address);
		u.setPhone(Phone);
		
		user_dao.update_or_insert((User)session.getAttribute( "user" ),u);
		
		this.getServletContext().getRequestDispatcher(VUEAfter).forward( request, response );

	}
}
