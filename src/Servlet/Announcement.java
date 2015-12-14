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
	private UserDAO user_dao = new UserDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String select_option = request.getParameter("select_option");
		System.out.println(select_option);
		List<Annonces> announcement = new ArrayList<>();
		announcement = dao.findAll();

		String factor[] = user_dao.findFactor((String) session.getAttribute("user.email"));
		session.setAttribute("factor", factor);

		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String select_option = request.getParameter("select_option");
		List<Annonces> announcement = new ArrayList<>();

		String factor_tmp[] = new String[2];
		factor_tmp[0] = request.getParameter("factor_lower_price");
		factor_tmp[1] = request.getParameter("factor_higher_price");
		factor_tmp[2] = request.getParameter("factor_location");

		User user = new User();
		user.setFactor(factor_tmp);
		user_dao.updateFactor(user, (User)session.getAttribute("user.email"));
		String sql_request = user_dao.sql_create_query((User)session.getAttribute("user"));
		
		if (select_option.equals("lower_Price")) {
			announcement = dao.findByLowerPrice();
		} else if (select_option.equals("higher_Price")) {
			announcement = dao.findByHigherPrice();
		} else if (select_option.equals("Location")) {
			announcement = dao.findByPostalCode();
		} else {
			announcement = dao.findAll();
		}

		session.setAttribute("annoucement_user", announcement);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
