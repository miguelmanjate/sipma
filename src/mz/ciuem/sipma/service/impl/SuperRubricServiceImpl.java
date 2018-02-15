package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SuperRubricDao;
import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.service.SuperRubricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("superRubricService")
public class SuperRubricServiceImpl extends GenericServiceImpl<SuperRubric> implements SuperRubricService{

	@Autowired
	private SuperRubricDao _superRubricDao;
	
	@Override
	public List<SuperRubric> getSuperRubricByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return _superRubricDao.getSuperRubricByCategoria(categoria);
	}

	@Override
	public List<SuperRubric> getAllSuperRubric() {
		// TODO Auto-generated method stub
		return _superRubricDao.getAllSuperRubric();
	}

	@Override
	public List<SuperRubric> getAllOrdenadoPorCategoriaEDescricao() {
		// TODO Auto-generated method stub
		return getAllOrdenadoPorCategoriaEDescricao();
	}

}
