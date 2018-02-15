package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Phase;
import mz.ciuem.sipma.entity.PhaseCycle;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.PhaseCycleService;
import mz.ciuem.sipma.util.SendSMS;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

import com.mashape.unirest.http.exceptions.UnirestException;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ConfigCycleVM extends AbstractVM {

	@WireVariable
	private CycleService cycleService;

	@WireVariable
	private PhaseCycleService phaseCycleService;

	@Wire
	private Window winConfig;

	private Cycle cycle;

	private List<Phase> phases;

	private PhaseCycle phaseCycle;

	private List<PhaseCycle> phaseCycles;

	private List<Organ> organNotInCycle;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private OrganService organService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	public void noitification() throws IOException, UnirestException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Employee> employees = employeeService.getAll();
		for (int i = 0; i < employees.size(); i++) {

			SendSMS.sms(
					employees.get(i).getCellPhone(),
					"Sr. Director Informamos que o NOVO  CICLO DE PLANIFICACAO 2016/17 esta aberto. Cada unidade deve submeter o seu plano ate "
							+ sdf.format(cycle.getEndDate()) + "." + "\n" + "");
		}

		System.out.println(employees.get(0).getName());
	}

	@Init(superclass = true)
	public void init(@ExecutionArgParam("cycle") Cycle cycle,
			@ExecutionArgParam("phases") List<Phase> phases,
			@ContextParam(ContextType.VIEW) Component view) {

		ui = view;

		this.cycle = cycle;
		this.phases = phases;

		phaseCycle = new PhaseCycle();
		phaseCycle.setCycle(cycle);
		setPhaseCycles(this.cycle.getPhaseCycles());

		setOrganNotInCycle(organService.findAllNotInCycle(cycle));

	}

	@NotifyChange({ "phaseCycle", "phaseCycles", "winConfig" })
	@Command
	public void addPhaseCycle(@ContextParam(ContextType.VIEW) Component view) {

		phaseCycleService.create(phaseCycle);

		log("Adicionou a fase: " + phaseCycle.getPhase().getDesignacao()
				+ " ao Ciclo: " + cycle.toString());
		Clients.showNotification("Fase registada com sucesso!", "info",
				winConfig, "before_center", -1);

		phaseCycle = new PhaseCycle();
		phaseCycle.setCycle(cycle);

		this.phaseCycles = cycleService.find(cycle.getId()).getPhaseCycles();

		refreshDataTable(ui.getFellow("phaseCyclesPanel"));
	}

	@NotifyChange({ "organs" })
	@Command
	public void addOrganInCycle(@ContextParam(ContextType.VIEW) Component view) {

		cycle.getOrgans().addAll(organNotInCycle);
		cycleService.update(cycle);
	}

	@Command
	public void limitEndDate(@BindingParam("begin") Date begin,
			@BindingParam("end") String end) {

		Datebox dbxEnd = (Datebox) ui.getFellow("dbxEnd");

		dbxEnd.setDisabled(false);

		dbxEnd.setRawValue(null);

		dbxEnd.setConstraint("between " + format(begin) + " and " + end
				+ ", no empty");
	}

	@NotifyChange("cycle")
	@Command
	public void updateState(@BindingParam("state") final String state) {

		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
			public void onEvent(ClickEvent event) throws Exception {
				if (Messagebox.Button.YES.equals(event.getButton())) {
					if (cycle.getPhaseCycles().isEmpty()) {
						Clients.showNotification(
								"O ciclo deve ter pelo menos uma fase!!!",
								"error", ui, "before_center", -1);
					} else {
						cycle.setState(state);
						cycleService.update(cycle);
						log("Mudou o estado do ciclo: " + cycle.toString()
								+ " para: " + cycle.getState());
						Clients.showNotification("Ciclo " + i18n(state) + "!",
								"info", ui, "before_center", -1);
					}
				}
			}
		};
		Messagebox.show("Tem certeza que deseja colocar o Ciclo " + i18n(state)
				+ "?", "ALteração do estado do Ciclo", new Messagebox.Button[] {
				Messagebox.Button.YES, Messagebox.Button.NO },
				Messagebox.QUESTION, clickListener);
	}

	private String i18n(String trans) {

		if (trans.equals("Started")) {
			return "em curso";

		}
		if (trans.equals("Stopped")) {
			return "parado";
		} else {
			return "em pausa";
		}
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	public PhaseCycle getPhaseCycle() {
		return phaseCycle;
	}

	public void setPhaseCycle(PhaseCycle phaseCycle) {
		this.phaseCycle = phaseCycle;
	}

	public List<PhaseCycle> getPhaseCycles() {
		return phaseCycles;
	}

	public void setPhaseCycles(List<PhaseCycle> phaseCycles) {
		this.phaseCycles = phaseCycles;
	}

	public List<Organ> getOrganNotInCycle() {
		return organNotInCycle;
	}

	public void setOrganNotInCycle(List<Organ> organNotInCycle) {
		this.organNotInCycle = organNotInCycle;
	}

}
