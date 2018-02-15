package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.PhaseCycle;
import mz.ciuem.sipma.entity.PhaseOrganCycle;
import mz.ciuem.sipma.service.PhaseCycleService;
import mz.ciuem.sipma.service.PhaseOrganCycleService;

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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PhaseMonitorVM extends AbstractVM {

	private List<PhaseOrganCycle> phasesOrganCycle;
	
	private PhaseOrganCycle phaseOrganCycle;

	@WireVariable
	private PhaseOrganCycleService phaseOrganCycleService;
	
	@WireVariable
	private PhaseCycleService phaseCycleService;
	
	private PhaseCycle phaseCycle;

	private Component ui;

	private Window window;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		setPhasesOrganCycle(phaseOrganCycleService.allNotAcepted());
	}

	@NotifyChange({ "phasesOrganCycle" })
	@Command
	public void acept(@BindingParam("phaseId") Long phaseId) {

		PhaseOrganCycle found = phaseOrganCycleService.find(phaseId);
		found.setAcepted("acepted");
		phaseOrganCycleService.update(found);

		setPhasesOrganCycle(phaseOrganCycleService.allNotAcepted());
		refreshDataTable(null);
	}
	
	@NotifyChange({ "phaseCycle", "phaseOrganCycle" })
	@Command
	public void extendPhase(@BindingParam("phaseId") Long phaseId) {
		
		setPhaseOrganCycle(phaseOrganCycleService.find(phaseId));
		phaseCycle = phaseOrganCycle.getPhaseCycle();
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("phaseId", phaseId);
		window = (Window) Executions.createComponents(
				"views/monitor/extend-phase-modal.zul",
				ui, map);
		window.doModal();
		refreshDataTable(null);
	}
	
	@NotifyChange({ "phasesOrganCycle" })
	@Command
	public void updatePhase() {
		
		phaseOrganCycle.setAcepted("extended");
		phaseOrganCycleService.update(phaseOrganCycle);
		phaseCycleService.update(phaseCycle);

		Clients.showNotification("Fase prolongada com sucesso!", "info", ui, "before_center", -1);

		setPhasesOrganCycle(phaseOrganCycleService.allNotAcepted());

		window.detach();
		refreshDataTable(null);
	}

	@Command
	public void reprotByOrgan() {
		
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public List<PhaseOrganCycle> getPhasesOrganCycle() {
		return phasesOrganCycle;
	}

	public void setPhasesOrganCycle(List<PhaseOrganCycle> phasesOrganCycle) {
		this.phasesOrganCycle = phasesOrganCycle;
	}

	public PhaseCycle getPhaseCycle() {
		return phaseCycle;
	}

	public void setPhaseCycle(PhaseCycle phaseCycle) {
		this.phaseCycle = phaseCycle;
	}

	public PhaseOrganCycle getPhaseOrganCycle() {
		return phaseOrganCycle;
	}

	public void setPhaseOrganCycle(PhaseOrganCycle phaseOrganCycle) {
		this.phaseOrganCycle = phaseOrganCycle;
	}
}
