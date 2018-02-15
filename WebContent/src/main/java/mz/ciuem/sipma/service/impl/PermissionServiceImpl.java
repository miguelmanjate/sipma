package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.dao.PermissionDao;
import mz.ciuem.sipma.entity.Permission;
import mz.ciuem.sipma.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission>
		implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Permission getPermission(String usersPermission) {
		// TODO Auto-generated method stub
		return permissionDao.getPermission(usersPermission);
	}

}
