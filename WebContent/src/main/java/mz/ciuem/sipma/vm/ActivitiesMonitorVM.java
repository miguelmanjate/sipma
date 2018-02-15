package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.OrganExecution;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.OrganExecutionService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ActivitiesMonitorVM extends AbstractVM {

	private List<Cycle> cycles;

	private List<OrganExecution> organExecutions;

	private List<Organ> organs;

	@WireVariable
	private CycleService cycleService;

	@WireVariable
	private OrganExecutionService organExecutionService;

	private Component ui;

	private Window modal;

	@WireVariable
	private OrganExecution organExecution;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		cycles = cycleService.getAll();

		organExecution = new OrganExecution();
	}

	@NotifyChange("organs")
	@Command
	public void findAllocations(@BindingParam("cycleId") Long cycleId) {

		Cycle found = cycleService.find(cycleId);

		organs = new ArrayList<Organ>();
		organs.addAll(found.getOrgans());

		refreshDataTable(null);
	}

	@NotifyChange("")
	@Command
	public void executionLevel(@BindingParam("organId") Long organId) {

	}

	@NotifyChange("")
	@Command
	public void budget(@BindingParam("organId") Long organId) {

		this.organExecution = organExecutionService.find(organId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		modal = (Window) Executions.createComponents("views/activity/pagListAction.zul", ui, map);
		modal.doModal();

	}

	@NotifyChange("")
	@Command
	public void schedule(@BindingParam("organId") Long organId) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		modal = (Window) Executions.createComponents("views/monitor/pagMonitoringActiosByOrgan.zul", ui, map);
		modal.doModal();

	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public List<OrganExecution> getOrganExecutions() {
		return organExecutions;
	}

	public void setOrganExecutions(List<OrganExecution> organExecutions) {
		this.organExecutions = organExecutions;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}
}
