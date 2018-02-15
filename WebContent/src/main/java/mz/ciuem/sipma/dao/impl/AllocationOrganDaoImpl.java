package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.AllocationOrganDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Organ;

import org.springframework.stereotype.Repository;

@Repository
public class AllocationOrganDaoImpl extends GenericDaoImpl<AllocationOrgan>
		implements AllocationOrganDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationOrgan> filterByOrgan(Organ organ) {

		Query query = em
				.createQuery("select ao from AllocationOrgan ao where ao.organ = ? ORDER BY ao.taskAlso DESC");
		query.setParameter(1, organ);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationOrgan> findAllNotIntegrated(Organ organ) {

		Query query = em
				.createQuery("select ao from AllocationOrgan ao where ao.state = false and ao.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationOrgan> filterIntegratedByOrgan(Organ organ) {

		Query query = em
				.createQuery("select ao from AllocationOrgan ao where ao.state = true and ao.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

}
