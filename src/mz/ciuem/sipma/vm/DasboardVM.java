package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.SectorExecution;
import mz.ciuem.sipma.entity.SpecifiedAction;
import mz.ciuem.sipma.service.ActionService;
import mz.ciuem.sipma.service.AllocationOrganService;
import mz.ciuem.sipma.service.AllocationSectorService;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.SectorExecutionService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.SpecifiedActionService;
import mz.ciuem.sipma.util.Breadcrumb;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DasboardVM extends AbstractVM {

	@WireVariable
	private CycleService cycleService;

	@WireVariable
	private ActionService actionService;

	@WireVariable
	private AllocationOrganService allocationOrganService;

	@WireVariable
	private SpecifiedActionService specifiedActionService;

	@WireVariable
	private OrganService organService;

	private List<AllocationOrgan> allocations;

	private List<SpecifiedAction> allocatedActions;

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private AllocationSectorService allocationSectorService;

	private List<AllocationSector> allocationSectors;

	private AllocationSector allocationSector;

	@WireVariable
	private SectorExecutionService sectorExecutionService;

	private List<SectorExecution> sectorExecutions;

	private List<Cycle> cycles;

	private List<Action> actions;

	private List<SectorExecution> SectorExec;

	private List<AllocationSector> allocSec;

	private int rate;

	private String state = "Pendente";

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);
	}

	@Init(superclass = true)
	public void init() {

		loggeduser = userService.getUser(authentication.getName());

		loggedOrgan = organService.getOrganByResponsible(loggeduser
				.getResponsible());

		loggedSector = sectorService.getSectorByResponsible(loggeduser
				.getResponsible());

		allocationSector = new AllocationSector();

		cycles = cycleService.cyclesByOrgan(loggedOrgan);

		if (cycles.size() > 3) {
			cycles = cycles.subList(0, 3);
		}

		allocations = allocationOrganService.filterByOrgan(loggedOrgan);

		if (allocations.size() > 3) {

			allocations = allocations.subList(0, 3);

		}

		allocatedActions = specifiedActionService
				.allAllocatedActions(loggedOrgan);

		actions = actionService.filterByOrgan(loggedOrgan);

		if (actions.size() > 3) {
			actions = actions.subList(0, 3);
		}

		allocationSectors = allocationSectorService
				.filterIntegratedBySector(loggedSector);

		if (allocationSectors.size() > 3) {

			for (int i = 0; i < allocationSectors.size(); i++) {
				rate = allocationSectors.get(i).rate();
				if (rate <= 100) {
					rate += allocationSectors.get(i).rate();
				} else {

					Clients.showNotification("A Percentagem de Execucao Nao Deve Estar Acima De 100%");
				}
			}
			allocationSectors = allocationSectors.subList(0, 3);
		}
	}

	public int percentage(Cycle cycle) {

		long now = System.currentTimeMillis();
		long s = cycle.getStartDate().getTime();
		long e = cycle.getEndDate().getTime();
		if (s >= e || now >= e) {
			return 0;
		}
		if (now <= s) {
			return 100;
		}
		return (int) (((now - s) * 100) / (e - s));
	}

	@Command
	public void integAction(@BindingParam("target") Div target,
			@BindingParam("breadcrumb") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents(
				"views/allocation_activity/pagActionIntegration.zul", target,
				map);

		ArrayList<String> links = new ArrayList<String>();
		links.add("Integrar Acção");
		Breadcrumb.drawn(breadcrumb, "Alocação da Actividade", links);
	}

	@Command
	public void registerAction(@BindingParam("target") Div target,
			@BindingParam("breadcrumb") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagRegisterAction.zul",
				target, map);

		ArrayList<String> links = new ArrayList<String>();
		links.add("Registar Acção");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);
	}

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public List<AllocationOrgan> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOrgan> allocations) {
		this.allocations = allocations;
	}

	public List<SpecifiedAction> getAllocatedActions() {
		return allocatedActions;
	}

	public void setAllocatedActions(List<SpecifiedAction> allocatedActions) {
		this.allocatedActions = allocatedActions;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<AllocationSector> getAllocationSectors() {
		return allocationSectors;
	}

	public void setAllocationSectors(List<AllocationSector> allocationSectors) {
		this.allocationSectors = allocationSectors;
	}

	public List<SectorExecution> getSectorExecutions() {
		return sectorExecutions;
	}

	public void setSectorExecutions(List<SectorExecution> sectorExecutions) {
		this.sectorExecutions = sectorExecutions;
	}

	public AllocationSector getAllocationSector() {
		return allocationSector;
	}

	public void setAllocationSector(AllocationSector allocationSector) {
		this.allocationSector = allocationSector;
	}

	public List<SectorExecution> getSectorExec() {
		return SectorExec;
	}

	public void setSectorExec(List<SectorExecution> sectorExec) {
		SectorExec = sectorExec;
	}

	public List<AllocationSector> getAllocSec() {
		return allocSec;
	}

	public void setAllocSec(List<AllocationSector> allocSec) {
		this.allocSec = allocSec;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
