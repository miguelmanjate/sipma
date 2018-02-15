package mz.ciuem.sipma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.sipma.dao.PhaseOrganCycleDao;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.PhaseOrganCycle;
import mz.ciuem.sipma.service.PhaseOrganCycleService;

@Service("phaseOrganCycleService")
public class PhaseOrganCycleServiceImpl extends GenericServiceImpl<PhaseOrganCycle>implements PhaseOrganCycleService {

	@Autowired
	private PhaseOrganCycleDao phaseOrganCycleDao;



	@Override
	public List<PhaseOrganCycle> filterByOrgan(Organ organ) {
		// TODO Auto-generated method stub
		return phaseOrganCycleDao.filterByOrgan(organ);
	}

	@Override
	public List<PhaseOrganCycle> filterByState(String state) {
		// TODO Auto-generated method stub
		return phaseOrganCycleDao.filterByState(state);
	}

	@Override
	public List<PhaseOrganCycle> allNotAcepted() {
		// TODO Auto-generated method stub
		return phaseOrganCycleDao.allNotAcepted();
	}

}
