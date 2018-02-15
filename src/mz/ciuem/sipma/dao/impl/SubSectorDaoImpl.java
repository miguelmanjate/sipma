package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.SubSectorDao;
import mz.ciuem.sipma.entity.Organ;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SubSector> getSubSectorsByOrgan(Organ organ) {
        //"select c from Cycle c where ? in elements(c.organs)"
		//Query query = em.createQuery("select ss from SubSector ss inner join fetch ss.sector s where s.organ = ?");
		//Select ss.designation from sub_sectors ss LEFT JOIN sectors s ON ss.id=s.id WHERE s.organ=1;
		//Query query = em.createQuery("select ss from SubSector ss where ss.sector not in (Select s from Sector s where s.organ <> ?)");
		//SELECT f from Student f LEFT JOIN f.classTbls s WHERE s.ClassName = 'abc'
	    Query query = em.createQuery("select ss from SubSector ss where ss.sector in (Select s from Sector s where s.organ = ?)");
		query.setParameter(1, organ);
		return query.getResultList();
	}

}
