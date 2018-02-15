package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;

public interface SubSectorDao extends GenericDao<SubSector> {

	public List<SubSector> getSubSectorsBySector(Sector sector);
}
