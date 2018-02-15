package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="distDistribuicaoUnidade")
public class DistDistribuicaoUnidade extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distDistribuicaoUEM_id")
	private DistDistribuicaoUEM distDistribuicaoUEM;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cicloOrgao_id")
	private OrganCycle cicloOrgao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fonteRecurso_id")
	private FonteRecurso fonteRecurso;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distDistribuicaoUnidade")
	private List<Reajuste> reajuste;
	

}
