package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "specified_actions")
@Access(AccessType.FIELD)
public class SpecifiedAction extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "designation")
	private String designation;

	@Column(name = "start_date")
	private Date beginDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "action_id", nullable = true)
	private Action action;

	@Column(name = "ambit")
	private String ambit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment", nullable=true)
	private Attachment attachment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accaoEspecifica")
	private List<Reajuste> reajuste;

	@Column(name = "is_budget")
	private boolean isBudget;

	public boolean isBudget() {
		return isBudget;
	}

	public void setBudget(boolean isBudget) {
		this.isBudget = isBudget;
	}
	
	public SpecifiedAction(){
		isBudget=true;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "specifiedAction")
	private List<BasedCalculating> basedCalcs = new ArrayList<BasedCalculating>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "")
	private List<AllocationOrgan> allocations = new ArrayList<AllocationOrgan>();

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(beginDate);
		hcb.append(designation);
		hcb.append(endDate);
		hcb.append(action);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SpecifiedAction)) {
			return false;
		}
		SpecifiedAction that = (SpecifiedAction) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(beginDate, that.beginDate);
		eb.append(designation, that.designation);
		eb.append(endDate, that.endDate);
		eb.append(action, that.action);
		return eb.isEquals();
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public List<BasedCalculating> getBasedCalcs() {
		return basedCalcs;
	}

	public void setBasedCalcs(List<BasedCalculating> basedCalcs) {
		this.basedCalcs = basedCalcs;
	}

	public List<AllocationOrgan> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOrgan> allocations) {
		this.allocations = allocations;
	}

	public String getAmbit() {
		return ambit;
	}

	public void setAmbit(String ambit) {
		this.ambit = ambit;
	}
}
