package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.EmployeeDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements
		EmployeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByFilter(String filter) {

		Query query = em
				.createQuery("select e from Employee e where e.name like ?");
		query.setParameter(1, filter + '%');

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> allWhereUserIdIsNull() {

		Query query = em
				.createQuery("select e from Employee e where e.userLogin is null");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> filterBySector(Sector sector) {

		Query query = em
				.createQuery("select e from Employee e where e.sector = ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> filterBySubSector(SubSector subSector) {
		Query query = em
				.createQuery("select e from Employee e where e.subSector = ?");
		query.setParameter(1, subSector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {

		Query query = em
				.createQuery("select e from Employee e where e.name NOT LIKE 'Admin'");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> filterByOrgan(Organ organ) {

		Query query = em
				.createQuery("select e from Employee e where e.sector.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> allWhereUserIdIsNotNull(Sector sector) {
		Query query = em
				.createQuery("select e from Employee e where e.userLogin is not null and e.sector = ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> allWhereUserIdIsNotNull(Organ organ) {
		Query query = em
				.createQuery("select e from Employee e where e.userLogin is not null and e.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> allWhereUserIdIsNotNull() {
		
		Query query = em
				.createQuery("select e from Employee e where e.userLogin is not null and e.name NOT LIKE 'Admin'");

		return query.getResultList();
	}
}
