package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.EmployeeExecutionDao;
import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.EmployeeExecution;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.service.EmployeeExecutionService;
import mz.ciuem.sipma.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeExecutionService")
public class EmployeeExecutionServiceImpl extends
		GenericServiceImpl<EmployeeExecution> implements
		EmployeeExecutionService {

	@Autowired
	private EmployeeExecutionDao employeeExecutionDao;

	@Autowired
	private FileService fileService;

	@Override
	public EmployeeExecution create(EmployeeExecution employeeExecution) {

		for (File attach : employeeExecution.getAttachments()) {
			fileService.create(attach);
		}

		return specificDao.create(employeeExecution);
	}

	@Override
	public List<EmployeeExecution> filterByAllocation(AllocationEmployee ae) {
		return employeeExecutionDao.filterByAllocation(ae);
	}

}
