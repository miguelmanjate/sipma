package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;

public interface ActionService extends GenericService<Action> {

	public List<Action> filter(String filter);

	public List<Action> filterByOrgan(Organ organ);

	public List<Action> filterNotHaveBaseCalculating(Action action);

	public List<Action> filterHaveBaseCalculating();

	public List<Action> filterNotHaveSpecificationAction(Action action);

	public List<Action> filterHaveSpecificationAction(Action action);

}
