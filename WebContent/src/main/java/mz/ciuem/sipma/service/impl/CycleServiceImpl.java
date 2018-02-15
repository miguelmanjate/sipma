package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.CycleDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cycleService")
public class CycleServiceImpl extends GenericServiceImpl<Cycle> implements
		CycleService {

	@Autowired
	private CycleDao cycleDao;

	@Autowired
	private FileService fileService;

	@Override
	public Cycle create(Cycle cycle) {

		for (File file : cycle.getAttachments()) {
			fileService.create(file);
		}
		return cycleDao.create(cycle);
	}

	@Override
	public List<Cycle> cyclesByOrgan(Organ organ) {
		// TODO Auto-generated method stub
		return cycleDao.cyclesByOrgan(organ);
	}

	@Override
	public List<Cycle> findByState(String state) {
		// TODO Auto-generated method stub
		return cycleDao.findByState(state);
	}

	@Override
	public List<Cycle> findByOrganAndState(Organ organ, String state) {
		// TODO Auto-generated method stub
		return cycleDao.findByOrganAndState(organ, state);
	}
}
