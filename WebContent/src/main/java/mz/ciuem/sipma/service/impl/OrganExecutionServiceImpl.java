package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.OrganExecutionDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.OrganExecution;
import mz.ciuem.sipma.service.FileService;
import mz.ciuem.sipma.service.OrganExecutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organExecutionService")
public class OrganExecutionServiceImpl extends
		GenericServiceImpl<OrganExecution> implements OrganExecutionService {

	@Autowired
	private OrganExecutionDao organExecutionDao;

	@Autowired
	private FileService fileService;

	@Override
	public OrganExecution create(OrganExecution organExecution) {

		for (File attach : organExecution.getAttachments()) {
			fileService.create(attach);
		}

		return specificDao.create(organExecution);
	}

	@Override
	public List<OrganExecution> filterByAllocation(AllocationOrgan oe) {
		return organExecutionDao.filterByAllocation(oe);
	}

	@Override
	public List<OrganExecution> filterByCycle(Cycle c) {
		// TODO Auto-generated method stub
		return organExecutionDao.filterByCycle(c);
	}

}
