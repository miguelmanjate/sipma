package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.SpecifiedAction;

public interface SpecifiedActionDao extends GenericDao<SpecifiedAction> {

	public List<SpecifiedAction> filterByAction(Action action);
	
	public List<SpecifiedAction> allAllocatedActions(Organ organ);
	
	public List<SpecifiedAction> allWithBasedCalc(Organ organ);
}
