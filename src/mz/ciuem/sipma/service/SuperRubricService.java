package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.SuperRubric;

public interface SuperRubricService extends GenericService<SuperRubric>{

	public List<SuperRubric> getSuperRubricByCategoria(Categoria categoria);
	public List<SuperRubric> getAllSuperRubric();
	public List<SuperRubric> getAllOrdenadoPorCategoriaEDescricao();
}
