package mz.ciuem.sipma.dao.impl;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.RoleDao;
import mz.ciuem.sipma.entity.Role;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements
		RoleDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public Role findByName(String role) {
		
		Query query = em
				.createQuery("select r from Role r where r.designation = ?");
		query.setParameter(1, role);

		return DataAccessUtils.singleResult(query.getResultList());
	}

}
