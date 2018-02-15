package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.OrganExecution;

public interface OrganExecutionService extends GenericService<OrganExecution> {

	public List<OrganExecution> filterByAllocation(AllocationOrgan oe);

	public List<OrganExecution> filterByCycle(Cycle c);
}
