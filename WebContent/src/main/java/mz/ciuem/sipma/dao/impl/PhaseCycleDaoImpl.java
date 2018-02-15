package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.PhaseCycleDao;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.PhaseCycle;

import org.springframework.stereotype.Repository;

@Repository
public class PhaseCycleDaoImpl extends GenericDaoImpl<PhaseCycle> implements
		PhaseCycleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PhaseCycle> filterByCycle(Cycle cycle) {
		
		Query query = em
				.createQuery("select pc from PhaseCycle pc where pc.cycle = ?");
		query.setParameter(1, cycle);

		return query.getResultList();
	}

}
