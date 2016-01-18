package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.UserDAO;
import entities.User;
import junit.framework.TestCase;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import static org.junit.Assert.*;

public class UserDAOTest extends TestCase {

    private Context  ctx;
    private EJBContainer ejbContainer;
    
    @BeforeClass
    public  void setUp() {
        ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Opening the container" );
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    public  void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the container" );
    }
    
    
	private UserDAO user_dao;
	
	 @Test
	public void testCreate() throws NamingException {
		 UserDAO converter = (UserDAO) ctx.lookup("java:global/classes/UserDAO");
	     assertNotNull(converter);
		
		user_dao = new UserDAO();
		assertTrue(user_dao != null);
		System.out.println("Test create start");
		User user = new User("test@gmail.com", "blalbla123");
		
		User user_tmp = user_dao.findByUser(user);
		
		assertTrue(user_tmp == user);

	}

	
	public void testDelete() {
		User user = new User("test@gmail.com", "blalbla123");
		User user_to_delete = user_dao.findByUser(user);
		user_dao.delete(user_to_delete.getId());
		
		User user_response = user_dao.findByUser(user);
		
		assertTrue(user_to_delete != user_response);
	}

	
	public void testFindUser() {
		User user = new User("test@gmail.com", "blalbla123");
		user_dao.create(user);
		
		User user_rep = user_dao.findByUser(user);
		
		assertTrue(user_rep != null);
		
	}

	
	public void testUpdate_or_insert() {
		
		User user_to_update = new User("test2255@gmail.com", "blalbl141a123");
		user_dao.create(user_to_update);
		
		User user_to_modify = user_dao.findByUser(user_to_update);
		user_to_modify.setFirstName("coucou");
		user_to_modify.setName("hello");
		user_to_modify.setAddress("34 rue des fleurs");
		user_to_modify.setPhone("07 85 57 47 44");
		
		User check = user_dao.findByUser(user_to_update);
		assertTrue(check.getFirstName().equals("coucou"));
		assertTrue(check.getName().equals("hello"));
		assertTrue(check.getAddress().equals("34 rue des fleurs"));
		assertTrue(check.getPhone().equals("07 85 57 47 44"));
	}



	
	public void testUpdateFactor() {
		
		User user = user_dao.find("test2255@gmail.com");
		user.setFactor("coucou;coucou;coucou");
		
		User check = user_dao.find("test2255@gmail.com");
		
		assertTrue(check.getFactor().equals("coucou;coucou;coucou"));
	}

	
	public void testUpdatePassword() {
		
		User user_update = new User("test2255@gmail.com", "test1234");
		User current = user_dao.find("test2255@gmail.com");
		
		user_dao.UpdatePassword(current, user_update);
		
		assertTrue(current.getPassword().equals("test1234"));
	}

	
	public void testUpdateDate() {
		User current = user_dao.find("test2255@gmail.com");
		java.util.Date dt = new java.util.Date();
		user_dao.updateDate(current, dt);
		
		assertTrue(current.getDate_connexion().equals(dt));
		
	}

	
	public void testUpdateDateDeco() {
		User current = user_dao.find("test2255@gmail.com");
		java.util.Date dt = new java.util.Date();
		user_dao.updateDateDeco(current, dt);
		
		assertTrue(current.getDate_deconnection().equals(dt));
	}

}
