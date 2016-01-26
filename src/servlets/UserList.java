package servlets;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.UserDAO;
import ejb.UserItf;
import entities.User;

@WebServlet("/dashboarduserList")
public class UserList extends HttpServlet {

	public static final String VUE = "/WEB-INF/ShowUsers.jsp";
	public static final String VUESucess = "/WEB-INF/ConnectionDashboard.jsp";

	@EJB
	UserItf userDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<User> users = userDao.getUsers();

		request.setAttribute("users", users);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
