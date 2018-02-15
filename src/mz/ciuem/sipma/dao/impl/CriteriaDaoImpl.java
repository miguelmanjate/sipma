package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.CriteriaDao;
import mz.ciuem.sipma.entity.Criteria;

import org.springframework.stereotype.Repository;

@Repository
public class CriteriaDaoImpl extends GenericDaoImpl<Criteria> implements
		CriteriaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Criteria> getCriteriaByType(String type) {

		Query query = em
				.createQuery("select c from Criteria c where c.type = ?");
		query.setParameter(1, type);

		return query.getResultList();
	}

}
