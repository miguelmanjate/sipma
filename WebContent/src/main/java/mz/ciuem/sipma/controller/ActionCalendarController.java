package mz.ciuem.sipma.controller;

import java.util.LinkedList;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.model.ActionModel;
import mz.ciuem.sipma.service.ActionService;
import mz.ciuem.sipma.service.LogService;
import mz.ciuem.sipma.util.QueueMessage;
import mz.ciuem.sipma.util.QueueUtil;

import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ActionCalendarController extends AbsCalendarController {

	private static final long serialVersionUID = 1L;

	private ActionModel actionModel;

	@WireVariable
	private ActionService actionService;
	
	@WireVariable
	protected LogService logService;

	@Wire
	private Textbox filter;
	
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

		actionModel = new ActionModel(new LinkedList<CalendarEvent>(
				actionService.filterByOrgan(loggedOrgan)));

		calendars.setModel(this.actionModel);
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

	// listen to the calendar-create and edit of a event data
	@Listen("onEventCreate = #calendars; onEventEdit = #calendars")
	public void createEvent(CalendarsEvent event) {
		calendarsEvent = event;

		// to display a shadow when editing
		calendarsEvent.stopClearGhost();

		Action data = (Action) event.getCalendarEvent();

		if (data == null) {
			data = new Action();
			data.setHeaderColor("#3366ff");
			data.setContentColor("#6699ff");
			data.setLocked(false);
			data.setBeginDate(event.getBeginDate());
			data.setEndDate(event.getEndDate());
		} else {
			data = (Action) event.getCalendarEvent();
		}
		// notify the editor
		QueueUtil.lookupQueue().publish(
				new QueueMessage(QueueMessage.Type.EDIT, data));
	}

	// listen to the calendar-update of event data, usually send when user drag
	// the event data
	@Listen("onEventUpdate = #calendars")
	public void updateEvent(CalendarsEvent event) {
		Action data = (Action) event.getCalendarEvent();
		data.setBeginDate(event.getBeginDate());
		data.setEndDate(event.getEndDate());
		actionModel.update(data);
	}

	// listen to queue message from other controller
	@Subscribe(value = QueueUtil.QUEUE_NAME)
	public void handleQueueMessage(Event event) {

		if (!(event instanceof QueueMessage)) {
			return;
		}
		QueueMessage message = (QueueMessage) event;
		switch (message.getType()) {
		case DELETE:
			Action ac = (Action) message.getData();

			actionModel.remove((Action) message.getData());

			delete(ac);

			// clear the shadow of the event after editing
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		case OK:
			Action action = (Action) message.getData();

			if (actionModel.indexOf(action) >= 0) {
				actionModel.update(action);
				update(action);
			} else {
				actionModel.add(action);
				save(action);
			}

		case CANCEL:
			// clear the shadow of the event after editing
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		default:
			break;
		}
	}

	private void save(Action action) {

		if (currEmployee.getSector() == null) {
			Clients.showNotification(
					"Actividade não registada na base de dados, autentique com o perfil orgão!",
					"warning", this.getSelf(), "before_center", -1);
		} else {
			action.setDesignation(action.getContent());
			action.setLocked(false);
			action.setOrgan(loggedOrgan);
			actionService.create(action);

			log("Registou uma nova actividade: "+action.getContent());
			Clients.showNotification("Actividade: " + action.getContent()
					+ " registada com sucesso! ", "info", this.getSelf(),
					"before_center", -1);
		}

	}

	private void update(Action action) {

		actionService.update(action);

		Clients.showNotification("Acção " + action.getId()
				+ " actualizada com sucesso!", "info", this.getSelf(),
				"before_center", -1);
	}

	private void delete(Action action) {

		if (action.getId() != null) {
			actionService.delete(action.getId());

			Clients.showNotification("Acção " + action.getId()
					+ " removida com sucesso!", "info", this.getSelf(),
					"before_center", -1);
		}
	}
	
	public void log(String log) {

		Log logDb = new Log(loggeduser, log);
		logService.create(logDb);
	}
}
