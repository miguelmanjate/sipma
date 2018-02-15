package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.LogDao;
import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> filterByUser(User u) {

		Query query = em
				.createQuery("select l from Log l where l.loggedUser = ? order by l.created desc");
		query.setParameter(1, u);

		return query.getResultList();
	}

}
