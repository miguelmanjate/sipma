package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="rubricaUEM")
public class RubricaUEM extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rubrica_id")
	private Rubric rubrica;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubricaUEM")
	private List<DistTempDistribuicaoUEM> distTempDistribuicaoUEM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubricaUEM")
	private List<DistOrcamentoUEM> distOrcamentoUEM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubricaUEM")
	private List<DistParamOGE> distParamOGE;

}
