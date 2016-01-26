package servlets;

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

import ejb.MessageDAO;
import ejb.MessageItf;
import ejb.UserDAO;
import ejb.UserItf;
import entities.Annonces;
import entities.Messages;
import entities.User;

@WebServlet("/messages_user")
public class messages extends HttpServlet {

	public static final String VUE = "/WEB-INF/Message.jsp";

	@EJB
	MessageItf mess_dao;

	@EJB
	UserItf dao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<Messages> message_list = new ArrayList<>();
		message_list = mess_dao.findAllByUser((User) session.getAttribute("user"));

		User user = dao.findByUser((User) session.getAttribute("user"));
		List<Messages> messagesCheckNOK = mess_dao.findAllByUser(user);
		MessageCheck(messagesCheckNOK);
		request.setAttribute("message_user", message_list);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void MessageCheck(List<Messages> messages) {

		for (Messages mess : messages) {
			if (mess.getNotification() == 1) {
				mess_dao.update(mess);
			}
		}
	}
}
