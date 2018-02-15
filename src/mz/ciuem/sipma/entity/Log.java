package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
@Access(AccessType.FIELD)
public class Log extends IdEntity {

	private static final long serialVersionUID = -3212773037810571222L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "logged_user_id")
	private User loggedUser;

	private String description;

	public Log() {

	}

	public Log(User loggedUser, String description) {
		this.loggedUser = loggedUser;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getLoggedUser() {
		return loggedUser;
	}
}
