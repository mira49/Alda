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

	private static final String VUE_VISU = "/WEB-INF/visuBuyer.jsp";

	public static final String VUE = "/WEB-INF/contactBuyer.jsp";

	@EJB
	AnnouncementItf dao;

	@EJB
	UserItf user_dao;

	@EJB
	MessageItf msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("view") != null){
			Annonces annonce = dao.findById(request.getParameter("view"));
			
			request.setAttribute("current_annonce", annonce);
			this.getServletContext().getRequestDispatcher(VUE_VISU).forward(request, response);
		}else{
		String annonce = request.getParameter("contact");
		Annonces annonces = dao.findById(annonce);

		request.setAttribute("current_announce", annonces);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);}
	}
	


}
