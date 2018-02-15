package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.model.ActionModel;
import mz.ciuem.sipma.service.ActionService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class NewDasboardVM extends AbstractVM {

	@WireVariable
	private ActionService actionService;

	private List<Action> actions;

	private ActionModel actionModel;

	@Wire
	private Calendars calendario;

	private Textbox filter;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);
		
	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;
		
		calendario = (Calendars) ui.getFellow("calendario");
		
		actions = actionService.getAll();
		LinkedList<CalendarEvent> ce = new LinkedList<CalendarEvent>();

		for (Action action : actions) {
			action.setContent("Actividade: " + action.getContent() + ", "
					+ "Orgão: " + action.getOrgan() + ", "
					+ "Duração: 3 dias");
			ce.add(action);
		}

		this.ui = view;

		actionModel = new ActionModel(ce);
		
		calendario.setModel(actionModel);
	}

	@Command
	public void today() {
		TimeZone timeZone = calendario.getDefaultTimeZone();
		calendario.setCurrentDate(Calendar.getInstance(timeZone).getTime());
	}

	@Command
	public void prev() {
		calendario.previousPage();
	}

	@Command
	public void next() {
		calendario.nextPage();
	}

	@Command
	public void applyFilter() {
		actionModel.setFilterText(filter.getValue());
		calendario.setModel(actionModel);
	}

	@Command
	public void resetFilter() {
		filter.setText("");
		actionModel.setFilterText("");
		calendario.setModel(actionModel);
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public ActionModel getActionModel() {
		return actionModel;
	}

	public void setActionModel(ActionModel actionModel) {
		this.actionModel = actionModel;
	}

}
