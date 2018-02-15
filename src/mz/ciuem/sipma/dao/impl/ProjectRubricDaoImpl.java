package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.ProjectRubricDao;
import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.SubRubric;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectRubricDaoImpl extends GenericDaoImpl<ProjectRubric> implements ProjectRubricDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectRubric> getProjectRubricBySubRubric(SubRubric subRubric) {
		Query query = em.createQuery("select ps from ProjectRubric ps where ps.subRubric = ? and ps.emUso=true");
        query.setParameter(1, subRubric);
		
		return query.getResultList();
	}

}
