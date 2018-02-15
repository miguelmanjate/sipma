package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.OrganDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.service.OrganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organService")
public class OrganServiceImpl extends GenericServiceImpl<Organ> implements
		OrganService {

	@Autowired
	private OrganDao organDao;

	@Override
	public Organ getOrganByResponsible(Employee e) {
		// TODO Auto-generated method stub
		return organDao.getOrganByResponsible(e);
	}

	@Override
	public List<Organ> getFilteredOrgans(Organ organ) {
		// TODO Auto-generated method stub
		return organDao.getFilteredOrgans(organ);
	}

	@Override
	public List<Organ> findAllWithoutCycles() {
		// TODO Auto-generated method stub
		return organDao.findAllWithoutCycles();
	}

	@Override
	public List<Organ> findAllNotInCycle(Cycle cycle) {
		// TODO Auto-generated method stub
		return organDao.findAllNotInCycle(cycle);
	}

}
