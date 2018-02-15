package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.OrganExecution;

public interface OrganExecutionDao extends GenericDao<OrganExecution> {

	public List<OrganExecution> filterByAllocation(AllocationOrgan ao);

	public List<OrganExecution> filterByCycle(Cycle c);
}
