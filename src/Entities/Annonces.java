package Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Annonces {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(length = 30, nullable = false)
	private String name;

	@Basic
	@Column(length = 30, nullable = false)
	private int surface;

	@Basic
	@Column(length = 3000, nullable = true)
	private String description;

	@Basic
	@Column(length = 30, nullable = false)
	private String emplacement;
	
	@Column(nullable =true)
	private List<String> img = new ArrayList<>();

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "email", referencedColumnName = "email")
	private User User_ID;

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

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

	public User getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(User user_ID) {
		User_ID = user_ID;
	}

}
