package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries({
    @NamedQuery(name="Administrator.find",
                query="SELECT u FROM Administrator u WHERE u.pseudo = :pseudo AND u.password = :password"),
}) 
@Table(name="ALDA_Administrator")
public class Administrator  implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  @Basic
  @Column(length = 30, nullable=true)
  private String pseudo;

 
  @Basic
  @Column(length = 20, nullable=false)
  private String password;
  
 
public Administrator(){
	
}
  
	Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


public String getPseudo() {
	return pseudo;
}


public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}


}