package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.EmployeeDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;
import mz.ciuem.sipma.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements
		EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> findByFilter(String filter) {
		return employeeDao.findByFilter(filter);
	}

	@Override
	public List<Employee> allWhereUserIdIsNull() {
		// TODO Auto-generated method stub
		return employeeDao.allWhereUserIdIsNull();
	}

	@Override
	public List<Employee> filterBySector(Sector sector) {
		// TODO Auto-generated method stub
		return employeeDao.filterBySector(sector);
	}

	@Override
	public List<Employee> filterBySubSector(SubSector subSector) {
		return employeeDao.filterBySubSector(subSector);
	}

	@Override
	public List<Employee> filterByOrgan(Organ organ) {
		// TODO Auto-generated method stub
		return employeeDao.filterByOrgan(organ);
	}

	@Override
	public List<Employee> allWhereUserIdIsNotNull(Sector sector) {
		// TODO Auto-generated method stub
		return employeeDao.allWhereUserIdIsNotNull(sector);
	}

	@Override
	public List<Employee> allWhereUserIdIsNotNull(Organ organ) {
		// TODO Auto-generated method stub
		return employeeDao.allWhereUserIdIsNotNull(organ);
	}

	@Override
	public List<Employee> allWhereUserIdIsNotNull() {
		// TODO Auto-generated method stub
		return employeeDao.allWhereUserIdIsNotNull();
	}

}
