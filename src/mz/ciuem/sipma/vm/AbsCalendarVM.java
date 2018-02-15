package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AbsCalendarVM {

	@Wire
	protected Calendars calendars;

	protected Component ui;

	// the in editing calendar ui event
	protected CalendarsEvent calendarsEvent = null;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;
	}

	// begin - control the calendar position
	@Command
	public void today() {
		TimeZone timeZone = calendars.getDefaultTimeZone();
		calendars.setCurrentDate(Calendar.getInstance(timeZone).getTime());
	}

	@Command
	public void next() {
		calendars.nextPage();
	}

	@Command
	public void previews() {
		calendars.previousPage();
	}

	@Command
	public void moldDay() {
		calendars.setMold("default");
		calendars.setDays(1);
	}

	@Command
	public void moldWeek() {
		calendars.setMold("default");
		calendars.setDays(7);
	}

	@Command
	public void moldYear() {
		calendars.setMold("month");
	}
	// end-control the calendar position

	public CalendarsEvent getCalendarsEvent() {
		return calendarsEvent;
	}

	public void setCalendarsEvent(CalendarsEvent calendarsEvent) {
		this.calendarsEvent = calendarsEvent;
	}
}
