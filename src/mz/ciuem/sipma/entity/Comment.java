package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
@Access(AccessType.FIELD)
public class Comment extends IdEntity {

	private static final long serialVersionUID = 905362670492693883L;
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
