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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "employee_executions")
@Access(AccessType.FIELD)
public class EmployeeExecution extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private Date date;

	@Column(name = "task_also")
	private String taskAlso;

	private int rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocationOrgan_id")
	private AllocationEmployee allocationEmployee;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> attachments = new HashSet<File>();

	public EmployeeExecution() {
		this.date = new Date();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(date);
		hcb.append(taskAlso);
		hcb.append(allocationEmployee);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EmployeeExecution)) {
			return false;
		}
		EmployeeExecution that = (EmployeeExecution) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(date, that.date);
		eb.append(taskAlso, that.taskAlso);
		eb.append(allocationEmployee, that.allocationEmployee);
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

	public Set<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<File> attachments) {
		this.attachments = attachments;
	}

	public AllocationEmployee getAllocationEmployee() {
		return allocationEmployee;
	}

	public void setAllocationEmployee(AllocationEmployee allocationEmployee) {
		this.allocationEmployee = allocationEmployee;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
