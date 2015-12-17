package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Annonces;
import Entities.User;

@Stateless
public class AnnouncementDAO {

	EntityManagerFactory emf;
	EntityManager em;

	public void create(Annonces n) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();
		
		try {
			em.merge(n);
			transactionOk = true;
		} finally {
			if (transactionOk) {
				em.getTransaction().commit();
			} else {
				System.out.println("error create Announce");
				em.getTransaction().rollback();
			}
		}
		em.close();
	}

	public List<Annonces> getAnnoucement_user(User user) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = new ArrayList<>();

		String name = user.getName();
		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces where email = ?", Annonces.class).setParameter(1, name).getResultList();

		return announcement;
	}

	public void delete(Long id) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();
		try {
			em.createNativeQuery("delete from Annonces where id = ?").setParameter(1, id).executeUpdate();
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

	public List<Annonces> findAll() {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces where sold = 0", Annonces.class).getResultList();

		return announcement;
	}
	
	public List<Annonces> findByFactor(String query){
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery(query, Annonces.class).getResultList();

		return announcement;
	}

	public List<Annonces> findByLowerPrice(String sql_request) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery(sql_request + "order by price ASC", Annonces.class).getResultList();

		return announcement;
	}

	public List<Annonces> findByHigherPrice(String sql_request) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery(sql_request + "order by price DESC", Annonces.class)
				.getResultList();

		return announcement;
	}

	public List<Annonces> findByPostalCode(String sql_request) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery(sql_request + "order by postal_code", Annonces.class)
				.getResultList();

		return announcement;
	}

	public Annonces findById(String annonce_id) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Annonces annonce = new Annonces();
		annonce = (Annonces) em.createNativeQuery("select * from Annonces where id=" + annonce_id, Annonces.class)
				.getSingleResult();
		return annonce;
	}

	public void setAnnounceSold(String parameter) {
		
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Annonces annonce = em.find(Annonces.class, parameter);
		
		annonce.setSold(1);
		
		em.getTransaction().commit();
		
	}

	public void addToFavoriteList(String parameter) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		String factor[] = new String[2];
		factor = parameter.split(";");
		
		Annonces annonce = findById(factor[1]);
		
		annonce.setFavorite(factor[0] + ";");

		em.getTransaction().commit();
	}

	public List<Annonces> findAllByFavorite(User user) {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Annonces> announces = null;
		announces = em.createNativeQuery("SELECT * FROM annonces WHERE favorite LIKE '%" + user.getEmail() + "%'", Annonces.class).getResultList();
		return announces;
	}

}
