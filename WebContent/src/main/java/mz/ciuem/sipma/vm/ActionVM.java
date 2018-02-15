package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zhtml.Span;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Criteria;
import mz.ciuem.sipma.entity.CriticalArea;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.StrategicObjective;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.service.ActionService;
import mz.ciuem.sipma.service.CriteriaService;
import mz.ciuem.sipma.service.CriticalAreaService;
import mz.ciuem.sipma.service.CycleService;
import mz.ciuem.sipma.service.StrategicObjectiveService;
import mz.ciuem.sipma.util.Breadcrumb;
import mz.ciuem.sipma.util.QueueMessage;
import mz.ciuem.sipma.util.QueueUtil;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ActionVM extends AbstractVM {

	// Calendar
	private boolean visible = false;

	private Action action;

	private List<Action> actions;

	private List<Criteria> firstCriterias;
	private List<Criteria> secondCriterias;
	private List<Criteria> thirdCriterias;
	private List<StrategicObjective> strategicObjectives;

	private List<CriticalArea> criticalAreas;
	private String term;

	private List<Cycle> cycles;
	
	@Wire
	private Div modal;

	@WireVariable
	private StrategicObjectiveService strategicObjectiveService;

	@WireVariable
	private CriticalAreaService criticalAreaService;

	@WireVariable
	private CriteriaService criteriaService;

	@WireVariable
	private CycleService cycleService;

	private List<Action> act;
	@WireVariable
	private ActionService actionService;

	private List<Action> actionsWithBudget;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		strategicObjectives = strategicObjectiveService.getAll();

		criticalAreas = criticalAreaService.getAll();

		firstCriterias = criteriaService.getCriteriaByType("Criterio1");
		secondCriterias = criteriaService.getCriteriaByType("Criterio2");
		thirdCriterias = criteriaService.getCriteriaByType("Criterio3");

		cycles = cycleService.findByOrganAndState(loggedOrgan, "Started");

		actions = actionService.filterByOrgan(loggedOrgan);
		
		act = actionService.getAll();

		actionsWithBudget = actionService.filterHaveBaseCalculating();

		action = new Action();
		action.setOrgan(loggedOrgan);

		// subscribe a queue, listen to other controller
		QueueUtil.lookupQueue().subscribe(new QueueListener());
	}

	@Command
	public void showDescription(@BindingParam("type") String type) {

		for (int i = 0; i < firstCriterias.size(); i++) {
			Label lbl = (Label) ui.getFellow("desc");

			lbl.setVisible(true);
			lbl.setValue(firstCriterias.get(i).getName());
		}
	}


	@SuppressWarnings("static-access")
	@Command
	public void printAct() throws JRException{
		if (act.isEmpty()) {

			Clients.showNotification("Informação Vazia", "info", modal,
					"middle_center", 3000);

		} else {

			mz.ciuem.sipma.util.MasterRep mas = new mz.ciuem.sipma.util.MasterRep();
			Map<String, Object> m = new HashMap<String, Object>();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/logo-uemmm.png");
			m.put("logo", inputV);
			m.put("filtragem", "asdasdsdsdada");
			Div modal = (Div) ui.getFellow("modal");
			mas.imprimir("/reports/actividades.jrxml", act, m, modal);
		

		}

	}
	
	@NotifyChange
	@Command
	public void specificActions(@BindingParam("actionId") Long actionId, @BindingParam("target") Div target,
			@BindingParam("bread") Div breadcrumb) {

		Action found = actionService.find(actionId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("action", found);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagRegisterSpecificAction.zul", target, map);

		List<String> links = new ArrayList<String>();
		links.add("Registar Acções Específicas");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);

	}
	
	@NotifyChange({ "actio" })
	@Command
	public void updateActivity(@BindingParam("actionId") Long actionId) {

		Action found = actionService.find(actionId);
       
		action = found;
		
		Div div_actiDetails = (Div) ui.getFellow("div_actiDetail");
		Div div_actiRegs = (Div) ui.getFellow("div_actiReg");
		Datebox dtx_dataInicios = (Datebox) ui.getFellow("dtx_dataInicio");
		Datebox dtx_fims = (Datebox) ui.getFellow("dtx_fim");
		Textbox tbx_descricaos = (Textbox) ui.getFellow("tbx_descricao");
		Span sp_nomes = (Span) ui.getFellow("sp_nome");
		Label paramIds = (Label) ui.getFellow("paramId");
		Div div_buttons = (Div) ui.getFellow("div_button");
		
		div_actiDetails.setVisible(false);
		div_actiRegs.setVisible(true);
		dtx_dataInicios.setDisabled(false);
		dtx_fims.setDisabled(false);
		tbx_descricaos.setVisible(true);
		sp_nomes.setVisible(true);
		paramIds.setVisible(false);
		div_buttons.setVisible(false);
		

	}

	@Command
	@NotifyChange("visible")
	public void cancel() {
		QueueMessage message = new QueueMessage(QueueMessage.Type.CANCEL);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	@Command
	@NotifyChange("visible")
	public void delete() {
		QueueMessage message = new QueueMessage(QueueMessage.Type.DELETE, action);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	@Command
	@NotifyChange("visible")
	public void ok() {
		QueueMessage message = new QueueMessage(QueueMessage.Type.OK, action);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	public boolean isAllDay(Date beginDate, Date endDate) {
		int M_IN_DAY = 1000 * 60 * 60 * 24;
		boolean allDay = false;

		if (beginDate != null && endDate != null) {
			long between = endDate.getTime() - beginDate.getTime();
			allDay = between > M_IN_DAY;
		}
		return allDay;
	}

	private class QueueListener implements EventListener<QueueMessage> {
		@Override
		public void onEvent(QueueMessage message) throws Exception {
			if (QueueMessage.Type.EDIT.equals(message.getType())) {
				ActionVM.this.startEditing((Action) message.getData());
			}
		}
	}

	private void startEditing(Action action) {
		this.action = action;
		visible = true;

		// reload entire view-model data when going to edit
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	public Validator getDateValidator() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				Map<String, Property> formData = ctx.getProperties(ctx.getProperty().getValue());
				Date beginDate = (Date) formData.get("beginDate").getValue();
				Date endDate = (Date) formData.get("endDate").getValue();
				if (beginDate == null) {
					addInvalidMessage(ctx, "beginDate", "Data do inicio esta vazia");
				}
				if (endDate == null) {
					addInvalidMessage(ctx, "endDate", "Data do fim esta vazia");
				}
				if (beginDate != null && endDate != null && beginDate.compareTo(endDate) >= 0) {
					addInvalidMessage(ctx, "endDate", "Data do fim esta antes da data do inicio");
				}
			}
		};
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public List<StrategicObjective> getStrategicObjectives() {
		return strategicObjectives;
	}

	public void setStrategicObjectives(List<StrategicObjective> strategicObjectives) {
		this.strategicObjectives = strategicObjectives;
	}

	public List<CriticalArea> getCriticalAreas() {
		return criticalAreas;
	}

	public void setCriticalAreas(List<CriticalArea> criticalAreas) {
		this.criticalAreas = criticalAreas;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Criteria> getFirstCriterias() {
		return firstCriterias;
	}

	public void setFirstCriterias(List<Criteria> firstCriterias) {
		this.firstCriterias = firstCriterias;
	}

	public List<Criteria> getSecondCriterias() {
		return secondCriterias;
	}

	public void setSecondCriterias(List<Criteria> secondCriterias) {
		this.secondCriterias = secondCriterias;
	}

	public List<Criteria> getThirdCriterias() {
		return thirdCriterias;
	}

	public void setThirdCriterias(List<Criteria> thirdCriterias) {
		this.thirdCriterias = thirdCriterias;
	}

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Organ getLoggedOrgan() {
		return loggedOrgan;
	}

	public void setLoggedOrgan(Organ loggedOrgan) {
		this.loggedOrgan = loggedOrgan;
	}

	public List<Action> getActionsWithBudget() {
		return actionsWithBudget;
	}

	public void setActionsWithBudget(List<Action> actionsWithBudget) {
		this.actionsWithBudget = actionsWithBudget;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Action> getAct() {
		return act;
	}

	public void setAct(List<Action> act) {
		this.act = act;
	}
}
