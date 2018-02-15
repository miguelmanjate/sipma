package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.OrganExecutionDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.OrganExecution;

import org.springframework.stereotype.Repository;

@Repository
public class OrganExecutionDaoImp extends GenericDaoImpl<OrganExecution>
		implements OrganExecutionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganExecution> filterByAllocation(AllocationOrgan oe) {
		Query query = em
				.createQuery("select oe from OrganExecution oe where oe.allocationOrgan = ?");
		query.setParameter(1, oe);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganExecution> filterByCycle(Cycle c) {

		Query query = em
				.createQuery("select oe from OrganExecution oe where oe.allocationOrgan.specifiedAction.action.cycle = ?");
		query.setParameter(1, c);

		return query.getResultList();
	}
}
