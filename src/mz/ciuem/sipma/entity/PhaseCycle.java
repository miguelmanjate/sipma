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
@Table(name = "phase_cycles")
@Access(AccessType.FIELD)
public class PhaseCycle extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private int code;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "phase_id")
	private Phase phase;

	@ManyToOne
	@JoinColumn(name = "cycle_id")
	private Cycle cycle;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phaseCycle")
	private List<PhaseOrganCycle> phasesOrganCycles = new ArrayList<PhaseOrganCycle>();

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(code);
		hcb.append(startDate);
		hcb.append(phase);
		hcb.append(cycle);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PhaseCycle)) {
			return false;
		}
		PhaseCycle that = (PhaseCycle) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(code, that.code);
		eb.append(startDate, that.startDate);
		eb.append(phase, that.phase);
		eb.append(cycle, that.cycle);
		return eb.isEquals();
	}

	public int rate() {
		int rate = 0;

		for (PhaseOrganCycle phaseOrganCycle : phasesOrganCycles) {
			rate += phaseOrganCycle.getRealizationPercentage();
		}
		return rate;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public List<PhaseOrganCycle> getPhasesOrganCycles() {
		return phasesOrganCycles;
	}

	public void setPhasesOrganCycles(List<PhaseOrganCycle> phasesOrganCycles) {
		this.phasesOrganCycles = phasesOrganCycles;
	}

}
