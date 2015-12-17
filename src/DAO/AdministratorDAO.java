package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Administrator;
import Entities.User;

@Stateless
public class AdministratorDAO {

	EntityManagerFactory emf;
	EntityManager em;
	public boolean instance=false;
	
	public void create(Administrator Administrator) {

		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		boolean transactionOk = false;
		em.getTransaction().begin();

		try {
			em.merge(Administrator);
			transactionOk = true;
		} finally {
			if (transactionOk) {
				instance=true;
				em.getTransaction().commit();
			} else {
				System.out.println("Administrator déjà présent en bdd");
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
	
	public Administrator findAdministrator(String pseudo, String password) {
		List<Administrator> find_Administrator = new ArrayList<Administrator>();
		Administrator Administrator;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		// boolean transactionOk = false;

		// try{
		find_Administrator = em.createNativeQuery("select * from ALDA_Administrator where pseudo = ? and password = ?", Administrator.class)
				.setParameter(1, pseudo).setParameter(2, password).getResultList();

		if (find_Administrator.isEmpty()) {
			Administrator = null;
		} else {
			Administrator = find_Administrator.get(0);
		}
		return Administrator;
	}
	
	
}
