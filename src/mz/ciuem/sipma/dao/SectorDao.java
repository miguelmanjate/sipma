package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

public interface SectorDao extends GenericDao<Sector> {

	public List<Sector> filterByOrgan(Organ organ);

	public Sector getSectorByResponsible(Employee e);

	public List<Sector> getFilteredSectors(Sector sector);
	public List<Sector> getOrganBySector(Organ organ);
}