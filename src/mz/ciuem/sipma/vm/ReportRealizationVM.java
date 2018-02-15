package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Embarrassment;
import mz.ciuem.sipma.entity.PhaseCycle;
import mz.ciuem.sipma.entity.PhaseOrganCycle;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.PhaseCycleService;
import mz.ciuem.sipma.service.PhaseOrganCycleService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ReportRealizationVM extends AbstractVM {

	private List<Cycle> cycles;

	private List<PhaseCycle> phaseCycles;

	private PhaseOrganCycle phaseOrganCycle;

	private List<PhaseOrganCycle> phasesOrganCycle;

	private Embarrassment embarrassment;

	@WireVariable
	private CycleService cycleService;

	@WireVariable
	private PhaseCycleService phaseCycleService;

	@WireVariable
	private PhaseOrganCycleService phaseOrganCycleService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		cycles = cycleService.findByOrganAndState(loggedOrgan, "Started");

		phaseOrganCycle = new PhaseOrganCycle();

		phasesOrganCycle = phaseOrganCycleService.filterByOrgan(loggedOrgan);
	}

	@NotifyChange({ "phaseOrganCycle", "embarrassment", "phasesOrganCycle", "filesList" })
	@Command
	public void report() {

		if (!phaseOrganCycle.isSuccessfully()) {
			phaseOrganCycle.setEmbarrassment(embarrassment);
			phaseOrganCycle.setAcepted("embarrassment");
		}

		phaseOrganCycle.setRealizationPercentage(Math.round(100 / phaseCycles.size()));
		phaseOrganCycle.setOrgan(loggedOrgan);
		phaseOrganCycle.setAcepted("success");
		phaseOrganCycleService.create(phaseOrganCycle);

		log("Reportou '" + phaseOrganCycle.getExecutedActivity() + "' na fase: "
				+ phaseOrganCycle.getPhaseCycle().getPhase().getDesignacao());
		Clients.showNotification("Relatório da fase submetido com sucesso!", "info", ui, "before_center", -1);

		filesList.clear();

		ui.getFellow("tbl").invalidate();

		phaseOrganCycle = new PhaseOrganCycle();
		embarrassment = new Embarrassment();

		ui.getFellow("rgTipo").invalidate();
		ui.getFellow("cycles").invalidate();
	}

	@NotifyChange("phaseCycles")
	@Command
	public void findPhasesByCycle(@BindingParam("cycleId") Long id) {

		Cycle found = cycleService.find(id);
		phaseCycles = phaseCycleService.filterByCycle(found);
	}

	@NotifyChange("embarrassment")
	@Command
	public void haveEmbarrassment(@BindingParam("resp") Boolean answer) {

		phaseOrganCycle.setSuccessfully(answer);
		ui.getFellow("embarrassment").setVisible(!answer);

		embarrassment = new Embarrassment();
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

	public List<PhaseCycle> getPhaseCycles() {
		return phaseCycles;
	}

	public void setPhaseCycles(List<PhaseCycle> phaseCycles) {
		this.phaseCycles = phaseCycles;
	}

	public PhaseOrganCycle getPhaseOrganCycle() {
		return phaseOrganCycle;
	}

	public void setPhaseOrganCycle(PhaseOrganCycle phaseOrganCycle) {
		this.phaseOrganCycle = phaseOrganCycle;
	}

	public Embarrassment getEmbarrassment() {
		return embarrassment;
	}

	public void setEmbarrassment(Embarrassment embarrassment) {
		this.embarrassment = embarrassment;
	}

	public List<PhaseOrganCycle> getPhasesOrganCycle() {
		return phasesOrganCycle;
	}

	public void setPhasesOrganCycle(List<PhaseOrganCycle> phasesOrganCycle) {
		this.phasesOrganCycle = phasesOrganCycle;
	}
}
