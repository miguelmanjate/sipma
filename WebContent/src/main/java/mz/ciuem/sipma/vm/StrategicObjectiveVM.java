package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.StrategicObjective;
import mz.ciuem.sipma.service.StrategicObjectiveService;

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
public class StrategicObjectiveVM extends AbstractVM {

	private StrategicObjective strategicObjective;

	private List<StrategicObjective> strategicObjectives;

	@WireVariable
	private StrategicObjectiveService strategicObjectiveService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		strategicObjective = new StrategicObjective();

		strategicObjectives = strategicObjectiveService.getAll();
	}

	@NotifyChange({ "strategicObjectives", "strategicObjective" })
	@Command
	public void save() {

		strategicObjectiveService.create(strategicObjective);

		log("Registou novo Objectivo Estratégico: "
				+ strategicObjective.getDesignation());
		Clients.showNotification("Objectivo #" + strategicObjective.getId()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		strategicObjectives = strategicObjectiveService.getAll();
		strategicObjective = new StrategicObjective();

		refreshDataTable(null);
	}

	@NotifyChange("strategicObjective")
	@Command
	public void edit(
			@BindingParam("strategicObjectiveId") Long strategicObjectiveId) {
		strategicObjective = (StrategicObjective)strategicObjectiveService
				.find(strategicObjectiveId);
		editButtons();

	}

	@NotifyChange({ "strategicObjectives", "strategicObjective" })
	@Command
	public void update() {

		strategicObjectiveService.update(strategicObjective);

		log("Actualizou  Objectivo Estratégico: "
				+ strategicObjective.getDesignation());
		Clients.showNotification("Objectivo #" + strategicObjective.getId()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		strategicObjectives = strategicObjectiveService.getAll();
		strategicObjective = new StrategicObjective();

		refreshDataTable(null);
	}

	public StrategicObjective getStrategicObjective() {
		return strategicObjective;
	}

	public void setStrategicObjective(StrategicObjective strategicObjective) {
		this.strategicObjective = strategicObjective;
	}

	public List<StrategicObjective> getStrategicObjectives() {
		return strategicObjectives;
	}

	public void setStrategicObjectives(
			List<StrategicObjective> strategicObjectives) {
		this.strategicObjectives = strategicObjectives;
	}
}
