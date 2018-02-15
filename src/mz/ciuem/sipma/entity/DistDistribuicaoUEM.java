package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="distDistribuicaoUEM")
public class DistDistribuicaoUEM extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distOrcamentoUEM")
	private DistOrcamentoUEM distOrcamentoUEM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distDistribuicaoUEM")
	private List<DistDistribuicaoUnidade> distDistribuicaoUnidade;

}
