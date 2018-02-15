package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SubRubricDao;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;

import org.springframework.stereotype.Repository;

@Repository
public class SubRubricDaoImpl extends GenericDaoImpl<SubRubric> implements SubRubricDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<SubRubric> getSubRubricByRubric(Rubric rubric) {
		Query query = em.createQuery("select sr from SubRubric sr where sr.rubric = ?");
        query.setParameter(1, rubric);
		
		return query.getResultList();
	}

}
