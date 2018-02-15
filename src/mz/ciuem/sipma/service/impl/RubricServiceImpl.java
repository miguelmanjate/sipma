package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.RubricDao;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.service.RubricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rubricService")
public class RubricServiceImpl extends GenericServiceImpl<Rubric> implements RubricService{

	@Autowired
	private RubricDao rubricDao;
	
	@Override
	public List<Rubric> getRubricBySuperRubric(SuperRubric superRubric) {
		return rubricDao.getRubricBySuperRubric(superRubric);
	}

	@Override
	public List<Rubric> buscarTodasAsRubricasOrdenadas() {
		return rubricDao.buscarTodasAsRubricasOrdenadas();
	}

}
