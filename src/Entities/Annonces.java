package Entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Annonces  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(length = 30, nullable = false)
	private String name;
	
	@Basic
	@Column(length = 50, nullable = false)
    private String date;

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

	@Basic
	@Column(length = 30, nullable = false)
	private String town;

	@Basic
	@Column(length = 50, nullable = false)
	private String image1;

	@Basic
	@Column(length = 50, nullable = false)
	private String image2;
	
	@Basic
	@Column(length = 50, nullable = true)
	private String image3;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "email", referencedColumnName = "name" )
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

}
