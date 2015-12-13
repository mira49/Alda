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

@WebServlet("/announcement")
public class Announcement extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/User_announcement.jsp";
	
	@EJB
	AnnouncementDAO dao = new AnnouncementDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	HttpSession session = request.getSession();
		 	
		 	String select_option = request.getParameter("select_option");
		 	System.out.println(select_option);
			List<Annonces> announcement = new ArrayList<>();
			announcement = dao.findAll();
			
			session.setAttribute("annoucement_user", announcement);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	 	HttpSession session = request.getSession();
	 	
	 	String select_option = request.getParameter("select_option");
	 	System.out.println(select_option);
		List<Annonces> announcement = new ArrayList<>();
		
		if (select_option.equals("lower_Price")){
			announcement = dao.findByLowerPrice();
		}
		else if(select_option.equals("higher_Price")){
			announcement = dao.findByHigherPrice();
		}
		else if(select_option.equals("Location")){
			announcement = dao.findByPostalCode();
		}
		else{
			announcement = dao.findAll();
		}
	
		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
}
}
