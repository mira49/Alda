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

import DAO.MessageDAO;
import Entities.Annonces;
import Entities.Messages;
import Entities.User;

@WebServlet("/messages_user")
public class messages extends HttpServlet{
	
	public static final String VUE = "/WEB-INF/Message.jsp";
	
	@EJB
	MessageDAO mess_dao = new MessageDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		List<Messages> message_list = new ArrayList<>();
		message_list = mess_dao.findAllByUser((User)session.getAttribute("user"));
		
		request.setAttribute("message_user", message_list);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
