package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "sectors")
@Access(AccessType.FIELD)
public class Sector extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "initials")
	private String initials;

	@Column(name = "designation")
	private String designation;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organ")
	private Organ organ;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sector")
	private List<SubSector> subSectors = new ArrayList<SubSector>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sector")
	private List<Employee> employees = new ArrayList<Employee>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id")
	private Employee responsible;
	
	@Column(name = "extetion")
	private String extetion;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(initials);
		hcb.append(designation);
		hcb.append(extetion);
		hcb.append(organ);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sector)) {
			return false;
		}
		Sector that = (Sector) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(initials, that.initials);
		eb.append(designation, that.designation);
		eb.append(organ, that.organ);
		eb.append(extetion, that.extetion);
		return eb.isEquals();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public List<SubSector> getSubSectors() {
		return subSectors;
	}

	public void setSubSectors(List<SubSector> subSectors) {
		this.subSectors = subSectors;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.designation;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getExtetion() {
		return extetion;
	}

	public void setExtetion(String extetion) {
		this.extetion = extetion;
	}
}
