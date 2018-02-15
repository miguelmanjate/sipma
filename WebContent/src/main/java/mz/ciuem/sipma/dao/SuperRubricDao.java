package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.SuperRubric;

public interface SuperRubricDao extends GenericDao<SuperRubric>{

	public List<SuperRubric> getSuperRubricByCategoria(Categoria categoria);
}
