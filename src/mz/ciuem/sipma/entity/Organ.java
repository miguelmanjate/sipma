package mz.ciuem.sipma.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "organs", uniqueConstraints = @UniqueConstraint(columnNames = { "designation" }))
@Access(AccessType.FIELD)
public class Organ extends IdEntity {

	
	
	private static final long serialVersionUID = 1L;

	@Column(name = "initials")
	private String initials;
	
	@Column(name = "designation")
	private String designation;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organType")
	private OrganType organType;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "organs")
	private List<Cycle> cycles = new ArrayList<Cycle>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organ")
	private List<Sector> sectors = new ArrayList<Sector>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id")
	private Employee responsible;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan")
	private Plan plan;

	public String since() {

		Locale ptBr = new Locale("pt", "BR");

		SimpleDateFormat sdf = new SimpleDateFormat("MMM, yyyy", ptBr);

		return sdf.format(getCreated());
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(id);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Organ)) {
			return false;
		}
		Organ that = (Organ) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(id, that.id);
		return eb.isEquals();
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials.toUpperCase();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public OrganType getOrganType() {
		return organType;
	}

	public void setOrganType(OrganType organType) {
		this.organType = organType;
	}

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	@Override
	public String toString() {
		return this.designation;
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
