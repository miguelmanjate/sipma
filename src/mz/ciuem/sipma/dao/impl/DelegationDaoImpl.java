package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.DelegationDao;
import mz.ciuem.sipma.entity.Delegation;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.UserRoleService;
import mz.ciuem.sipma.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DelegationDaoImpl extends GenericDaoImpl<Delegation> implements
		DelegationDao {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Delegation> filterByResponsible(Employee emp) {

		Query query = em
				.createQuery("select d from Delegation d where d.responsible = ?");
		query.setParameter(1, emp);

		return query.getResultList();
	}

	@Override
	public void delete(final Object id) {

		Delegation found = find(id);

		User delegated = userService.find(found.getEmployee().getUserLogin()
				.getId());

		delegated.getRoles().remove(
				userRoleService.findByName("Delegação#" + id));

		userService.update(delegated);
		userRoleService.delete(userRoleService.findByName("Delegação#" + id).getId());

		em.remove(em.getReference(Delegation.class, id));
	}

}
