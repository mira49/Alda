package Servlet;

import java.io.IOException;

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

@WebServlet("/Add_Announcement")
public class Add_Announcement extends HttpServlet {
	public static final String VUE = "/WEB-INF/Add_Announcement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	public static final String VUEAfter = "/WEB-INF/Home_user.jsp";

	@EJB
	private AnnouncementDAO annoucement;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + VUESucess);
		} else {
			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String Name = request.getParameter("Name");
		String emplacement = request.getParameter("Location");
		int Surface = Integer.parseInt(request.getParameter("Surface"));
		String Description = request.getParameter("Description");

		annoucement = new AnnouncementDAO();

		Annonces a = new Annonces();
		a.setName(Name);
		a.setSurface(Surface);
		a.setDescription(Description);
		a.setEmplacement(emplacement);
		a.setUser_ID((User)session.getAttribute("user"));


		annoucement.create(a);

		this.getServletContext().getRequestDispatcher(VUEAfter).forward(request, response);

	}
}
