package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Delegation;
import mz.ciuem.sipma.entity.Employee;

public interface DelegationDao extends GenericDao<Delegation> {

	public List<Delegation> filterByResponsible(Employee emp);
}
