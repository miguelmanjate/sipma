package mz.ciuem.sipma.dao.impl;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.UserDao;
import mz.ciuem.sipma.entity.User;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String login) {
		
		Query query = em
				.createQuery("from User u where u.username = ?");
		query.setParameter(1, login);
		
		return DataAccessUtils.singleResult(query.getResultList());
	}

}
