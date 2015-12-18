package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Entities.User;

@WebServlet("/userList")
public class UserList extends HttpServlet {

	public static final String VUE = "/WEB-INF/ShowUsers.jsp";
	public static final String VUESucess = "/WEB-INF/ConnectionDashboard.jsp";

	@EJB
	UserDAO userDao= new UserDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("admin") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			List<User> users = userDao.getUsers();
		
		request.setAttribute("users", users);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}}
}
