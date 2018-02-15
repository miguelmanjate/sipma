package mz.ciuem.sipma.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="distUnidadeArea")
public class DistUnidadeArea extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distUnidadeArea")
	private List<DistParamAreaPrioritaria> distParamAreaPrioritaria;

}
