package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.FormValidationException;
import ejb.UserDAO;
import ejb.UserItf;
import entities.User;

@WebServlet("/forgetting")
public class Forgetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	public static final String VUE = "/WEB-INF/Forgetting.jsp";
	private static final String FICHIER = "/DAO/dao.mail";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	@EJB
	private UserItf userDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

		String email = request.getParameter("email");

		try {
			traiteEmail(email);
			if (erreurs.isEmpty()) {
				SecureRandom random = new SecureRandom();
				String password = new BigInteger(40, random).toString(32);
				User user1 = new User();
				User user2 = new User();
				user1 = userDao.find(email);
				user2.setPassword(password);
				userDao.UpdatePassword(user1, user2);
				postMail(email, password);
				resultat = "Succ�s.";
			} else {
				resultat = "�chec de la reg�n�ration.";
			}
		} catch (ejb.DAOException e) {
			resultat = "�chec de la reg�n�ration : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("Succ�s.".equals(resultat)) {
			this.getServletContext().getRequestDispatcher(VUESucess).forward(request, response);
		} else {
			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute("resultat", resultat);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	public void traiteEmail(String email) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur("email", e.getMessage());
		}
	}

	public void validationEmail(String email) throws FormValidationException {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new FormValidationException("Bad Email's Format.");
			} else if (userDao.find(email) == null) {
				throw new FormValidationException("This email address is not found.");
			}
		} else {
			throw new FormValidationException("Thank you to enter an email address.");
		}
	}

	public void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	public void postMail(String to, String password) throws Exception {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(FICHIER);
		Properties properties = new Properties();
		final String from;
		final String passwordMail;

		if (fileProperties == null) {
			throw new Exception("Le fichier properties " + FICHIER + " est introuvable.");
		}

		try {
			properties.load(fileProperties);
			from = properties.getProperty("mail");
			passwordMail = properties.getProperty("password");
		} catch (IOException e) {
			throw new Exception("Impossible de charger le fichier properties " + FICHIER, e);
		}

		String subject = "Votre demande de code d'acc�s a �t� accept�e.";
		String msg = "Bonjour, \n"
				+ "Voici le code confidentiels qui vous permettera d'acc�der, d�s � pr�sent, � votre espace adh�rents : \n"
				+ "Mot de passe :" + password + "\n"
				+ "Nous vous conseillons de conserver ce code, ils vous sera demand� � chaque visite sur votre espace s�curis�. \n"
				+ "Important : lorsque vous saisissez vos codes, attention de bien respecter les majuscules/minuscules et de ne pas laisser d'espace avant/apr�s apr�s le mot de passe. \n"
				+ "A bient�t ";

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, passwordMail);
			}
		});

		// session.setDebug(true);
		Transport transport = session.getTransport();
		InternetAddress addressFrom = new InternetAddress(from);

		MimeMessage message = new MimeMessage(session);
		message.setSender(addressFrom);
		message.setSubject(subject);
		message.setContent(msg, "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		transport.connect();
		Transport.send(message);
		transport.close();
		System.out.println("Sent message successfully....");

	}

}