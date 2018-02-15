package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SuperRubricDao;
import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.SuperRubric;

import org.springframework.stereotype.Repository;

@Repository
public class SuperRubricDaoImpl extends GenericDaoImpl<SuperRubric> implements SuperRubricDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SuperRubric> getSuperRubricByCategoria(Categoria categoria) {
		Query query = em.createQuery("select sr from SuperRubric sr where sr.categoria = ?");
        query.setParameter(1, categoria);
		
		return query.getResultList();
	}

}
