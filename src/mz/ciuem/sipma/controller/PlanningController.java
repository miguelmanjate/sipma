package mz.ciuem.sipma.controller;

import java.util.LinkedList;
import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.model.ActionModel;
import mz.ciuem.sipma.service.ActionService;

import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

public class PlanningController extends AbsCalendarController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6236816849329283438L;

	private ActionModel actionModel;

	@Wire
	private Textbox filter;

	@WireVariable
	private ActionService actionService;

	private List<Action> actions;

	private Employee currEmployee;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		loggeduser = userService.getUser(authentication.getName());

		currEmployee = loggeduser.getResponsible();
		Sector sector = currEmployee.getSector();

		if (sector != null) {
			loggedOrgan = sector.getOrgan();
		}

		actions = actionService.getAll();
		LinkedList<CalendarEvent> ce = new LinkedList<CalendarEvent>();

		if (loggedOrgan != null) {
			for (Action action : actions) {
				action.setContent("Actividade: " + action.getContent() + ", "
						+ "Orgão: " + action.getOrgan());

				Organ tmp = action.getOrgan();
				if (tmp != null) {
					if (tmp.getId() == loggedOrgan.getId()) {
						action.setContentColor("#65CEA7");
					} else {
						action.setContentColor("#428bca");
					}
				} else {

					action.setContentColor("red");
					action.setContent("Actividade: " + action.getContent()
							+ ", " + "Orgão: Desconhecido(Erro)");
				}
				ce.add(action);
			}

			actionModel = new ActionModel(ce);

			calendars.setModel(this.actionModel);
		}

	}

	// control the filter
	@Listen("onClick = #applyFilter")
	public void applyFilter() {
		actionModel.setFilterText(filter.getValue());
		calendars.setModel(actionModel);
	}

	@Listen("onClick = #resetFilter")
	public void resetFilter() {
		filter.setText("");
		actionModel.setFilterText("");
		calendars.setModel(actionModel);
	}
}
