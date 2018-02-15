package mz.ciuem.sipma.dao;

import mz.ciuem.sipma.entity.UserRole;

public interface UserRoleDao extends GenericDao<UserRole> {

	public UserRole findByName(String rollname);
}
