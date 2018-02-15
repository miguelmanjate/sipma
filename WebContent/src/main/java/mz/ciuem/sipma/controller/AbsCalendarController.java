package mz.ciuem.sipma.controller;

import java.util.Calendar;
import java.util.TimeZone;

import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;

public class AbsCalendarController extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -882922971006100906L;

	@Wire
    protected Calendars calendars;
	
	@WireVariable
	protected UserService userService;
	
	@WireVariable
	protected OrganService organService;

	protected Authentication authentication = SecurityContextHolder.getContext()
			.getAuthentication();
	
	protected User loggeduser;
	
	protected Organ loggedOrgan;
    
  //the in editing calendar ui event
    protected CalendarsEvent calendarsEvent = null;
    
  //control the calendar position
    @Listen("onClick = #today")
    public void gotoToday(){
        TimeZone timeZone = calendars.getDefaultTimeZone();
        calendars.setCurrentDate(Calendar.getInstance(timeZone).getTime());
    }
    @Listen("onClick = #next")
    public void gotoNext(){
        calendars.nextPage();
    }
    @Listen("onClick = #prev")
    public void gotoPrev(){
        calendars.previousPage();
    }
     
    //control page display
    @Listen("onClick = #pageDay")
    public void changeToDay(){
        calendars.setMold("default");
        calendars.setDays(1);
    }
    @Listen("onClick = #pageWeek")
    public void changeToWeek(){
        calendars.setMold("default");
        calendars.setDays(7);
    }
    @Listen("onClick = #pageMonth")
    public void changeToYear(){
        calendars.setMold("month");
    }
}
