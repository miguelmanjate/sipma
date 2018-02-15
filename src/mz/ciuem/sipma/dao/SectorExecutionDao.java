package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SectorExecution;

public interface SectorExecutionDao extends GenericDao<SectorExecution> {

	public List<SectorExecution> filterByAllocation(AllocationSector as);

	public List<SectorExecution> filterByAllocationOrgan(AllocationOrgan oa);

	public List<SectorExecution> filterExecutionBySector(Sector sector);
}
