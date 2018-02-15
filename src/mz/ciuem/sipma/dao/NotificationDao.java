package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Notification;

public interface NotificationDao extends GenericDao<Notification> {

	public List<Notification> filterByEmployee(Employee e);
}
