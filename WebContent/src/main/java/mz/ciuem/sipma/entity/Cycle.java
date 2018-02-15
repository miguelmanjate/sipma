package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "cycles")
@Access(AccessType.FIELD)
public class Cycle extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "state")
	private String state;

	@Column(name = "type")
	private String type;

	@Column(name = "subject")
	private String subject;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cycle_organ", joinColumns = {
			@JoinColumn(name = "cycle_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "organ_id", nullable = false, updatable = false) })
	private Set<Organ> organs = new HashSet<Organ>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cycle")
	private List<PhaseCycle> phaseCycles = new ArrayList<PhaseCycle>();

	private String description;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> attachments = new HashSet<File>();

	public Cycle() {
		this.type = "Normal";
		this.state = "Stopped";
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(code);
		hcb.append(startDate);
		hcb.append(endDate);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cycle)) {
			return false;
		}
		Cycle that = (Cycle) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(code, that.code);
		eb.append(startDate, that.startDate);
		eb.append(endDate, that.endDate);
		return eb.isEquals();
	}

	public int rate() {
		int rate = 0;
		for (PhaseCycle phaseCycle : phaseCycles) {
			rate += phaseCycle.rate();
		}
		return rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PhaseCycle> getPhaseCycles() {
		return phaseCycles;
	}

	public void setPhaseCycles(List<PhaseCycle> phaseCycles) {
		this.phaseCycles = phaseCycles;
	}

	public Set<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(Set<Organ> organs) {
		this.organs = organs;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<File> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code + " - " + this.subject;
	}
}
