package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.SpecifiedActionDao;
import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.BasedCalculating;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.SpecifiedAction;
import mz.ciuem.sipma.service.AllocationOrganService;
import mz.ciuem.sipma.service.BasedCalculatingService;
import mz.ciuem.sipma.service.RoleService;
import mz.ciuem.sipma.service.SpecifiedActionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("specifiedActionService")
public class SpecifiedActionServiceImpl extends
		GenericServiceImpl<SpecifiedAction> implements SpecifiedActionService {

	@Autowired
	private SpecifiedActionDao specifiedActionDao;

	@Autowired
	private BasedCalculatingService basedCalculatingService;

	@Autowired
	private AllocationOrganService allocationOrganService;

	@Autowired
	private RoleService roleService;
	

	@Override
	public List<SpecifiedAction> filterByAction(Action action) {
		// TODO Auto-generated method stub
		return specifiedActionDao.filterByAction(action);
	}

	@Override
	public SpecifiedAction create(SpecifiedAction specifiedAction) {

		for (BasedCalculating bc : specifiedAction.getBasedCalcs()) {
			bc.setSpecifiedAction(specifiedAction);
			basedCalculatingService.create(bc);
		}

		for (AllocationOrgan ao : specifiedAction.getAllocations()) {
			ao.setSpecifiedAction(specifiedAction);
			allocationOrganService.create(ao);
		}
		specifiedAction.setAmbit("Planificacao");
		specificDao.create(specifiedAction);

		AllocationOrgan allocation = new AllocationOrgan();
		allocation.setStartDate(specifiedAction.getBeginDate());
		allocation.setEndDate(specifiedAction.getEndDate());
		allocation.setOrgan(specifiedAction.getAction().getOrgan());
		allocation.setSpecifiedAction(specifiedAction);
		allocation.setTaskAlso(specifiedAction.getDesignation());
		allocation.setRole(roleService.findByName("Principal"));
		allocation.setState(true);

		allocationOrganService.create(allocation);

		return specifiedAction;
	}

	@Override
	public List<SpecifiedAction> allAllocatedActions(Organ organ) {
		// TODO Auto-generated method stub
		return specifiedActionDao.allAllocatedActions(organ);
	}

	public void createActions(List<SpecifiedAction> specifiedActions) {

		for (SpecifiedAction specifiedAction : specifiedActions) {
			specifiedAction.setAmbit("Documento");
			specificDao.create(specifiedAction);
		}
	}

	@Override
	public List<SpecifiedAction> allWithBasedCalc(Organ organ) {
		// TODO Auto-generated method stub
		return specifiedActionDao.allWithBasedCalc(organ);
	}




}
