package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import entities.Annonces;
import entities.Messages;
import entities.User;

@Stateless
public class MessageDAO {
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public void create(User user, String message, Annonces attribute) {
		Messages msg = new Messages(message, user.getName() + user.getFirstName(),attribute.getUser(),0);
		em.merge(msg);
	}

	public void create(Messages messages) {
		em.persist(messages);
	}

	public List<Messages> findAllByUser(User user) {
		List<Messages> list_message = new ArrayList<>();

		try {
			list_message = em.createNamedQuery("Messages.getAllByUser").setParameter("email", user.getEmail())
					.getResultList();
		} catch (Exception e) {
		}

		return list_message;
	}

	public long findAnnouncementSold(String email) {

		long count = 0;
		System.out.println(email);
		try {
			count = (long) em.createNamedQuery("Messages.CountSoldAnnouncement").setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
		}
		return count;
	}

	public void update(Messages message) {

		Messages mess = em.find(Messages.class, message.getId());
		mess.setNotification(0);
		em.persist(mess);
	}
}
