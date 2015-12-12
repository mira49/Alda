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
	
	public List<Annonces> getAnnoucement_user(User current_user){
		
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		List<Annonces> announcement = new ArrayList<>();
		
		
		em.getTransaction().begin();
		try{
			announcement = em.createNativeQuery("select * from Annonces where email = ?", Annonces.class).setParameter(1, current_user.getAddress()).getResultList();
			transactionOk = true;
		}finally {
			if (transactionOk) {
				em.getTransaction().commit();
			} else {
				System.out.println("erreur with getAnnouncement_user");
				em.getTransaction().rollback();
			}
		}
		return announcement;
	}
}
