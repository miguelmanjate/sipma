package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Organ;

public interface AllocationOrganService extends GenericService<AllocationOrgan> {

	public List<AllocationOrgan> filterByOrgan(Organ organ);
	
	public List<AllocationOrgan> findAllNotIntegrated(Organ organ);
	
	public List<AllocationOrgan> filterIntegratedByOrgan(Organ organ);
}
