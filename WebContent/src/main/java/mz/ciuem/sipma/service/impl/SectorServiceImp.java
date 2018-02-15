package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SectorDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.service.SectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sectorService")
public class SectorServiceImp extends GenericServiceImpl<Sector> implements
		SectorService {

	@Autowired
	private SectorDao sectorDao;

	@Override
	public List<Sector> filterByOrgan(Organ organ) {
		return sectorDao.filterByOrgan(organ);
	}

	@Override
	public Sector getSectorByResponsible(Employee e) {
		return sectorDao.getSectorByResponsible(e);
	}

	@Override
	public List<Sector> getFilteredOrsectors(Sector sector) {
		return sectorDao.getFilteredSectors(sector);
	}

	@Override
	public List<Sector> getOrganBySector(Organ organ) {
		return sectorDao.getOrganBySector(organ);
	}

}