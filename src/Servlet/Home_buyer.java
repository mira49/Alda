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

@WebServlet("/homeBuyer")
public class Home_buyer extends AbstactQueryClass {

	public static final String VUE = "/WEB-INF/homeSite.jsp";

	@EJB
	private AnnouncementDAO dao;
	@EJB
	private UserDAO user_dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String sql_request2 = null;

		List<Annonces> announcement2 = new ArrayList<>();

		sql_request2 = "SELECT u FROM Annonces u where u.sold = 0";

		announcement2 = dao.findByFactor(sql_request2);

		request.setAttribute("annoucement_user", announcement2);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String select_option = request.getParameter("select_option");
		List<Annonces> announcement2 = new ArrayList<>();

		String factor[] = new String[5];

		String sql_request2 = "SELECT u FROM Annonces u where u.sold = 0";

		if (request.getParameter("factor_lower_price") != null || request.getParameter("factor_higher_price") != null
				|| request.getParameter("factor_location") != null
				|| request.getParameter("factor_lower_surface") != null
				|| request.getParameter("factor_higher_surface") != null) {
			sql_request2 = sql_create_query_factor(request.getParameter("factor_lower_price"),
					request.getParameter("factor_higher_price"), request.getParameter("factor_location"),
					request.getParameter("factor_lower_surface"), request.getParameter("factor_higher_surface")) + " ";
			factor[0] = request.getParameter("factor_lower_price");
			factor[1] = request.getParameter("factor_higher_price");
			factor[2] = request.getParameter("factor_location");
			factor[3] = request.getParameter("factor_lower_surface");
			factor[4] = request.getParameter("factor_higher_surface");
		}

		if (select_option != null) {
			if (select_option.equals("lower_Price")) {
				sql_request2 = dao.findByLowerPrice(sql_request2);
			} else if (select_option.equals("higher_Price")) {
				sql_request2 = dao.findByHigherPrice(sql_request2);
			} else if (select_option.equals("Location")) {
				sql_request2 = dao.findByPostalCode(sql_request2);
			}
		}

		announcement2 = dao.findByFactor(sql_request2);
		request.setAttribute("factor", factor);

		request.setAttribute("annoucement_user", announcement2);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
