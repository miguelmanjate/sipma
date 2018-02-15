package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.AllocationEmployeeDao;
import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;

import org.springframework.stereotype.Repository;

@Repository
public class AllocationEmployeeDaoImpl extends
		GenericDaoImpl<AllocationEmployee> implements AllocationEmployeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationEmployee> filterBySector(Sector sector) {
		Query query = em
				.createQuery("select ae from AllocationEmployee ae where ae.allocationSector.sector = ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationEmployee> filterByEmployee(Employee employee) {
		Query query = em
				.createQuery("select ae from AllocationEmployee ae where ae.responsible = ?");
		query.setParameter(1, employee);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationEmployee> filterBySectorAllocation(
			AllocationSector allsec) {
		Query query = em
				.createQuery("select ae from AllocationEmployee ae where ae.allocationSector = ?");
		query.setParameter(1, allsec);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationEmployee> filterByAllocation(Employee responsible) {

		Query query = em
				.createQuery("select ae from AllocationEmployee ae where ae.responsible = ?");
		query.setParameter(1, responsible);

		return query.getResultList();
	}

	@Override
	public Long filterByEmployeeExecution(AllocationEmployee allocationEmployee) {

		Query query = em
				.createQuery("select SUM(eex.rate) from EmployeeExecution eex where eex.allocationEmployee = ? ");

		query.setParameter(1, allocationEmployee);

		Long value = (Long) query.getSingleResult();

		return value;
	}

}
