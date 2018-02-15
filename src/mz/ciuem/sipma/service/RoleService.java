package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.Role;

public interface RoleService extends GenericService<Role> {

	public Role findByName(String role);
}
