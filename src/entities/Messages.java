package entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Messages.getAllByUser",
                query="SELECT u FROM Messages u WHERE u.user.email = :email"),
    @NamedQuery(name="Messages.CountSoldAnnouncement",
    query="SELECT count(u.id) FROM Messages u WHERE u.user.email = :email AND u.notification = 1"),
})
public class Messages implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	@Column(length = 90, nullable = false)
	private String message;
	
	@Basic
	@Column(length = 90, nullable = false)
	private String sender_message;
	
	@ManyToOne
	private User user;
	
	@Basic
	@Column(length = 1, nullable = true)
	private int notification;
	
	
	public Messages(String message, String sender_message, User user, int notif){
		this.message = message;
		this.sender_message = sender_message;
		this.user = user;
		this.notification = notif;
	}
	
	public Messages(){}
	
	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender_message() {
		return sender_message;
	}

	public void setSender_message(String sender_message) {
		this.sender_message = sender_message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
