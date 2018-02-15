package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.RubricDao;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SuperRubric;

import org.springframework.stereotype.Repository;

@Repository
public class RubricDaoImpl extends GenericDaoImpl<Rubric> implements RubricDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Rubric> getRubricBySuperRubric(SuperRubric superRubric) {
		Query query = em.createQuery("select r from Rubric r where r.superRubric = ?");
        query.setParameter(1, superRubric);
		
		return query.getResultList();
	}

}
