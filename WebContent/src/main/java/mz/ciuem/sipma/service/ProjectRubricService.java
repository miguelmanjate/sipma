package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.SubRubric;

public interface ProjectRubricService extends GenericService<ProjectRubric>{

	public List<ProjectRubric> getProjectRubricBySubRubric(SubRubric subRubric);
}
