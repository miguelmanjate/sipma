package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.PhaseOrganCycle;

public interface PhaseOrganCycleService extends GenericService<PhaseOrganCycle> {

	public List<PhaseOrganCycle> filterByOrgan(Organ organ);
	
	public List<PhaseOrganCycle> filterByState(String state);
	
	public List<PhaseOrganCycle> allNotAcepted();
}
