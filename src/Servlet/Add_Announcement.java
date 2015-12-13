package Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.Part;
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

@WebServlet("/add_Announcement")
public class Add_Announcement extends HttpServlet {
	public static final String VUE = "/WEB-INF/Add_Announcement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
    private Map<String, String> erreurs         = new HashMap<String, String>();
	public static final String VUEAfter = "/WEB-INF/Home_user.jsp";


	private AnnouncementDAO annoucement;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			/* Redirection vers la page publique */
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String Name = request.getParameter("Name");
		String town = request.getParameter("Town");
		int Surface = Integer.parseInt(request.getParameter("Surface"));
		int Price = Integer.parseInt(request.getParameter("Price"));
		int postal_code = Integer.parseInt(request.getParameter("postal_code"));
		String Description = request.getParameter("Description");
		String picture1 = request.getParameter("picture1");
		String picture2 = request.getParameter("picture2");
		String picture3 = request.getParameter("picture3");
	    

		User user = (User) session.getAttribute("user");
		annoucement = new AnnouncementDAO();

		Annonces a = new Annonces();
      

		a.setName(Name);
		a.setSurface(Surface);
		a.setDescription(Description);
		a.setTown(town);
		a.setPostal_code(postal_code);
		a.setPrice(Price);
		a.setImage1(picture1);
		a.setImage2(picture2);	
		a.setImage3(picture3);
		a.setUser_ID(user);
		


		annoucement.create(a);

		this.getServletContext().getRequestDispatcher(VUEAfter).forward(request, response);

	}
	

	
	
	 
}
