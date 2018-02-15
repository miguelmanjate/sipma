package mz.ciuem.sipma.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="distParamOGE")
public class DistParamOGE extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distParamAreaGoverno_id")
	private DistParamAreaGoverno distParamAreaGoverno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fonteRecurso_id")
	private FonteRecurso fonteRecurso;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rubricaUEM_id")
	private RubricaUEM rubricaUEM;

}
