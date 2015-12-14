package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Entities.User;
import EntityManagerF.SingletonEntityManagerFactory;

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

	public User find(String email) {
		List<User> find_user = new ArrayList<User>();
		User user;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		find_user = em.createNativeQuery("select * from alda_user where email = ? ", User.class).setParameter(1, email)
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

	public String[] findFactor(String email) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		String[] factor = (String[]) em.createNativeQuery("select factor from alda_user where email = ? ", User.class)
				.setParameter(1, email).getSingleResult();
		return factor;
	}

	public void updateFactor(User user_tmp, User user) {
		
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		User account = em.find(User.class, user.getId());
		account.setFactor(user_tmp.getFactor());
		
		em.getTransaction().commit();
		em.close();
	}

	public String sql_create_query(User user) {
		String factor[] = user.getFactor();
		String sql = "";
		
		if((factor[0]!= null || factor[0] != "") && (factor[1] == null || factor[1] == "" )&& (factor[2] == null || factor[2] == "")){
			sql = "select * from Annonces where price >=" + factor[0];
		}
		
		else if((factor[0]== null || factor[0] == "") && (factor[1] != null || factor[1] != "" )&& (factor[2] == null || factor[2] == "")){
			sql = "select * from Annonces where price <=" + factor[1];
		}
		
		else if((factor[0]== null || factor[0] == "") && (factor[1] == null || factor[1] == "" )&& (factor[2] != null || factor[2] != "")){
			sql = "select * from Annonces where postal_code =" + factor[2];
		}
		
		else if((factor[0]!= null || factor[0] != "") && (factor[1] != null || factor[1] != "" )&& (factor[2] == null || factor[2] == "")){
			sql = "select * from Annonces where price between" + factor[0] + "AND" + factor[1];
		}
		
		else if((factor[0]!= null || factor[0] != "") && (factor[1] == null || factor[1] == "" )&& (factor[2] != null || factor[2] != "")){
			sql = "select * from Annonces where price >=" + factor[0] + "AND postal_code = " + factor[2];
		}
		
		else if((factor[0]== null || factor[0] == "") && (factor[1] != null || factor[1] != "" )&& (factor[2] != null || factor[2] != "")){
			sql = "select * from Annonces where price <=" + factor[1] + "AND postal_code = " + factor[2];
		}
		
		else if((factor[0]!= null || factor[0] != "") && (factor[1] != null || factor[1] != "" )&& (factor[2] != null || factor[2] != "")){
			sql = "select * from Annonces where price between" + factor[0] + "AND" + factor[1] + "AND postal_code = " + factor[2];
		}
		
		else{
			sql = "select * from Annonces";
		}
		
		System.out.println(sql);
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
