package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.DelegationDao;
import mz.ciuem.sipma.entity.Delegation;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.entity.UserRole;
import mz.ciuem.sipma.service.DelegationService;
import mz.ciuem.sipma.service.UserRoleService;
import mz.ciuem.sipma.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("delegationService")
public class DelegationServiceImpl extends GenericServiceImpl<Delegation>
		implements DelegationService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private DelegationDao delegationDao;

	@Override
	public Delegation create(Delegation delegation) {

		specificDao.create(delegation);

		UserRole role = new UserRole();
		role.setRolename("Delegação#" + delegation.getId());
		role.getPermissions().addAll(delegation.getPermissions());
		role.setType("Delegated");
		userRoleService.create(role);

		User owner = delegation.getEmployee().getUserLogin();
		owner.getRoles().add(role);
		userService.update(owner);

		return delegation;
	}

	@Override
	public List<Delegation> filterByResponsible(Employee emp) {
		// TODO Auto-generated method stub
		return delegationDao.filterByResponsible(emp);
	}
}
