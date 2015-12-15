package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;

import Entities.User;

@Stateless
public class UserDAO {

	EntityManagerFactory emf;
	EntityManager em;

	public void create(User user) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();

		try {
			em.persist(user);
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

	public void delete(Long id) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();
		try {
			em.createNativeQuery("delete from alda_user where id = ?").setParameter(1, id).executeUpdate();
			transactionOk = true;
		} finally {
			if (transactionOk) {
				em.getTransaction().commit();
			} else {
				System.out.println("error in delete announcement");
				em.getTransaction().rollback();
			}
		}
	}
	public User findUser(String email, String password) {
		List<User> find_user = new ArrayList<User>();
		User user;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		// boolean transactionOk = false;

		// try{
		find_user = em.createNativeQuery("select * from alda_user where email = ? and password = ?", User.class)
				.setParameter(1, email).setParameter(2, password).getResultList();

		if (find_user.isEmpty()) {
			user = null;
		} else {
			user = find_user.get(0);
		}
		return user;
	}
	
	public List<User>  getUsers() {
		List<User> find_user = new ArrayList<User>();
		User user;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		// boolean transactionOk = false;

		// try{
		find_user = em.createNativeQuery("select * from alda_user", User.class)
				.getResultList();

		return find_user;
	}

	public User find(String email) {
		List<User> find_user = new ArrayList<User>();
		User user;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		find_user = em.createNativeQuery("select * from alda_user where email = ?", User.class).setParameter(1, email)
				.getResultList();

		if (find_user.isEmpty()) {
			user = null;
		} else {
			user = find_user.get(0);
		}
		return user;
	}

	public void update_or_insert(User account_modify, User temp) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		User account = em.find(User.class, account_modify.getId());

		account.setFirstName(temp.getFirstName());
		account.setName(temp.getName());
		account.setAddress(temp.getAddress());
		account.setPhone(temp.getPhone());

		em.getTransaction().commit();
		em.close();
	}

	public String[] findFactor(User user) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		User user_temp = new User();
		String factor = "";

		user_temp = em.find(User.class, user.getId());
		factor = user_temp.getFactor();

		String factor_temp[] = factor.split(";");
		System.out.println(factor_temp.length);
		return factor_temp;
	}

	public void updateFactor(User user_tmp, User user) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		User account = em.find(User.class, user.getId());
		account.setFactor(user_tmp.getFactor());

		System.out.println(account.getFactor());
		em.getTransaction().commit();
		em.close();
	}

	public String sql_create_query(User user) {

		String factor_user = user.getFactor();
		String sql = "select * from Annonces";
		String factor[] = new String[3];
		factor = factor_user.split(";");
		System.out.println(factor[0]);

		if (!(StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where price >=" + factor[0];
		}

		if ((StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where price <=" + factor[1];
		}

		if ((StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where postal_code =" + factor[2];
		}

		if (!(StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where price between " + factor[0] + " AND " + factor[1];
		}

		if (!(StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where price >=" + factor[0] + " AND postal_code =" + factor[2];
		}

		if ((StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "select * from Annonces where price <=" + factor[1] + " AND postal_code =" + factor[2];
		}
		  
		if (!(StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
		  sql= "select * from Annonces where price between " + factor[0] +" AND " + factor[1] +" AND postal_code =" + factor[2];
		 }

		System.out.println("le sql" + sql);
		return sql;

	}

	public void UpdatePassword(User account_modify, User temp) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		User account = em.find(User.class, account_modify.getId());
		account.setPassword(temp.getPassword());
		em.getTransaction().commit();
		em.close();

	}

}
