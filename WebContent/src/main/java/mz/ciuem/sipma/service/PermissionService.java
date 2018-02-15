package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.Permission;

public interface PermissionService extends GenericService<Permission> {

	public Permission getPermission(String usersPermission);
}
