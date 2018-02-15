package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "identity_document_types")
@Access(AccessType.FIELD)
public class IdentityDocumentType extends IdEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "names")
	private String name;

	@Column(name = "descriptions")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
