package Entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	@Column(length = 90, nullable = false)
	private String message;
	
	@Basic
	@Column(length = 90, nullable = false)
	private String sender_message;
	
	@Basic
	@Column(length = 90, nullable = false)
	private String receiver_message;
	
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
		message = message;
	}

	public String getSender_message() {
		return sender_message;
	}

	public void setSender_message(String sender_message) {
		this.sender_message = sender_message;
	}


	public String getReceiver_message() {
		return receiver_message;
	}

	public void setReceiver_message(String receiver_message) {
		this.receiver_message = receiver_message;
	}
	
}
