package mz.ciuem.sipma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "phases_organ_cycles")
@Access(AccessType.FIELD)
public class PhaseOrganCycle extends IdEntity {

	private static final long serialVersionUID = -1976393788089101929L;

	@Column(name = "realization_percentage")
	private int realizationPercentage;

	@Column(name = "executed_activity")
	private String executedActivity;

	public Set<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<File> attachments) {
		this.attachments = attachments;
	}

	@Column(name = "realization_date")
	private Date realizationDate;

	@Column(name = "successfully")
	private boolean successfully;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "embarrassment_id")
	private Embarrassment embarrassment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phase_cycle_id")
	private PhaseCycle phaseCycle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ")
	private Organ organ;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> attachments = new HashSet<File>();
	
	private String acepted;
	
	public PhaseOrganCycle() {
		setAcepted("Pendente");
	}

	@PrePersist
	public void onRealizationDate() {
		realizationDate = new Date();
	}

	public int getRealizationPercentage() {
		return realizationPercentage;
	}

	public void setRealizationPercentage(int realizationPercentage) {
		this.realizationPercentage = realizationPercentage;
	}

	public String getExecutedActivity() {
		return executedActivity;
	}

	public void setExecutedActivity(String executedActivity) {
		this.executedActivity = executedActivity;
	}

	public Date getRealizationDate() {
		return realizationDate;
	}

	public boolean isSuccessfully() {
		return successfully;
	}

	public void setSuccessfully(boolean successfully) {
		this.successfully = successfully;
	}

	public Embarrassment getEmbarrassment() {
		return embarrassment;
	}

	public void setEmbarrassment(Embarrassment embarrassment) {
		this.embarrassment = embarrassment;
	}

	public PhaseCycle getPhaseCycle() {
		return phaseCycle;
	}

	public void setPhaseCycle(PhaseCycle phaseCycle) {
		this.phaseCycle = phaseCycle;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public void setAcepted(String acepted) {
		this.acepted = acepted;
	}

	public String getAcepted() {
		return acepted;
	}

}
