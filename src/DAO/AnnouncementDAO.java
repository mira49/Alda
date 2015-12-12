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
	
	public void create(Annonces n){
		
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
	
	public List<Annonces> getAnnoucement_user(User user){
		
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		List<Annonces> announcement = null;
		
		String email = user.getEmail();
		em.getTransaction().begin();
		
		announcement = em.createNativeQuery("select * from Annonces where email = ?", Annonces.class).setParameter(1, email).getResultList();
		
		return announcement;
	}
}
