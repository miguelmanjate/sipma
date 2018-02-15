package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Organ;

public interface CycleDao extends GenericDao<Cycle> {

	public List<Cycle> cyclesByOrgan(Organ organ);
	
	public List<Cycle> findByState(String state);
	
	public List<Cycle> findByOrganAndState(Organ organ, String state);
}
