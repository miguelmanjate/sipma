package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;

public interface EmployeeService extends GenericService<Employee> {

	public List<Employee> findByFilter(String filter);
	
	public List<Employee> allWhereUserIdIsNull();
	
	public List<Employee> allWhereUserIdIsNotNull(Sector sector);
	
	public List<Employee> allWhereUserIdIsNotNull(Organ organ);
	
	public List<Employee> filterBySector(Sector sector);
	
	public List<Employee> filterBySubSector(SubSector subSector);
	
	public List<Employee> filterByOrgan(Organ organ);
	
	public List<Employee> allWhereUserIdIsNotNull();
}
