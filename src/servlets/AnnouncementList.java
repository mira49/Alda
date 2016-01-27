package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Annonces;
import entities.User;

@WebServlet("/announcement")
public class AnnouncementList extends AbstactQueryClass {

	public static final String VUE = "/WEB-INF/AnnouncementList.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = user_dao.findByUser((User) session.getAttribute("user"));
		
		List<Annonces> announcement = getAnnoncesList(sql_create_query(user) + " ");
		List<Annonces> announcement2 = getAnnoncesList("SELECT u FROM Annonces u where u.sold = 0");

		String factor[] = new String[3];

		if (user_dao.findFactorBeta(user).equals(" ; ; ")) {
			factor = null;
		} else {
			factor = user_dao.findFactor(user);
		}
		request.setAttribute("factor", factor);

		List<Boolean> list = getBooleanList(announcement, user);
		List<Boolean> list2 = getBooleanList(announcement2, user);

		request.setAttribute("liste", list);
		request.setAttribute("liste2", list2);

		request.setAttribute("annoucement_user_factor", announcement);
		request.setAttribute("annoucement_user", announcement2);

		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String select_option = request.getParameter("select_option");
	

		User user = user_dao.findByUser((User) session.getAttribute("user"));
		User user_tmp = new User();
		String factor[] = new String[3];

		String sql_request = "SELECT u FROM Annonces u where u.sold = 0";

		if (request.getParameter("factor_lower_price") != null || request.getParameter("factor_higher_price") != null
				|| request.getParameter("factor_location") != null) {
			user_tmp.setFactor(request.getParameter("factor_lower_price") + " ; "
					+ request.getParameter("factor_higher_price") + " ; " + request.getParameter("factor_location"));
			user = user_dao.updateFactor(user_tmp, user);
			sql_request = sql_create_query(user) + " ";
		} else {
			sql_request = sql_create_query(user);
		}

		if (request.getParameter("favorite") != null) {
			dao.addToFavoriteList(request.getParameter("favorite"), user);
		}

		if (request.getParameter("favoriteRemove") != null) {
			dao.removeToFavoriteList(request.getParameter("favoriteRemove"), user);
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

		List<Annonces> announcement = getAnnoncesList(sql_request);
		List<Annonces> announcement2 = getAnnoncesList(sql_request2);
		
		
		if (user_dao.findFactorBeta(user).equals(" ; ; ")) {
			factor = null;
		} else {
			factor = user_dao.findFactor(user);
		}
		
		
		List<Boolean> list = getBooleanList(announcement, user);
		List<Boolean> list2 = getBooleanList(announcement2, user);
		
		request.setAttribute("liste", list);
		request.setAttribute("liste2", list2);
		request.setAttribute("factor", factor);
		
		request.setAttribute("annoucement_user_factor", announcement);
		request.setAttribute("annoucement_user", announcement2);
		
		session.setAttribute("user", user);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
