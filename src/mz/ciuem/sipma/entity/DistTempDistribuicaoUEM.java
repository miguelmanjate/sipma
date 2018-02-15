package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="distTempDistribuicaoUEM")
public class DistTempDistribuicaoUEM extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distRubricaEspecifica")
	private DistRubricaEspecifica distRubricaEspecifica;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distTempDistribuicaoUEM_id")
	private RubricaUEM rubricaUEM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distTempDistribuicaoUEM")
	private List<DistTemplateDistribuicaoUnidade> distTemplateDistribuicaoUnidade;

}
