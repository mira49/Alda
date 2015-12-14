package Entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ALDA_user")
public class User {

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
  private String factor[];
  
  public String[] getFactor() {
	return factor;
}

public void setFactor(String[] factor) {
	this.factor = factor;
}

public User(){}
  
  /*public User(int i, String name,String firstName,String email ,String password,String adress,String phone){
	  this.id = (long) i;
	  this.name = name;
	  this.firstName = firstName;
	  this.email = email;
	  this.password = password;
	  this.address = adress;
	  this.phone = phone;
	  
  }*/
  
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