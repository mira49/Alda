package ejb;

import java.util.List;

import javax.ejb.Local;

import entities.Annonces;
import entities.User;

@Local
public interface AnnouncementItf {
	
	public void create(Annonces n);
	public List<Annonces> getAnnoucement_user(User user);
	public void delete(Long idA);
	public List<Annonces> findAll();
	public List<Annonces> findByFactor(String query);
	public String findByLowerPrice(String sql_request);
	public String findByHigherPrice(String sql_request);
	public String findByPostalCode(String sql_request);
	public Annonces findById(String annonce_id);
	public Annonces findFavorites(String annonce_id);
	public Annonces setAnnounceSold(String parameter);
	public void addToFavoriteList(String id, User user);
	public Boolean findFavorite(User user, String id);
	public List<User> findFavorite(Long id);
	public void removeToFavoriteList(String id, User user);
	public List<Annonces> findAllByFavorite(User user);
	public void resetFavorite(Long id);
}
