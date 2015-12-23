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

import DAO.AdministratorDAO;
import DAO.AnnouncementDAO;
import DAO.UserDAO;
import Entities.Administrator;
import Entities.Annonces;
import Entities.User;

@WebServlet("/dashboarddeleteUser")
public class DeleteUser extends AbstactQueryClass {

	public static final String VUE = "/WEB-INF/ShowUsers.jsp";
	public static final String VUE2 = "/WEB-INF/ShowAnnonce.jsp";
	public static final String VUESucess = "/WEB-INF/ConnectionDashboard.jsp";

	@EJB
	UserDAO dao;

	@EJB
	AnnouncementDAO dao2;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		HttpSession session = request.getSession();

		
			String idUser = request.getParameter("delete");

			if (idUser != null) {
				Long id = Long.parseLong(idUser);
				dao.delete(id);
				List<User> users = dao.getUsers();
				request.setAttribute("users", users);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

			} else {
				String idA = request.getParameter("deleteA");
				if (idA != null) {
					Long idAN = Long.parseLong(idA);

					dao2.delete(idAN);
					String select_option = request.getParameter("select_option");
					System.out.println("select_option :" + select_option);
					String sql_request = null;

					List<Annonces> announcement = new ArrayList<>();
					if ((User) session.getAttribute("user") != null) {
						sql_request = sql_create_query((User) session.getAttribute("user")) + " ";
						String factor[] = new String[3];
						factor = dao.findFactor((User) session.getAttribute("user"));
						session.setAttribute("factor", factor);
					} else {
						sql_request = "select * from Annonces";
					}

					System.out.println("sql_request:" + sql_request);
					announcement = dao2.findByFactor(sql_request);

					session.setAttribute("annoucement_user", announcement);
					this.getServletContext().getRequestDispatcher(VUE2).forward(request, response);

				}

			}

		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
