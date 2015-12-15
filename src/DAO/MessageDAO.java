package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Messages;

import Entities.Annonces;
import Entities.User;

@Stateless
public class MessageDAO {
	EntityManagerFactory emf;
	EntityManager em;

	public void create(User user, String message, Annonces attribute) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();
		
		Messages msg = new Messages();
		msg.setMessage(message);
		msg.setReceiver_message((attribute.getUser().getName()));
		
		System.out.println("le name du mec est" + user.getName());
		msg.setSender_message(user.getName());
		try {
			em.persist(msg);
			transactionOk = true;
		} finally {
			if (transactionOk) {
				em.getTransaction().commit();
			} else {
				System.out.println("utilisateur déjà présent en bdd");
				em.getTransaction().rollback();
			}
		}
		em.close();
	}

	public List<Messages> findAllByUser(User user) {
		List< Messages > list_message = new ArrayList<>();
		
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		list_message = em.createNativeQuery("select * from Messages where receiver_message = ?", Messages.class).setParameter(1, user.getName()).getResultList();
		 
		return list_message;
	}
}
