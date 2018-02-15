package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SubRubricDao;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.service.SubRubricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subRubricService")
public class SubRubricServiceImpl extends GenericServiceImpl<SubRubric> implements SubRubricService{
	
	@Autowired
	private SubRubricDao subRubricDao;
	

	@Override
	public List<SubRubric> getSubRubricByRubric(Rubric rubric) {
		return subRubricDao.getSubRubricByRubric(rubric);
	}


}
