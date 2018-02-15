package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.CriteriaDao;
import mz.ciuem.sipma.entity.Criteria;
import mz.ciuem.sipma.service.CriteriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("criteriaService")
public class CriteriaServiceImpl extends GenericServiceImpl<Criteria>
		implements CriteriaService {

	@Autowired
	private CriteriaDao criteriaDao;
	
	@Override
	public List<Criteria> getCriteriaByType(String type) {
		// TODO Auto-generated method stub
		return criteriaDao.getCriteriaByType(type);
	}

}
