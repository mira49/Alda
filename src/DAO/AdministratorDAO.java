package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Entities.Administrator;
import Entities.User;

@Stateless
public class AdministratorDAO {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public void create(Administrator Administrator) {

		em.persist(Administrator);
	}

	public Administrator findAdministrator(String pseudo, String password) {
		List<Administrator> find_Administrator = new ArrayList<Administrator>();
		Administrator Administrator;

		find_Administrator = em.createNamedQuery("Administrator.find", Administrator.class)
				.setParameter("pseudo", pseudo).setParameter("password", password).getResultList();

		if (find_Administrator.isEmpty()) {
			Administrator = null;
		} 
		else {
			Administrator = find_Administrator.get(0);
		}
		return Administrator;
	}

}
