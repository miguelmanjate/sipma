package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.AllocationSectorDao;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.service.AllocationSectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("allocationSectorService")
public class AllocationSectorServiceImpl extends
		GenericServiceImpl<AllocationSector> implements AllocationSectorService {

	@Autowired
	private AllocationSectorDao allocationSectorDao;

	@Override
	public List<AllocationSector> filterByOrgan(Organ organ) {

		return allocationSectorDao.filterByOrgan(organ);
	}

	@Override
	public List<AllocationSector> filterIntegratedBySector(Sector sector) {
		return allocationSectorDao.filterIntegratedBySector(sector);
	}

	@Override
	public List<AllocationSector> filterBySector(Sector sector) {
		return allocationSectorDao.filterBySector(sector);
	}

}
