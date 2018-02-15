package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="")
public class DistParamAreaGoverno extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distParamFuncao_id")
	private DistParamFuncao distParamFuncao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distParamAreaGoverno")
	private List<DistParamOGE> distParamOGE;

}
