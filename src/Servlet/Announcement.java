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
public class Announcement extends AbstactQueryClass {

	public static final String VUE = "/WEB-INF/User_announcement.jsp";

	@EJB
	private AnnouncementDAO dao;
	@EJB
	private UserDAO user_dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String select_option = request.getParameter("select_option");
		String sql_request = null;
		String sql_request2 = null;

		User user = user_dao.findByUser((User) session.getAttribute("user"));
	
		List<Annonces> announcement = new ArrayList<>();
		List<Annonces> announcement2 = new ArrayList<>();

	
			sql_request = sql_create_query(user) + " ";
String factor[] = new String[3];
			
			if(user_dao.findFactorBeta(user).equals(" ; ; ")) 	{ factor=null;}
			else{
			factor = user_dao.findFactor(user);
			}
			request.setAttribute("factor", factor);

		
			sql_request2 = "SELECT u FROM Annonces u where u.sold = 0";
		
		List<Boolean> liste = new ArrayList<>();
		List<Boolean> liste2 = new ArrayList<>();

		announcement = dao.findByFactor(sql_request);
		announcement2 = dao.findByFactor(sql_request2);

		for(Annonces annonce: announcement)
		{
			String parameter;
			parameter= user.getEmail()+";"+annonce.getId();
			liste.add(dao.findFavorite(parameter));
		}
		
		for(Annonces annonce: announcement2)
		{
			String parameter;
			parameter= user.getEmail()+";"+annonce.getId();
			liste2.add(dao.findFavorite(parameter));
		}
		request.setAttribute("liste", liste);
		request.setAttribute("liste2", liste2);

		request.setAttribute("annoucement_user_factor", announcement);
		request.setAttribute("annoucement_user", announcement2);

		

		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String select_option = request.getParameter("select_option");
		List<Annonces> announcement = new ArrayList<>();
		List<Annonces> announcement2 = new ArrayList<>();

		User user = user_dao.findByUser((User) session.getAttribute("user"));
		User user_tmp = new User();
		String factor[] = new String[3];

		String sql_request = "SELECT u FROM Annonces u where u.sold = 0";

		if (request.getParameter("factor_lower_price") != null || request.getParameter("factor_higher_price") != null
				|| request.getParameter("factor_location") != null) {
			user_tmp.setFactor(request.getParameter("factor_lower_price") + " ; "
					+ request.getParameter("factor_higher_price") + "; " + request.getParameter("factor_location"));
			user = user_dao.updateFactor(user_tmp, user);
			sql_request = sql_create_query(user) + " ";
		} else {
			sql_request = sql_create_query(user);
		}

		if (request.getParameter("favorite") != null) {
			
			dao.addToFavoriteList(request.getParameter("favorite"));
			

		}
		
		if (request.getParameter("favoriteRemove") != null) {
	
			dao.removeToFavoriteList(request.getParameter("favoriteRemove"));
			

		}
		
		String sql_request2 = "SELECT u FROM Annonces u where u.sold = 0";

		if (select_option != null) {
			if (select_option.equals("lower_Price")) {
				sql_request2 = dao.findByLowerPrice(sql_request2);
			} else if (select_option.equals("higher_Price")) {
				sql_request2 = dao.findByHigherPrice(sql_request2);
			} else if (select_option.equals("Location")) {
				sql_request2 = dao.findByPostalCode(sql_request2);
			}
		}

		announcement = dao.findByFactor(sql_request);
		if(user_dao.findFactorBeta(user).equals(" ; ; ")) 	{ factor=null;}
		else{
		factor = user_dao.findFactor(user);
		}
		List<Boolean> liste = new ArrayList<>();
		List<Boolean> liste2 = new ArrayList<>();

		for(Annonces annonce: announcement)
		{
			String parameter;
			parameter= user.getEmail()+";"+annonce.getId();
			liste.add(dao.findFavorite(parameter));
		}
		
	
		
		announcement2 = dao.findByFactor(sql_request2);
		for(Annonces annonce2: announcement2)
		{
			String parameter;
			parameter= user.getEmail()+";"+annonce2.getId();
			liste2.add(dao.findFavorite(parameter));
		}
		request.setAttribute("liste", liste);
		request.setAttribute("liste2", liste2);
		request.setAttribute("factor", factor);
		request.setAttribute("annoucement_user_factor", announcement);

		request.setAttribute("annoucement_user", announcement2);
		session.setAttribute("user", user);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
