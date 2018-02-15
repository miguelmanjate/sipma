package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SubSectorDao;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;
import mz.ciuem.sipma.service.SubSectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subSectorService")
public class SubSectorServiceImpl extends GenericServiceImpl<SubSector>
		implements SubSectorService {

	@Autowired
	private SubSectorDao subSectorDao;
	
	@Override
	public List<SubSector> getSubSectorsBySector(Sector sector) {
		// TODO Auto-generated method stub
		return subSectorDao.getSubSectorsBySector(sector);
	}

	@Override
	public List<SubSector> getSubSectorsByOrgan(Organ organ) {
		// TODO Auto-generated method stub
		return subSectorDao.getSubSectorsByOrgan(organ);
	}

	

}
