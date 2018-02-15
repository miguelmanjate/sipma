package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.EmployeeExecution;

public interface EmployeeExecutionService extends
		GenericService<EmployeeExecution> {

	public List<EmployeeExecution> filterByAllocation(AllocationEmployee ae);
}
