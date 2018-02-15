package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "organ_types")
@Access(AccessType.FIELD)
public class OrganType extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "initials")
	private String initials;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organType")
	private List<Organ> organs = new ArrayList<Organ>();

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(initials);
		hcb.append(title);
		hcb.append(description);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrganType)) {
			return false;
		}
		OrganType that = (OrganType) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(initials, that.initials);
		eb.append(title, that.title);
		eb.append(description, that.description);
		return eb.isEquals();
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
