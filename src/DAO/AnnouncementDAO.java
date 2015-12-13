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
				System.out.println("Annonce déjà présente en bdd");
				em.getTransaction().rollback();
			}
		}
		em.close();
	}

	public List<Annonces> getAnnoucement_user(User user) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		String name = user.getName();
		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces where email = ?", Annonces.class)
				.setParameter(1, name).getResultList();

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

		announcement = em.createNativeQuery("select * from Annonces", Annonces.class).getResultList();

		return announcement;
	}

	public List<Annonces> findByLowerPrice() {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces order by price ASC", Annonces.class)
				.getResultList();

		return announcement;
	}

	public List<Annonces> findByHigherPrice() {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces order by price DESC", Annonces.class)
				.getResultList();

		return announcement;
	}

	public List<Annonces> findByPostalCode() {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;

		em.getTransaction().begin();

		announcement = em.createNativeQuery("select * from Annonces order by postal_code", Annonces.class)
				.getResultList();

		return announcement;
	}

}
