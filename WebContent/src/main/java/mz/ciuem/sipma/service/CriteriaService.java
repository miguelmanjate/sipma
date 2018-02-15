package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Criteria;

public interface CriteriaService extends GenericService<Criteria> {

	public List<Criteria> getCriteriaByType(String type);
}
