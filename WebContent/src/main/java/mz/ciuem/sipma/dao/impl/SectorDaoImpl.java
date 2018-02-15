package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SectorDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SectorDaoImpl extends GenericDaoImpl<Sector> implements SectorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> filterByOrgan(Organ organ) {

		Query query = em
				.createQuery("select s from Sector s where s.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sector getSectorByResponsible(Employee e) {

		Query query = em
				.createQuery("select s from Sector s where s.responsible = ?");
		query.setParameter(1, e);

		return DataAccessUtils.singleResult(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> getFilteredSectors(Sector sector) {
		Query query = em.createQuery("select s from Sector s where s != ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> getOrganBySector(Organ organ) {

		Query query = em
				.createQuery("select s from Sector s where s.organ = ?");
		query.setParameter(1, organ);
		
		return query.getResultList();
		
	}

}