package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.EmployeeExecutionDao;
import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.EmployeeExecution;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeExecutionDaoImpl extends GenericDaoImpl<EmployeeExecution>
		implements EmployeeExecutionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeExecution> filterByAllocation(AllocationEmployee ae) {

		Query query = em
				.createQuery("select ee from EmployeeExecution ee where ee.allocationEmployee = ?");
		query.setParameter(1, ae);

		return query.getResultList();
	}

}
