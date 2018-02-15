package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.PhaseCycle;

public interface PhaseCycleDao extends GenericDao<PhaseCycle> {

	public List<PhaseCycle> filterByCycle(Cycle cycle);
}
