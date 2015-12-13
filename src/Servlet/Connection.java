package Servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
import EntityManagerF.SingletonEntityManagerFactory;

@WebServlet("/connexion")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    public static final String VUESucess              = "/WEB-INF/Home_user.jsp";
    public static final String VUE       			  = "/WEB-INF/Connection.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDAO user = new UserDAO();
		User user_connexion = user.findUser(email, password);
		
		
	
		if (user_connexion!=null){
			session.setAttribute("user", user_connexion);
			AnnouncementDAO dao = new AnnouncementDAO();
        	List<Annonces> annoucements = new ArrayList<>();
        	
        	annoucements = dao.getAnnoucement_user((User)session.getAttribute("user"));
        	
        	if (!annoucements.isEmpty()){
        		System.out.println(annoucements.get(0).getName());
        		session.setAttribute("annoucement_user", annoucements);
        	}

			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		}
		else{
			String error = "Bad email or bad password";
			request.setAttribute("error", error);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}

}
