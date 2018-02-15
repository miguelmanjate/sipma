package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mz.ciuem.sipma.dao.ActionDao;
import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;

@Repository
public class ActionDaoImpl extends GenericDaoImpl<Action>implements ActionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filter(String filter) {

		Query query = em.createQuery("select a from Action a where a.content like ?");
		query.setParameter(1, "%" + filter + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filterByOrgan(Organ organ) {

		Query query = em.createQuery("select a from Action a where a.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filterNotHaveSpecificationAction(Action action) {
		Query query = em.createQuery("select a from Action a where ? not in elements(a.specifiedActions)");
		query.setParameter(1, action);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filterHaveSpecificationAction(Action action) {
		Query query = em.createQuery("select a from Action a where ? in elements(a.specifiedActions)");
		query.setParameter(1, action);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filterNotHaveBaseCalculating(Action action) {
		Query query = em.createQuery(
				"select a from Action a join FETCH a.specifiedActions sact where ? not in elements(sact.basedCalcs)");
		query.setParameter(1, action);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> filterHaveBaseCalculating() {
		Query query = em.createQuery("select a from Action a inner join a.specifiedActions sa inner join sa.basedCalcs group by a.id");
		return query.getResultList();
	}

}
