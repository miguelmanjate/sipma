package mz.ciuem.sipma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "allocation_organs")
@Cacheable(true)
public class AllocationOrgan extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "task_also")
	private String taskAlso;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "seen")
	private boolean  seen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ_id")
	private Organ organ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specified_action_id")
	private SpecifiedAction specifiedAction;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private Comment comment;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "allocationOrgan")
	private Set<OrganExecution> organExecutions = new HashSet<OrganExecution>();

	private boolean state;

	public AllocationOrgan() {

		this.state = false;
	}

	public int rate() {

		int rate = 0;
		for (OrganExecution organExecution : organExecutions) {
			rate += organExecution.getRate();
		}
		return rate;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(taskAlso);
		hcb.append(endDate);
		hcb.append(organ);
		hcb.append(role);
		hcb.append(specifiedAction);
		hcb.append(startDate);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllocationOrgan)) {
			return false;
		}
		AllocationOrgan that = (AllocationOrgan) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(taskAlso, that.taskAlso);
		eb.append(endDate, that.endDate);
		eb.append(organ, that.organ);
		eb.append(role, that.role);
		eb.append(specifiedAction, that.specifiedAction);
		eb.append(startDate, that.startDate);
		return eb.isEquals();
	}

	public SpecifiedAction getSpecifiedAction() {
		return specifiedAction;
	}

	public void setSpecifiedAction(SpecifiedAction specifiedAction) {
		this.specifiedAction = specifiedAction;
	}

	public String getTaskAlso() {
		return taskAlso;
	}

	public void setTaskAlso(String taskAlso) {
		this.taskAlso = taskAlso;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Set<OrganExecution> getOrganExecutions() {
		return organExecutions;
	}

	public void setOrganExecutions(Set<OrganExecution> organExecutions) {
		this.organExecutions = organExecutions;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	

}
