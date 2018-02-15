package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SuperRubric;

public interface RubricService extends GenericService<Rubric>{

	public List<Rubric> getRubricBySuperRubric(SuperRubric superRubric);
	
	public List<Rubric> buscarTodasAsRubricasOrdenadas();
}
