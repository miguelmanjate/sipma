package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organ_cycles")
@Access(AccessType.FIELD)
public class OrganCycle extends IdEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "organ_id")
	private Organ organ;

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

}
