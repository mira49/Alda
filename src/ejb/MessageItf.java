package ejb;

import java.util.List;

import javax.ejb.Local;

import entities.Annonces;
import entities.Messages;
import entities.User;

@Local
public interface MessageItf {
	
	public void create(User user, String message, Annonces attribute);
	public void create(Messages messages);
	public List<Messages> findAllByUser(User user);
	public long findAnnouncementSold(String email);
	public void update(Messages message);
}
