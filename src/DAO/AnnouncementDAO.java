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
			announcement = em.createNamedQuery("Annonces.findAll").getResultList();
		}catch(Exception e){}
		
		return announcement;
	}
	
	public List<Annonces> findByFactor(String query){

		List<Annonces> announcement = null;
		System.out.println("query :" + query);
		announcement = em.createQuery(query, Annonces.class).getResultList();

		return announcement;
	}

	public String findByLowerPrice(String sql_request) {
		sql_request = sql_request + "order by u.price ASC";

		return sql_request;
	}

	public String findByHigherPrice(String sql_request) {

		List<Annonces> announcement = null;

		sql_request =sql_request + "order by u.price DESC";

		return sql_request;
	}

	public String findByPostalCode(String sql_request) {

		List<Annonces> announcement = null;

		sql_request = sql_request + "order by u.postal_code";

		return sql_request;
	}

	public Annonces findById(String annonce_id) {
		Long id = Long.parseLong(annonce_id); 
		Annonces annonce = new Annonces();
		try{
			annonce = (Annonces) em.createNamedQuery("Annonces.findByID")
					.setParameter("id", id).getSingleResult();
		}catch(Exception e){}
		return annonce;
	}

	public void setAnnounceSold(String parameter) {
		
		
		Annonces annonce = em.find(Annonces.class, parameter);
		
		annonce.setSold(1);
	
	}

	public void addToFavoriteList(String parameter) {

	
		String factor[] = new String[2];
		factor = parameter.split(";");
		
		Annonces annonce = findById(factor[1]);
		System.out.println(annonce.getName());
		annonce.setFavorite(factor[0] + ";");
	}

	public List<Annonces> findAllByFavorite(User user) {
		
		List<Annonces> announces = null;
		try{
			announces = (List<Annonces>) em.createNamedQuery("Annonces.findAllByFavorite")
					.setParameter("email", "%" + user.getEmail()+ "%").getResultList();
		}catch(Exception e){}
		return announces;
	}

	

}
