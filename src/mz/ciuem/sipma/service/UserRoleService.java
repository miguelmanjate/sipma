package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.UserRole;


public interface UserRoleService extends GenericService<UserRole> {

	public UserRole findByName(String rolename);
}
