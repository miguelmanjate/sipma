package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Notification;

public interface NotificationService extends GenericService<Notification> {

	public List<Notification> filterByEmployee(Employee e);
}
