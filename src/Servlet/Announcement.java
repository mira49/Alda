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
		System.out.println("select_option :" + select_option);
		
		
		List<Annonces> announcement = new ArrayList<>();
		String sql_request = user_dao.sql_create_query((User)session.getAttribute("user")) + " ";
		System.out.println("sql_request:" + sql_request);
		announcement = dao.findByFactor(sql_request);
		
		String factor[] = new String[3];
		factor = user_dao.findFactor((User) session.getAttribute("user"));
		
		session.setAttribute("factor", factor);

		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user_connexion = (User) session.getAttribute("user");
		String select_option = request.getParameter("select_option");
		List<Annonces> announcement = new ArrayList<>();
		announcement = dao.findAll();

		User user = new User();
		
		user.setFactor(request.getParameter("factor_lower_price") + " ; " + request.getParameter("factor_higher_price") + "; " + request.getParameter("factor_location"));
		user_dao.updateFactor(user, (User)session.getAttribute("user"));
		
		String sql_request = user_dao.sql_create_query((User)session.getAttribute("user")) + " ";
		
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
		
		session.invalidate();
		HttpSession session2 = request.getSession();
		session2.setAttribute("user", user_connexion);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
