package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.PhaseCycleDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.PhaseCycle;
import mz.ciuem.sipma.service.PhaseCycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("phaseCycleService")
public class PhaseCycleServiceImpl extends GenericServiceImpl<PhaseCycle>
		implements PhaseCycleService {

	@Autowired
	private PhaseCycleDao phaseCycleDao;
	
	@Override
	public List<PhaseCycle> filterByCycle(Cycle cycle) {
		// TODO Auto-generated method stub
		return phaseCycleDao.filterByCycle(cycle);
	}

}
