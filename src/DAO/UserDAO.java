package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;

import Entities.User;

@Stateless
public class UserDAO {

	@PersistenceContext(unitName ="persistenceUnit")
	private EntityManager em; 

	public void create(User user) {
		em.persist(user);
	}

	public void delete(Long id) {
			em.createNamedQuery("User.deleteUser").setParameter(1, id).executeUpdate();
	}
	
	public User findUser(String email, String password) {
		User user = null;
		try{
			user = (User) em.createNamedQuery("User.findUserConnexion")
					.setParameter("email", email)
					.setParameter("password", password)
					.getSingleResult();
		}catch(Exception e){}
		
		return user;
	}
	
	public List<User>  getUsers() {
		List<User> find_user = new ArrayList<User>();

		find_user = em.createNamedQuery("User.findAll", User.class).getResultList();

		return find_user;
	}


	public User find(String email) {
		List<User> find_user = new ArrayList<User>();
		User user = null;
		/*emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		find_user = em.createNativeQuery("select * from alda_user where email = ?", User.class).setParameter(1, email)
				.getResultList();

		if (find_user.isEmpty()) {
			user = null;
		} else {
			user = find_user.get(0);
		}*/
		return user;
	}

	public User update_or_insert(User account_modify, User temp) {


		User account = em.find(User.class, account_modify.getId());

		account.setFirstName(temp.getFirstName());
		account.setName(temp.getName());
		account.setAddress(temp.getAddress());
		account.setPhone(temp.getPhone());
		
		return account;
	}

	public String[] findFactor(User user) {
		
	
		User user_temp = new User();
		String factor = "";

		user_temp = em.find(User.class, user.getId());
		factor = user_temp.getFactor();

		String factor_temp[] = factor.split(";");
		return factor_temp;
	}

	public User updateFactor(User user_tmp, User user) {

		User account = em.find(User.class, user.getId());
		account.setFactor(user_tmp.getFactor());
		
		return account;
	}

	public void UpdatePassword(User account_modify, User temp) {

		User account = em.find(User.class, account_modify.getId());
		account.setPassword(temp.getPassword());

	}

	public User findByUser(User user) {
		
		User u = em.find(User.class, user.getId());
		return u;
	}

	public void updateDate(User user_connexion, Date dt) {
		user_connexion.setDate_connexion(dt);
		em.merge(user_connexion);
	}

	public void updateDateDeco(User user, Date dateFormatee) {
		user.setDate_deconnection(dateFormatee);
		em.merge(user);	
	}

}