package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;

public interface AllocationEmployeeService extends
		GenericService<AllocationEmployee> {

	public List<AllocationEmployee> filterBySector(Sector sector);

	public List<AllocationEmployee> filterByEmployee(Employee employee);

	public List<AllocationEmployee> filterBySectorAllocation(
			AllocationSector allsec);

	public Long filterByEmployeeExecution(AllocationEmployee allocationEmployee);

	public List<AllocationEmployee> filterByAllocation(Employee responsible);

}
