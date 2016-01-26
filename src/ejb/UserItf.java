package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserItf {
	public void create(User user);
	public void delete(Long id);
	public User findUser(String email, String password);
	public List<User> getUsers();
	public User find(String email);
	public User update_or_insert(User account_modify, User temp);
	public String[] findFactor(User user);
	public String findFactorBeta(User user);
	public User updateFactor(User user_tmp, User user);
	public void UpdatePassword(User account_modify, User temp);
	public User findByUser(User user);
	public void updateDate(User user_connexion, Date dt);
	public void updateDateDeco(User user, Date dateFormatee);
}
