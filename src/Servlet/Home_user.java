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

import org.apache.commons.lang.StringUtils;

import DAO.AnnouncementDAO;
import DAO.MessageDAO;
import DAO.UserDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/home_user")
public class Home_user extends HttpServlet {

	public static final String VUE = "/WEB-INF/Home_user.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";

	@EJB
	private MessageDAO dao;

	@EJB
	private UserDAO user_dao;

	@EJB
	private AnnouncementDAO announceDAO;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {

			User user_temp = user_dao.findByUser((User) session.getAttribute("user"));
			long new_notification = dao.findAnnouncementSold(user_temp.getEmail());
			request.setAttribute("notifications", new_notification);

			List<Annonces> annonce = announceDAO.findAll();
			String news = newAnnouncewithFactorUser(annonce, (User) session.getAttribute("user"));
			if (news.equals("news")) {
				request.setAttribute("news", news);
			}

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public String newAnnouncewithFactorUser(List<Annonces> announce, User user) {

		User user_tmp = user_dao.findByUser(user);
		String answear = "";

		for (Annonces a : announce) {
			if(!(a.getUser().getEmail().equals(user.getEmail()))){
				boolean check = true;
				if (a.getDate().compareTo(user.getDate_connexion()) > 0) {
					String factor[] = user.getFactor().split(";");
					if (!StringUtils.isBlank(factor[0])) {
						if (Integer.parseInt(factor[0]) > a.getPrice()) {
							check = false;
						}
					}
	
					if (!StringUtils.isBlank(factor[1])) {
						if (Integer.parseInt(factor[1]) < a.getPrice()) {
							check = false;
						}
					}
	
					if (!StringUtils.isBlank(factor[2])) {
						if (Integer.parseInt(factor[2]) == a.getPostal_code()) {
							check = false;
						}
					}
	
					if (check) {
						answear = "news";
					}
				}
			}
		}
		return answear;
	}

}