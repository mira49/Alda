package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries({
    @NamedQuery(name="Annonces.AnnouncementUser",
                query="SELECT u FROM Annonces u WHERE u.user.email = :email"),
    @NamedQuery(name="Annonces.AnnouncementMerge",
    query="SELECT u FROM Annonces u WHERE u.user.email = :email AND u.date = :date"),
    @NamedQuery(name="Annonces.deleteAnnounce",
    query="delete from Annonces u where u.id = :id"),
    @NamedQuery(name="Annonces.findAll",
    query="SELECT u FROM Annonces u where u.sold = 0"),
    @NamedQuery(name="Annonces.findByID",
    query="SELECT u FROM Annonces u where u.id = :id"),
    @NamedQuery(name="Annonces.findFavorite",
    query="SELECT u FROM Annonces u join  u.favorites t where u.id = :id"),
    @NamedQuery(name="Annonces.findAllByFavorite",
    query="SELECT u FROM Annonces u join u.favorites t where t.id = :id "),
})
public class Annonces  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private Date date;

	@Basic
	@Column(length = 30, nullable = false)
	private int surface;

	@Basic
	@Column(length = 30, nullable = false)
	private int price;
	
	@Basic
	@Column(length = 5, nullable = false)
	private int postal_code;
	
	@Basic
	@Column(length = 3000, nullable = true)
	private String description;
	
	@ManyToMany
	private List<User> favorites;
	
	@Basic
	@Column(length = 30, nullable = true)
	private String town;
	
	@Basic
	@Column(length = 1, nullable = false)
	private int sold;


	@Basic
	@Column(length = 200, nullable = false)
	private String image1;

	@Basic
	@Column(length = 200, nullable = false)
	private String image2;
	
	@Basic
	@Column(length = 200, nullable = true)
	private String image3;

	@ManyToOne
	@JoinColumn(name = "email", referencedColumnName = "email" )
	private User user;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date dt) {
		this.date = dt;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}
	
	public List<User> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<User> favorites) {
		this.favorites = favorites;
	}

}
