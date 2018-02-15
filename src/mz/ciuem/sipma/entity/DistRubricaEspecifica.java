package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "distRubricaEspecifica")
public class DistRubricaEspecifica extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distRubricaEspecifica")
	private List<DistTempDistribuicaoUEM> distTempDistribuicaoUEM;
	
}
