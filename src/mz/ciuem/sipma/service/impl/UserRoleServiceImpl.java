package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.dao.UserRoleDao;
import mz.ciuem.sipma.entity.UserRole;
import mz.ciuem.sipma.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole> implements
		UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public UserRole findByName(String rolename) {
		// TODO Auto-generated method stub
		return userRoleDao.findByName(rolename);
	}

}
