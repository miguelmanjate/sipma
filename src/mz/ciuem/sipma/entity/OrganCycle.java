package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organ_cycles")
@Access(AccessType.FIELD)
public class OrganCycle extends IdEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "organ_id")
	private Organ organ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cicloOrgao")
	private List<DistDistribuicaoUnidade> distDistribuicaoUnidade;

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public List<DistDistribuicaoUnidade> getDistDistribuicaoUnidade() {
		return distDistribuicaoUnidade;
	}

	public void setDistDistribuicaoUnidade(
			List<DistDistribuicaoUnidade> distDistribuicaoUnidade) {
		this.distDistribuicaoUnidade = distDistribuicaoUnidade;
	}

}
