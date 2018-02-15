package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.ProjectRubricDao;
import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.service.ProjectRubricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectRubricService")
public class ProjectRubricServiceImpl extends GenericServiceImpl<ProjectRubric> implements ProjectRubricService{

	@Autowired
	private ProjectRubricDao projectRubricDao;
	
	@Override
	public List<ProjectRubric> getProjectRubricBySubRubric(SubRubric subRubric) {
		return projectRubricDao.getProjectRubricBySubRubric(subRubric);
	}

}
