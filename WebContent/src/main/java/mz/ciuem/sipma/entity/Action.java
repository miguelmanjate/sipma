package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import mz.ciuem.sipma.model.IdEventEntity;

@Entity
@Table(name = "actions")
@Cacheable(true)
public class Action extends IdEventEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "designation")
	private String designation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "strategic_objective_id")
	private StrategicObjective strategicObjective;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "criticalArea_id")
	private CriticalArea criticalArea;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_level_id")
	private PriorityLevel priorityLevel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ_id")
	private Organ organ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_id")
	private Cycle cycle;

	@SuppressWarnings("unused")
	private Float budget;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "action")
	private List<SpecifiedAction> specifiedActions = new ArrayList<SpecifiedAction>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan")
	private Plan plan;
	
	private String nature;

	public Action() {
		priorityLevel = new PriorityLevel();

		setBudget(baseCalc());
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(designation);
		hcb.append(strategicObjective);
		hcb.append(criticalArea);
		hcb.append(cycle);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Action)) {
			return false;
		}
		Action that = (Action) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(designation, that.designation);
		eb.append(criticalArea, that.criticalArea);
		eb.append(strategicObjective, that.strategicObjective);
		eb.append(cycle, that.cycle);
		return eb.isEquals();
	}

	private Float baseCalc() {
		Float value = 0F;
		for (SpecifiedAction sa : specifiedActions) {
			for (BasedCalculating bc : sa.getBasedCalcs()) {
				value += bc.getCost();
			}
		}
		return value;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public StrategicObjective getStrategicObjective() {
		return strategicObjective;
	}

	public void setStrategicObjective(StrategicObjective strategicObjective) {
		this.strategicObjective = strategicObjective;
	}

	public CriticalArea getCriticalArea() {
		return criticalArea;
	}

	public void setCriticalArea(CriticalArea criticalArea) {
		this.criticalArea = criticalArea;
	}

	public List<SpecifiedAction> getSpecifiedActions() {
		return specifiedActions;
	}

	public void setSpecifiedActions(List<SpecifiedAction> specifiedActions) {
		this.specifiedActions = specifiedActions;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public PriorityLevel getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(PriorityLevel priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public Float getBudget() {
		return baseCalc();
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
}
