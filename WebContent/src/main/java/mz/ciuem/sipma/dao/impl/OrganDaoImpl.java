package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.OrganDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class OrganDaoImpl extends GenericDaoImpl<Organ> implements OrganDao {

	@SuppressWarnings("unchecked")
	@Override
	public Organ getOrganByResponsible(Employee e) {

		Query query = em
				.createQuery("select o from Organ o where o.responsible = ?");
		query.setParameter(1, e);

		return DataAccessUtils.singleResult(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organ> getFilteredOrgans(Organ organ) {

		Query query = em.createQuery("select o from Organ o where o != ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organ> findAllWithoutCycles() {
		
		Query query = em.createQuery("select o from Organ o left join FETCH o.cycles c where c.id is null");
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organ> findAllNotInCycle(Cycle cycle) {
		
		Query query = em.createQuery("select o from Organ o where ? not in elements(o.cycles)");
		query.setParameter(1, cycle);

		return query.getResultList();
	}

}
