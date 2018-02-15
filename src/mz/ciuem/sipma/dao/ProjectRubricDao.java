package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;

public interface ProjectRubricDao extends GenericDao<ProjectRubric>{
	
	public List<ProjectRubric> getProjectRubricBySubRubric(SubRubric subRubric);

}
