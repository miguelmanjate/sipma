package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.SpecifiedAction;

public interface SpecifiedActionService extends GenericService<SpecifiedAction> {

	public List<SpecifiedAction> filterByAction(Action action);

	public List<SpecifiedAction> allAllocatedActions(Organ organ);
	
	public void createActions(List<SpecifiedAction> specifiedActions);
}
