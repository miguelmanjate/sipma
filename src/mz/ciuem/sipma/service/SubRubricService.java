package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;

public interface SubRubricService extends GenericService<SubRubric>{
	public List<SubRubric> getSubRubricByRubric(Rubric rubric);
}
