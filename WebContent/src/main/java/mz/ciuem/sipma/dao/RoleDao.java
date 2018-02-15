package mz.ciuem.sipma.dao;

import mz.ciuem.sipma.entity.Role;

public interface RoleDao extends GenericDao<Role> {

	public Role findByName(String role);
}
