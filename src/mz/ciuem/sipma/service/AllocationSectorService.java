package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

public interface AllocationSectorService extends
		GenericService<AllocationSector> {

	public List<AllocationSector> filterByOrgan(Organ organ);

	public List<AllocationSector> filterIntegratedBySector(Sector sector);

	public List<AllocationSector> filterBySector(Sector sector);

}
