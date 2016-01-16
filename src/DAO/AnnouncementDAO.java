package DAO;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import Entities.Annonces;
import Entities.User;

@Stateless
public class AnnouncementDAO {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public void create(Annonces n) {

		if (n.getId() != null){
			em.merge(n);
		}
		else{
			em.persist(n);
		}
		
	}

	public List<Annonces> getAnnoucement_user(User user) {

		List<Annonces> announcement = new ArrayList<>();

		try {
			announcement = (List<Annonces>) em.createNamedQuery("Annonces.AnnouncementUser")
					.setParameter("email", user.getEmail()).getResultList();
		} catch (Exception e) {
		}

		return announcement;
	}

	public void delete(Long idA) {
		try {
			em.createNamedQuery("Annonces.deleteAnnounce").setParameter("id", idA).executeUpdate();
		} catch (Exception e) {
		}
	}

	public List<Annonces> findAll() {

		List<Annonces> announcement = null;

		try {
			announcement = em.createNamedQuery("Annonces.findAll").getResultList();
		} catch (Exception e) {
		}

		return announcement;
	}

	public List<Annonces> findByFactor(String query) {

		List<Annonces> announcement = null;
		announcement = em.createQuery(query, Annonces.class).getResultList();

		return announcement;
	}

	public String findByLowerPrice(String sql_request) {
		sql_request = sql_request + "order by u.price ASC";

		return sql_request;
	}

	public String findByHigherPrice(String sql_request) {

		List<Annonces> announcement = null;

		sql_request = sql_request + "order by u.price DESC";

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
		try {
			annonce = (Annonces) em.createNamedQuery("Annonces.findByID").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
		}
		
		return annonce;
	}
	
	public Annonces findFavorites(String annonce_id) {
		Long id = Long.parseLong(annonce_id);
		Annonces annonce = new Annonces();
		try {
			annonce = (Annonces) em.createNamedQuery("Annonces.findFavorite").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
		}
		return annonce;
	}

	public Annonces setAnnounceSold(String parameter) {

		Annonces annonce = em.find(Annonces.class, parameter);
		annonce.setSold(1);
		return annonce;
	}

	public void addToFavoriteList(String id, User user) {
		
		List <User> tmp ;
		Annonces annonce = findById(id);
		tmp = annonce.getFavorites();
		tmp.add(user);
		annonce.setFavorites(tmp);
	}

	public Boolean findFavorite(User user, String id) {
		Boolean res = false;
		Annonces annonce = findById(id);
		List <User> users;
		if (annonce.getFavorites() != null) {
			users = annonce.getFavorites();
			for (User user_favorites : users) {
				if (user_favorites.getEmail().equals(user.getEmail()))
					res = true;
			}
		}
		return res;
	}
	

	public List<User> findFavorite(Long id) {
		Annonces annonce = em.find(Annonces.class, id);
		return annonce.getFavorites();
	}

	public void removeToFavoriteList(String id, User user) {
		Annonces annonce = findById(id);
		List <User> list = new ArrayList<>();
		for (User u : annonce.getFavorites()){
			if (!u.getEmail().equals(user.getEmail())){
				list.add(u);
			}
		}
		
		annonce.setFavorites(list);
	}

	public List<Annonces> findAllByFavorite(User user) {

		List<Annonces> announces = null;
		
		try {
			announces = (List<Annonces>) em.createNamedQuery("Annonces.findAllByFavorite")
					.setParameter("id", user.getId()).getResultList();
		} catch (Exception e) {
		}
		return announces;
	}


	public void resetFavorite(Long id) {
		Annonces reset = em.find(Annonces.class, id);
		reset.setFavorites(null);
	}

}
