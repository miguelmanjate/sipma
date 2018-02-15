package mz.ciuem.sipma.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="distParamAreaPrioritaria")
public class DistParamAreaPrioritaria extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distUnidadeArea_id")
	private DistUnidadeArea distUnidadeArea;

}
