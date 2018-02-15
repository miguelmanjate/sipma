package mz.ciuem.sipma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.sipma.dao.ActionDao;
import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.service.ActionService;
import mz.ciuem.sipma.service.PriorityLevelService;

@Service("actionService")
public class ActionServiceImpl extends GenericServiceImpl<Action>implements ActionService {

	@Autowired
	private ActionDao actionDao;

	@Override
	public List<Action> filter(String filter) {
		return actionDao.filter(filter);
	}

	@Autowired
	private PriorityLevelService priorityLevelService;

	@Override
	public Action create(Action action) {

		priorityLevelService.create(action.getPriorityLevel());

		return specificDao.create(action);
	}

	@Override
	public List<Action> filterByOrgan(Organ organ) {
		return actionDao.filterByOrgan(organ);
	}

	@Override
	public List<Action> filterNotHaveBaseCalculating(Action action) {
		return actionDao.filterNotHaveBaseCalculating(action);
	}

	@Override
	public List<Action> filterHaveBaseCalculating() {
		return actionDao.filterHaveBaseCalculating();
	}

	@Override
	public List<Action> filterNotHaveSpecificationAction(Action action) {
		return actionDao.filterNotHaveSpecificationAction(action);
	}

	@Override
	public List<Action> filterHaveSpecificationAction(Action action) {
		// TODO Auto-generated method stub
		return actionDao.filterHaveSpecificationAction(action);
	}

}
