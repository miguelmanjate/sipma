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
@Table(name = "sector_executions")
@Access(AccessType.FIELD)
public class SectorExecution extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private Date date;

	@Column(name = "task_also")
	private String taskAlso;

	@Column(name = "rate")
	private int rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocation_sector_id")
	private AllocationSector allocationSector;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<File> attachments = new HashSet<File>();

	private int totalRate;

	public int totalRate() {

		setTotalRate(rate + 1);

		return totalRate;
	}

	@PrePersist
	public void onDate() {
		date = new Date();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(date);
		hcb.append(taskAlso);
		hcb.append(allocationSector);
		hcb.append(attachments);
		hcb.append(rate);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SectorExecution)) {
			return false;
		}
		SectorExecution that = (SectorExecution) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(date, that.date);
		eb.append(taskAlso, that.taskAlso);
		eb.append(allocationSector, that.allocationSector);
		eb.append(attachments, that.attachments);
		eb.append(rate, that.rate);
		return eb.isEquals();

	}

	public String getTaskAlso() {
		return taskAlso;
	}

	public void setTaskAlso(String taskAlso) {
		this.taskAlso = taskAlso;
	}

	public AllocationSector getAllocationSector() {
		return allocationSector;
	}

	public void setAllocationSector(AllocationSector allocationSector) {
		this.allocationSector = allocationSector;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(int totalRate) {
		this.totalRate = totalRate;
	}

}
