package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;

public interface SectorService extends GenericService<Sector> {

	public List<Sector> filterByOrgan(Organ organ);

	public Sector getSectorByResponsible(Employee e);

	public List<Sector> getFilteredOrsectors(Sector sector);

	public List<Sector> getOrganBySector(Organ organ);
}