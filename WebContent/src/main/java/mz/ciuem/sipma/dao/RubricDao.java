package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SuperRubric;

public interface RubricDao extends GenericDao<Rubric> {

	public List<Rubric> getRubricBySuperRubric(SuperRubric superRubric);
}
