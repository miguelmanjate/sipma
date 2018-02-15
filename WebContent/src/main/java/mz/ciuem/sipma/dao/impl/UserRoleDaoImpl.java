package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.UserRoleDao;
import mz.ciuem.sipma.entity.UserRole;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements
		UserRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAll() {
		Query query = em
				.createQuery("from UserRole ur where ur.type = 'Normal'");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRole findByName(String rolename) {
		Query query = em.createQuery("from UserRole ur where ur.rolename = ?");
		query.setParameter(1, rolename);
		
		return DataAccessUtils.singleResult(query.getResultList());
	}
}
