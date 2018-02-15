package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.AllocationEmployeeDao;
import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.service.AllocationEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("allocationEmployeeService")
public class AllocationEmployeeServiceImp extends
		GenericServiceImpl<AllocationEmployee> implements
		AllocationEmployeeService {

	@Autowired
	private AllocationEmployeeDao allocationEmployeeDao;

	public List<AllocationEmployee> filterBySector(Sector sector) {
		return allocationEmployeeDao.filterBySector(sector);
	}

	@Override
	public List<AllocationEmployee> filterByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return allocationEmployeeDao.filterByEmployee(employee);
	}

	@Override
	public List<AllocationEmployee> filterBySectorAllocation(
			AllocationSector allsec) {
		// TODO Auto-generated method stub
		return allocationEmployeeDao.filterBySectorAllocation(allsec);
	}

	public Long filterByEmployeeExecution(AllocationEmployee allocationEmployee) {
		return allocationEmployeeDao
				.filterByEmployeeExecution(allocationEmployee);
	}

	@Override
	public List<AllocationEmployee> filterByAllocation(Employee responsible) {
		// TODO Auto-generated method stub
		return allocationEmployeeDao.filterByAllocation(responsible);
	}
}
