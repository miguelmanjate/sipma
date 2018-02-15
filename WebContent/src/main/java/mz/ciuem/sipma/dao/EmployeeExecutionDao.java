package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.EmployeeExecution;

public interface EmployeeExecutionDao extends GenericDao<EmployeeExecution> {

	public List<EmployeeExecution> filterByAllocation(AllocationEmployee ae);
}
