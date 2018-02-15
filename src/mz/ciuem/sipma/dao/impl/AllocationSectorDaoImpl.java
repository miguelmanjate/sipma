package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.AllocationSectorDao;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

import org.springframework.stereotype.Repository;

@Repository
public class AllocationSectorDaoImpl extends GenericDaoImpl<AllocationSector>
		implements AllocationSectorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationSector> filterByOrgan(Organ organ) {

		Query query = em
				.createQuery("select allsec from AllocationSector allsec where allsec.allocationOrgan.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationSector> filterIntegratedBySector(Sector sector) {

		Query query = em
				.createQuery("select allsec from AllocationSector allsec  where allsec.sector = ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationSector> filterBySector(Sector sector) {
		Query query = em
				.createQuery("select a from AllocationSector a where a.sector = ?");
		query.setParameter(1, sector);

		return query.getResultList();
	}
}
