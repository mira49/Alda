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
import DAO.MessageDAO;
import DAO.UserDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/contactBuyer")
public class ContactBuyer extends HttpServlet {

	public static final String VUE = "/WEB-INF/contactBuyer.jsp";

	@EJB
	AnnouncementDAO dao;

	@EJB
	UserDAO user_dao;

	@EJB
	MessageDAO msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String annonce = request.getParameter("contact");
		Annonces annonces = dao.findById(annonce);

		request.setAttribute("current_announce", annonces);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
