package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SectorExecutionDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SectorExecution;
import mz.ciuem.sipma.service.FileService;
import mz.ciuem.sipma.service.SectorExecutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sectorExecutionService")
public class SectorExecutionServiceImpl extends
		GenericServiceImpl<SectorExecution> implements SectorExecutionService {

	@Autowired
	private SectorExecutionDao sectorExecutionDao;

	@Autowired
	private FileService fileService;

	@Override
	public SectorExecution create(SectorExecution sectorExecution) {

		for (File attach : sectorExecution.getAttachments()) {
			fileService.create(attach);
		}

		return specificDao.create(sectorExecution);
	}

	@Override
	public List<SectorExecution> filterByAllocation(AllocationSector se) {
		return sectorExecutionDao.filterByAllocation(se);
	}

	@Override
	public List<SectorExecution> filterByAllocationOrgan(AllocationOrgan oa) {
		return sectorExecutionDao.filterByAllocationOrgan(oa);
	}

	@Override
	public List<SectorExecution> filterExecutionBySector(Sector sector) {
		return sectorExecutionDao.filterExecutionBySector(sector);
	}

}
