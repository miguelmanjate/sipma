package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "priority_levels")
@Access(AccessType.FIELD)
public class PriorityLevel extends IdEntity {

	
	private static final long serialVersionUID = 2516091261561709018L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firstCriteria_id")
	private Criteria firstCriteria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondCriteria_id")
	private Criteria secondCriteria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thirdCriteria_id")
	private Criteria thirdCriteria;

	public int priority() {

		return firstCriteria.getWeight() + secondCriteria.getWeight()
				+ thirdCriteria.getWeight();
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
		if (!(obj instanceof PriorityLevel)) {
			return false;
		}
		PriorityLevel that = (PriorityLevel) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(id, that.id);
		return eb.isEquals();
	}

	public Criteria getFirstCriteria() {
		return firstCriteria;
	}

	public void setFirstCriteria(Criteria firstCriteria) {
		this.firstCriteria = firstCriteria;
	}

	public Criteria getSecondCriteria() {
		return secondCriteria;
	}

	public void setSecondCriteria(Criteria secondCriteria) {
		this.secondCriteria = secondCriteria;
	}

	public Criteria getThirdCriteria() {
		return thirdCriteria;
	}

	public void setThirdCriteria(Criteria thirdCriteria) {
		this.thirdCriteria = thirdCriteria;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.priority()+"";
	}
}
