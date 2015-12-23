package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

@WebServlet("/dashboardannonceList")
public class AnnonceList extends AbstactQueryClass {

	public static final String VUE = "/WEB-INF/ShowAnnonce.jsp";
	public static final String VUESucess = "/WEB-INF/ConnectionDashboard.jsp";

	@EJB
	private AnnouncementDAO dao;
	
	@EJB
	private UserDAO user_dao;	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		
		List<Annonces> announcement = new ArrayList<>();
		
		announcement = dao.findAll();
		
		

		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
		String select_option = request.getParameter("select_option");
		List<Annonces> announcement = new ArrayList<>();
		User user = new User();
		String sql_request = "";
		
		if((User) session.getAttribute("user") != null){
			user.setFactor(request.getParameter("factor_lower_price") + " ; " + request.getParameter("factor_higher_price") + "; " + request.getParameter("factor_location"));
			user_dao.updateFactor(user, (User)session.getAttribute("user"));
			sql_request = sql_create_query((User)session.getAttribute("user")) + " ";
		}
		else{
			sql_request =  "select * from Annonces" + " "; 
		}
	
		
	/*	if( select_option != null){
			if (select_option.equals("lower_Price")) {
				announcement = dao.findByLowerPrice(sql_request);
			} else if (select_option.equals("higher_Price")) {
				announcement = dao.findByHigherPrice(sql_request);
			} else if (select_option.equals("Location")) {
				announcement = dao.findByPostalCode(sql_request);
			} 
		}*/
		
		session.setAttribute("annoucement_user", announcement);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}}
}

