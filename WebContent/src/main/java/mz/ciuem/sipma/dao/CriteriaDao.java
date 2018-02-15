package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Criteria;

public interface CriteriaDao extends GenericDao<Criteria> {

	public List<Criteria> getCriteriaByType(String type);
}
