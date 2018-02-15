package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;

public interface SubRubricDao extends GenericDao<SubRubric>{

	public List<SubRubric> getSubRubricByRubric(Rubric rubric);
}
