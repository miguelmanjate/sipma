package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SubSectorDao;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;

import org.springframework.stereotype.Repository;

@Repository
public class SubSectorDaoImpl extends GenericDaoImpl<SubSector> implements
		SubSectorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SubSector> getSubSectorsBySector(Sector sector) {
		
		Query query = em
				.createQuery("select ss from SubSector ss where ss.sector = ?");
		query.setParameter(1, sector);
		
		return query.getResultList();
		
	}

}
