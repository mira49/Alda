package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name="User.findAll",
                query="SELECT u FROM User u"),
    @NamedQuery(name="User.findUserConnexion",
                query="SELECT u FROM User u WHERE u.email = :email AND u.password = :password"),
    @NamedQuery(name="User.findUserByEmail",
    query="SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name="User.deleteUser",
    query="SELECT u FROM User u WHERE u.id = :id"),
}) 
@Table(name="ALDA_user")
public class User  implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  @Basic
  @Column(length = 30, nullable=true)
  private String name;

 
  @Basic
  @Column(length = 30, nullable=true)
  private String firstName;
  
  @Basic
  @Column(length = 60, nullable=false, unique=true)
  private String email;
  
  @Basic
  @Column(length = 20, nullable=false)
  private String password;
  
  @Basic
  @Column(length = 250, nullable=true)
  private String address;
  
  @Basic
  @Column(length = 20, nullable=true)
  private String phone;
  
  @Basic
  @Column(nullable=true)
  private String factor;
  
  @Column(length = 50, nullable = true)
  private Date date_connexion;
  
  
  @Column(length = 50, nullable = true)
  private Date date_deconnection;
  
  public User(){}
  
  public User(String email, String password){
	  this.email = email;
	  this.password = password;
  }
  
  public Date getDate_connexion() {
	return date_connexion;
  }

public void setDate_connexion(Date date_connexion) {
	this.date_connexion = date_connexion;
}

public Date getDate_deconnection() {
	return date_deconnection;
}

public void setDate_deconnection(Date date_deconnection) {
	this.date_deconnection = date_deconnection;
}

@OneToMany(mappedBy="user", cascade = { CascadeType.ALL})
	List<Annonces> announcements;
 
@ManyToMany(mappedBy="favorites")
List<Annonces> favorite_user;

  public List<Annonces> getFavorite_user() {
	return favorite_user;
}

@OneToMany(mappedBy="user", cascade = { CascadeType.ALL})
List <Messages> messages;


public void setFavorite_user(List<Annonces> favorite_user) {
	this.favorite_user = favorite_user;
}

public List<Annonces> getAnnouncements() {
	return announcements;
}

public void setAnnouncements(List<Annonces> announcements) {
	this.announcements = announcements;
}

public String getFactor() {
	return factor;
}

public void setFactor(String factor) {
	this.factor = factor;
}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

}