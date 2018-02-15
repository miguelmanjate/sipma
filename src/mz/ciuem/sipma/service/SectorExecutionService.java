package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SectorExecution;

public interface SectorExecutionService extends GenericService<SectorExecution> {

	public List<SectorExecution> filterByAllocation(AllocationSector se);

	public List<SectorExecution> filterByAllocationOrgan(AllocationOrgan oa);

	public List<SectorExecution> filterExecutionBySector(Sector sector);
}
