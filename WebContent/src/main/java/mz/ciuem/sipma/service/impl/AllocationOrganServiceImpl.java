package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.AllocationOrganDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.service.AllocationOrganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("allocationOrganService")
public class AllocationOrganServiceImpl extends
		GenericServiceImpl<AllocationOrgan> implements AllocationOrganService {

	@Autowired
	private AllocationOrganDao allocDao;
	
	@Override
	public List<AllocationOrgan> filterByOrgan(Organ organ) {
		return allocDao.filterByOrgan(organ);
	}

	@Override
	public List<AllocationOrgan> findAllNotIntegrated(Organ organ) {
		// TODO Auto-generated method stub
		return allocDao.findAllNotIntegrated(organ);
	}

	@Override
	public List<AllocationOrgan> filterIntegratedByOrgan(Organ organ) {
		// TODO Auto-generated method stub
		return allocDao.filterIntegratedByOrgan(organ);
	}


}
