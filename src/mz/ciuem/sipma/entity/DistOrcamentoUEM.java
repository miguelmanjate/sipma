package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="distOrcamentoUEM")
public class DistOrcamentoUEM extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ciclo_id")
	private Cycle ciclo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distOrcamentoUEM_id")
	private DistOrcamentoUEM distOrcamentoUEM;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fonteRecurso_id")
	private FonteRecurso fonteRecurso;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rubricaUEM_id")
	private RubricaUEM rubricaUEM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distOrcamentoUEM")
	private List<DistDistribuicaoUEM> distDistribuicaoUEM;

}
