package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

public interface AllocationSectorDao extends GenericDao<AllocationSector> {

	public List<AllocationSector> filterByOrgan(Organ organ);

	public List<AllocationSector> filterIntegratedBySector(Sector sector);
	
	public List<AllocationSector> filterBySector(Sector sector);

}
