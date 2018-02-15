package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SectorExecutionDao;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SectorExecution;

import org.springframework.stereotype.Repository;

@Repository
public class SectorExecutionDaoImpl extends GenericDaoImpl<SectorExecution>
		implements SectorExecutionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SectorExecution> filterByAllocation(AllocationSector se) {

		Query query = em
				.createQuery("select se from SectorExecution se where se.allocationSector = ?");
		query.setParameter(1, se);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectorExecution> filterByAllocationOrgan(AllocationOrgan oa) {

		Query query = em
				.createQuery("select se from SectorExecution se where se.allocationSector.allocationOrgan = ?");
		query.setParameter(1, oa);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<SectorExecution> filterExecutionBySector(Sector sector) {

		Query query = em
				.createQuery("select se from SectorExecution se where se.allocationSector.sector = ?");

		query.setParameter(1, sector);

		return query.getResultList();
	}

}
