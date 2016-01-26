package unit_tests;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import ejb.MessageDAO;
import entities.Annonces;
import entities.Messages;
import entities.User;

public class MessageDAOTest {

	@EJB
	private MessageDAO mess_test = new MessageDAO();
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testCreateUser() {
		
		User user = new User("test@gmail.com", "blalbla123");
		Messages msg = new Messages("coucou", "moi", user,0);
		Annonces annonce = new Annonces();
		annonce.setUser(user);
		
		mess_test.create(msg);
		mess_test.create(user, "test", annonce);
		
		Messages msg_check1,msg_check2;
		
		msg_check1 = em.find(Messages.class, msg.getMessage());
		msg_check2 = em.find(Messages.class, 2);
		
		assertTrue( "Error in Messages create" ,msg_check1 == msg);
		assertTrue("Error in Messages create" ,msg_check2 == msg);
		
	}

	@Test
	public void testFindAnnouncementSold() {
		
		Long count = mess_test.findAnnouncementSold("test@gmail.com");
		
		assertTrue("Error in Messages FindAnnouncementSold" ,count == 2);
	}

	@Test
	public void testUpdate() {
		User user = new User("test@gmail.com", "blalbla123");
		Messages msg = new Messages("coucou", "moi", user,0);
		
		mess_test.update(msg);
		
		Messages msg_check = em.find(Messages.class, msg.getMessage());
		assertTrue("Error in Messages Update" ,msg_check.getNotification() == 0);
	}

}
