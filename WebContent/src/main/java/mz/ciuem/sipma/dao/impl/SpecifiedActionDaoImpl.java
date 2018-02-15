package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SpecifiedActionDao;
import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.SpecifiedAction;

import org.springframework.stereotype.Repository;

@Repository
public class SpecifiedActionDaoImpl extends GenericDaoImpl<SpecifiedAction>
		implements SpecifiedActionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecifiedAction> filterByAction(Action action) {

		Query query = em
				.createQuery("select sa from SpecifiedAction sa where sa.action = ?");
		query.setParameter(1, action);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecifiedAction> allAllocatedActions(Organ organ) {
		
		Query query = em
				.createQuery("select sa from SpecifiedAction sa where sa.action.organ = ? and size(sa.allocations) > 0 ");
		query.setParameter(1, organ);

		return query.getResultList();
	}

}
