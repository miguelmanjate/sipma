package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Phase;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.PhaseCycleService;
import mz.ciuem.sipma.service.PhaseService;
import mz.ciuem.sipma.util.Breadcrumb;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CicleVM extends AbstractVM {

	@WireVariable
	private CycleService cycleService;

	@WireVariable
	private PhaseService phaseService;

	@WireVariable
	private PhaseCycleService phaseCycleService;

	@Wire
	private DualListbox selectedSpecifiedOrgans;

	private ListModelList<Organ> organs;

	private Cycle cycle;

	private List<Cycle> cycles;

	private List<Phase> phases;

	@WireVariable
	private OrganService organService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		Cycle found = (Cycle) Executions.getCurrent().getArg().get("cycle");

		if (found == null) {
			cycle = new Cycle();
		}

		else {

			this.cycle = found;

		}

		this.ui = view;

		organs = new ListModelList<Organ>(organService.getAll());
		cycle = new Cycle();
		setCycles(cycleService.getAll());
		phases = phaseService.getAll();
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "cycle", "filesList" })
	@Command
	public void saveCycle() {

		if (cycle.getType().equals("Normal")) {
			cycle.getOrgans().clear();
			cycle.getOrgans().addAll(organService.getAll());
		} else {
			cycle.getOrgans().addAll((Collection<? extends Organ>) selectedSpecifiedOrgans.getChosenDataList());
		}

		if (cycle.getOrgans().size() == 0) {
			Clients.showNotification("Selecione orgãos especiais antes!", "warning", selectedSpecifiedOrgans,
					"before_center", -1);

		} else {
			if (!filesList.isEmpty()) {
				cycle.getAttachments().addAll(filesList);
				filesList.clear();
			}
			cycleService.create(cycle);
			for (int i = 0; i < cycle.getOrgans().size(); i++) {

				log("Registou um novo Ciclo: " + cycle.toString());
//				notify(loggedOrgan.getResponsible(), organs.get(i).getInitials(), cycles.get(i).getDescription());
				Clients.showNotification("Ciclo " + cycle.toString() + " registado com sucesso!", "info", ui,
						"before_center", -1);
				cycle = new Cycle();
				selectedSpecifiedOrgans.unchooseAll();
				ui.getFellow("specific").setVisible(false);
			}
		}
	}

	@Command
	public void configCycle(@BindingParam("cycleId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		Cycle found = cycleService.find(cycleId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cycle", found);
		map.put("phases", phases);
		target.getChildren().clear();
		Executions.createComponents("views/cycle/pagConfigCycle.zul", target, map);

		ArrayList<String> links = new ArrayList<String>();
		links.add("Configurar");
		links.add(found.getSubject());
		Breadcrumb.drawn(breadcrumb, "Listar Ciclos", links);
	}

	@NotifyChange({ "cycle", "cycles" })
	@Command
	public void edit(@BindingParam("cycleId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		this.cycle = cycleService.find(cycleId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("cycle", cycle);
		map.put("cycles", cycles);
		target.getChildren().clear();
		Executions.createComponents("views/cycle/pagRegisterCycle.zul", target, map);

	}

	@Command
	public void checkCycletype(@BindingParam("val") String value) {

		if (value.equals("Especial")) {
			ui.getFellow("specific").setVisible(true);
		} else {
			ui.getFellow("specific").setVisible(false);
		}

		selectedSpecifiedOrgans.unchooseAll();
	}

	@Command
	public void limitEndDate(@BindingParam("begin") Date begin) {

		Datebox dbxEnd = (Datebox) ui.getFellow("dbxEnd");

		dbxEnd.setDisabled(false);

		dbxEnd.setRawValue(null);

		dbxEnd.setConstraint("after " + format(begin) + ", no empty");
	}

	public ListModelList<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(ListModelList<Organ> organs) {
		this.organs = organs;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}
}
