package Servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AnnouncementDAO;
import Entities.Annonces;
import Entities.User;

@WebServlet("/favorite")
public class Favorite extends HttpServlet{
	
public static final String VUE = "/WEB-INF/Favorite.jsp";
	
	@EJB
	AnnouncementDAO dao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		List <Annonces> announces_favorite = dao.findAllByFavorite((User)session.getAttribute("user"));
		
		request.setAttribute("annoucement_user", announces_favorite);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
