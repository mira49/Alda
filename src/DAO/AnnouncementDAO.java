package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Entities.Annonces;
import Entities.User;

@Stateless
public class AnnouncementDAO {

	@PersistenceContext(unitName ="persistenceUnit")
	private EntityManager em; 
	
	public void create(Annonces n) {
			em.merge(n);	
	}

	public List<Annonces> getAnnoucement_user(User user) {

	
		List<Annonces> announcement = new ArrayList<>();

		try{
			announcement =  (List<Annonces>) em.createNamedQuery("Annonces.AnnouncementUser")
					.setParameter("email", user.getName())
					.getResultList();
		}catch(Exception e){}
		
		return announcement;
	}
	
	public void delete(Long idA) {
		try{
			em.createNamedQuery("Annonces.deleteAnnounce")
					.setParameter("id", idA).executeUpdate();
		}catch(Exception e){}
	}

	public List<Annonces> findAll() {

		
		List<Annonces> announcement = null;

		try{
			em.createNamedQuery("Annonces.findAll").getResultList();
		}catch(Exception e){}
		
		return announcement;
	}
	
	public List<Annonces> findByFactor(String query){

		List<Annonces> announcement = null;

		announcement = em.createQuery(query, Annonces.class).getResultList();

		return announcement;
	}

	public List<Annonces> findByLowerPrice(String sql_request) {
		System.out.println(sql_request);
		List<Annonces> announcement = null;

		announcement = em.createQuery(sql_request + "order by price ASC", Annonces.class).getResultList();

		return announcement;
	}

	public List<Annonces> findByHigherPrice(String sql_request) {

		List<Annonces> announcement = null;

		announcement = em.createQuery(sql_request + "order by u.price DESC", Annonces.class)
				.getResultList();

		return announcement;
	}

	public List<Annonces> findByPostalCode(String sql_request) {

		List<Annonces> announcement = null;

		announcement = em.createQuery(sql_request + "order by u.postal_code", Annonces.class)
				.getResultList();

		return announcement;
	}

	public Annonces findById(String annonce_id) {
		/*emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();*/
		
		Annonces annonce = new Annonces();
		/*annonce = (Annonces) em.createNativeQuery("select * from Annonces where id=" + annonce_id, Annonces.class)
				.getSingleResult();*/
		return annonce;
	}

	public void setAnnounceSold(String parameter) {
		
	/*	emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();*/
		
		Annonces annonce = em.find(Annonces.class, parameter);
		
		annonce.setSold(1);
		
		//em.getTransaction().commit();
		
	}

	public void addToFavoriteList(String parameter) {
	/*	emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();*/
		
		String factor[] = new String[2];
		factor = parameter.split(";");
		
		Annonces annonce = findById(factor[1]);
		
		annonce.setFavorite(factor[0] + ";");

		//em.getTransaction().commit();
	}

	public List<Annonces> findAllByFavorite(User user) {
		/*emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();*/
		
		List<Annonces> announces = null;
		//announces = em.createNativeQuery("SELECT * FROM annonces WHERE favorite LIKE '%" + user.getEmail() + "%'", Annonces.class).getResultList();
		return announces;
	}

	

}
