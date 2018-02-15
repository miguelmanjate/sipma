package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;

public interface OrganDao extends GenericDao<Organ> {

	public Organ getOrganByResponsible(Employee e);
	
	public List<Organ> getFilteredOrgans(Organ organ);
	
	public List<Organ> findAllWithoutCycles();
	
	public List<Organ> findAllNotInCycle(Cycle cycle);
}
