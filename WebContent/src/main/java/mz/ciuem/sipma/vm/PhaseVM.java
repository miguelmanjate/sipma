package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Phase;
import mz.ciuem.sipma.service.PhaseService;

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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PhaseVM extends AbstractVM {

	@WireVariable
	private PhaseService phaseService;

	private Phase phase;

	private List<Phase> phases;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		ui = view;

	}

	@Init
	public void init() {

		phases = phaseService.getAll();

		phase = new Phase();
	}

	@NotifyChange({ "phase", "phases" })
	@Command
	public void save() {

		phaseService.create(phase);

		log("Registou nova Fase: " + phase.getDesignacao());
		Clients.showNotification("Fase " + phase.getDesignacao()
				+ " gravada com sucesso!", Clients.NOTIFICATION_TYPE_INFO,
				ui.getFellow("phase"), "before_center", -1);

		phase = new Phase();

		phases = phaseService.getAll();

		refreshDataTable(null);
	}

	@NotifyChange("phase")
	@Command
	public void edit(@BindingParam("phaseId") Long phaseId) {

		phase = (Phase) phaseService.find(phaseId);
		
		editButtons();

	}

	@NotifyChange({ "phase", "phases" })
	@Command
	public void update() {
		phaseService.update(phase);
		
		cancelButtons();

		log("Actualizou a Fase" + phase.getDesignacao());
		Clients.showNotification("Fase " + phase.getDesignacao()
				+ " Actualizada com sucesso!", Clients.NOTIFICATION_TYPE_INFO,
				ui.getFellow("phase"), "before_center", -1);

		setPhase(new Phase());

		phases = phaseService.getAll();
		
		refreshDataTable(null);

	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
}
