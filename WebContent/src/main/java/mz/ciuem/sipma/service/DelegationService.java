package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Delegation;
import mz.ciuem.sipma.entity.Employee;

public interface DelegationService extends GenericService<Delegation> {

	public List<Delegation> filterByResponsible(Employee emp);
}
