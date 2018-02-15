package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.PhaseOrganCycle;

public interface PhaseOrganCycleDao extends GenericDao<PhaseOrganCycle> {

	public List<PhaseOrganCycle> filterByOrgan(Organ organ);
	
	public List<PhaseOrganCycle> filterByState(String state);
	
	public List<PhaseOrganCycle> allNotAcepted();
}
