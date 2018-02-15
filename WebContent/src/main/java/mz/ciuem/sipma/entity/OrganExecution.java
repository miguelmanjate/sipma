package mz.ciuem.sipma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "Organ_executions")
@Access(AccessType.FIELD)
public class OrganExecution extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private Date date;

	@Column(name = "task_also")
	private String taskAlso;

	@ManyToOne
	@JoinColumn(name = "allocationOrgan_id")
	private AllocationOrgan allocationOrgan;

	@Column(name = "rate")
	private int rate;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> attachments = new HashSet<File>();

	@PrePersist
	public void onDate() {
		date = new Date();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(date);
		hcb.append(taskAlso);
		hcb.append(allocationOrgan);
		hcb.append(attachments);
		hcb.append(rate);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrganExecution)) {
			return false;
		}
		OrganExecution that = (OrganExecution) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(date, that.date);
		eb.append(taskAlso, that.taskAlso);
		eb.append(allocationOrgan, that.allocationOrgan);
		eb.append(attachments, that.attachments);
		eb.append(rate, that.rate);
		return eb.isEquals();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTaskAlso() {
		return taskAlso;
	}

	public void setTaskAlso(String taskAlso) {
		this.taskAlso = taskAlso;
	}

	public AllocationOrgan getAllocationOrgan() {
		return allocationOrgan;
	}

	public void setAllocationOrgan(AllocationOrgan allocationOrgan) {
		this.allocationOrgan = allocationOrgan;
	}

	public Set<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<File> attachments) {
		this.attachments = attachments;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
