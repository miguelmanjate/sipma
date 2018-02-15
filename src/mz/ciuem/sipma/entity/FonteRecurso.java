package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="fonteRecurso")
public class FonteRecurso extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fonteRecurso")
	private List<BasedCalculating> baseCalculo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fonteRecurso")
	private List<DistParamOGE> distParamOGE;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fonteRecurso")
	private List<DistDistribuicaoUnidade> distDistribuicaoUnidade;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fonteRecurso")
	private List<DistOrcamentoUEM> distOrcamentoUEM;

}
