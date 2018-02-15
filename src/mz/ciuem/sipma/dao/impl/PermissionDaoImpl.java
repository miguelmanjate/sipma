package mz.ciuem.sipma.dao.impl;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.PermissionDao;
import mz.ciuem.sipma.entity.Permission;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements
		PermissionDao {

	@SuppressWarnings("unchecked")
	@Override
	public Permission getPermission(String usersPermission) {

		Query query = em
				.createQuery("select p from Permission p where p.permissionname = ?");
		query.setParameter(1, usersPermission);
		
		return (Permission) DataAccessUtils.singleResult(query.getResultList());
	}

}
