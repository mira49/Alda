package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AnnouncementDAO;
import ejb.AnnouncementItf;
import ejb.MessageDAO;
import ejb.MessageItf;
import ejb.UserDAO;
import ejb.UserItf;
import entities.Annonces;

@WebServlet("/contactBuyer")
public class ContactBuyer extends HttpServlet {

	private static final String VUE_VISU = "/WEB-INF/VisuBuyer.jsp";

	public static final String VUE = "/WEB-INF/ContactBuyer.jsp";

	@EJB
	AnnouncementItf dao;

	@EJB
	UserItf user_dao;

	@EJB
	MessageItf msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("view") != null){
			Annonces announce = dao.findById(request.getParameter("view"));
			
			request.setAttribute("current_annonce", announce);
			this.getServletContext().getRequestDispatcher(VUE_VISU).forward(request, response);
		}else{
		String annonce_contact = request.getParameter("contact");
		Annonces current_annonces = dao.findById(annonce_contact);

		request.setAttribute("current_announce", current_annonces);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);}
	}
	


}
