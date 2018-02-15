package mz.ciuem.sipma.dao;

import mz.ciuem.sipma.entity.Permission;

public interface PermissionDao extends GenericDao<Permission> {

	public Permission getPermission(String usersPermission);
}
