package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.PhaseCycle;

public interface PhaseCycleService extends GenericService<PhaseCycle> {

	public List<PhaseCycle> filterByCycle(Cycle cycle);
}
