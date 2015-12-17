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

@WebServlet("/announcement")
public class Announcement extends HttpServlet {

	public static final String VUE = "/WEB-INF/User_announcement.jsp";

	@EJB
	private AnnouncementDAO dao = new AnnouncementDAO();
	@EJB
	private UserDAO user_dao = new UserDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String select_option = request.getParameter("select_option");
		String sql_request = null;
		
		List<Annonces> announcement = new ArrayList<>();
		
		if((User)session.getAttribute("user") != null){
			User user = user_dao.findByUser((User)session.getAttribute("user"));
			sql_request = user_dao.sql_create_query(user) + " ";
			String factor[] = new String[3];
			factor = user_dao.findFactor(user);
			request.setAttribute("factor", factor);
		}
		else{
			sql_request = "SELECT u FROM Annonces u where u.sold = 0";
			System.out.println("sql_request:" + sql_request);
		}
		
		
		announcement = dao.findByFactor(sql_request);
		
		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String select_option = request.getParameter("select_option");
		List<Annonces> announcement = new ArrayList<>();
		User user = new User();
		String sql_request = "";
		
		if((User) session.getAttribute("user") != null){
			user.setFactor(request.getParameter("factor_lower_price") + " ; " + request.getParameter("factor_higher_price") + "; " + request.getParameter("factor_location"));
			user_dao.updateFactor(user, (User)session.getAttribute("user"));
			sql_request = user_dao.sql_create_query((User)session.getAttribute("user")) + " ";
		}
		else{
			sql_request =  "select * from Annonces where sold = 0" + " "; 
		}
		
		if (request.getParameter("favorite") != null){
			dao.addToFavoriteList(request.getParameter("favorite"));
		}
		
		if( select_option != null){
			if (select_option.equals("lower_Price")) {
				announcement = dao.findByLowerPrice(sql_request);
			} else if (select_option.equals("higher_Price")) {
				announcement = dao.findByHigherPrice(sql_request);
			} else if (select_option.equals("Location")) {
				announcement = dao.findByPostalCode(sql_request);
			} 
		}
		
		session.setAttribute("annoucement_user", announcement);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
