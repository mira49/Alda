package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Entities.Messages;

import Entities.Annonces;
import Entities.User;

@Stateless
public class MessageDAO {
	@PersistenceContext(unitName ="persistenceUnit")
	private EntityManager em; 

	public void create(User user, String message, Annonces attribute) {

	
		Messages msg = new Messages();
		msg.setMessage(message);
		msg.setReceiver_message((attribute.getUser().getName()));
		msg.setSender_message(user.getName());
		
		System.out.println("le user est" + msg.getMessage() );
		System.out.println("l'annonce est" + msg.getReceiver_message());
		System.out.println("le message est" + msg.getSender_message());
		
		em.merge(msg);
	}

	public List<Messages> findAllByUser(User user) {
		List< Messages > list_message = new ArrayList<>();
		
		try{
			list_message =  em.createNamedQuery("Messages.getAllByUser")
					.setParameter("name", user.getName())
					.getResultList();
		}catch(Exception e){}
		 
		return list_message;
	}
}
