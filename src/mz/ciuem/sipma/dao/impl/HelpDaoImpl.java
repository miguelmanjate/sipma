package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.HelpDao;
import mz.ciuem.sipma.entity.Help;
import mz.ciuem.sipma.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public class HelpDaoImpl extends GenericDaoImpl<Help> implements HelpDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Help> findBySender(User from) {
		
		Query query = em
				.createQuery("select h from Help h where h.who = ?");
		query.setParameter(1, from);

		return query.getResultList();
	}

}
