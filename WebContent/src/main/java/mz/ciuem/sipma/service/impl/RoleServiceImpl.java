package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.dao.RoleDao;
import mz.ciuem.sipma.entity.Role;
import mz.ciuem.sipma.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role>
		implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName(String role) {
		// TODO Auto-generated method stub
		return roleDao.findByName(role);
	}

}
