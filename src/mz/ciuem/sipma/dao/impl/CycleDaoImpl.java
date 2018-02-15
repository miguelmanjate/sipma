package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.CycleDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Organ;

import org.springframework.stereotype.Repository;

@Repository
public class CycleDaoImpl extends GenericDaoImpl<Cycle> implements CycleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Cycle> cyclesByOrgan(Organ organ) {

		Query query = em
				.createQuery("select c from Cycle c where ? in elements(c.organs)");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cycle> findByState(String state) {

		Query query = em.createQuery("select c from Cycle c where c.state = ?");
		query.setParameter(1, state);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cycle> findByOrganAndState(Organ organ, String state) {

		Query query = em
				.createQuery("select c from Cycle c where ? in elements(c.organs) and c.state = ?");
		query.setParameter(1, organ);
		query.setParameter(2, state);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Organ> findOrganNotIncludeCycle() {

		Query query = em
				.createQuery("select o from Cycle c inner join c.organs o");

		return query.getResultList();
	}

}
