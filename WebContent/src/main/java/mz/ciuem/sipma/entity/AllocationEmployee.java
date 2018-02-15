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

@Entity
@Table(name = "allocation_employees")
@Access(AccessType.FIELD)
public class AllocationEmployee extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "task")
	private String task;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocationSector_id")
	private AllocationSector allocationSector;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id")
	private Employee responsible;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_sector_id")
	private SubSector subSector;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "allocationEmployee")
	private Set<EmployeeExecution> employeeExecutions = new HashSet<EmployeeExecution>();

	public int rate() {

		int rate = 0;
		for (EmployeeExecution employeeExecution : employeeExecutions) {
			rate += employeeExecution.getRate();
		}
		return rate;
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}

	public SubSector getSubSector() {
		return subSector;
	}

	public void setSubSector(SubSector subSector) {
		this.subSector = subSector;
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public AllocationSector getAllocationSector() {
		return allocationSector;
	}

	public void setAllocationSector(AllocationSector allocationSector) {
		this.allocationSector = allocationSector;
	}

	public Set<EmployeeExecution> getEmployeeExecutions() {
		return employeeExecutions;
	}

	public void setEmployeeExecutions(Set<EmployeeExecution> employeeExecutions) {
		this.employeeExecutions = employeeExecutions;
	}

}
