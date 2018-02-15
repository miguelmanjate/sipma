package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;

public interface SubSectorService extends GenericService<SubSector> {

	public List<SubSector> getSubSectorsBySector(Sector sector);
	
}
