package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.UserDao;
import mz.ciuem.sipma.entity.Organ;
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
		
		return (User) DataAccessUtils.singleResult(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByOrgan(Organ organ) {
		Query query = em.createQuery("from User u where u.organ = ?");
		query.setParameter(1, organ);
		return query.getResultList();
	}

}
