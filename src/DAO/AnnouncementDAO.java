package DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Annonces;

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
			em.persist(n);
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
}
