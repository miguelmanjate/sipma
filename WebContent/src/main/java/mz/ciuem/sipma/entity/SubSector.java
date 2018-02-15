package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "sub_sectors")
@Access(AccessType.FIELD)
public class SubSector extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "initials")
	private String initials;

	@Column(name = "designation")
	private String designation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector")
	private Sector sector;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(initials);
		hcb.append(designation);
		hcb.append(sector);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SubSector)) {
			return false;
		}
		SubSector that = (SubSector) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(initials, that.initials);
		eb.append(designation, that.designation);
		eb.append(sector, that.sector);
		return eb.isEquals();
	}



	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

}
