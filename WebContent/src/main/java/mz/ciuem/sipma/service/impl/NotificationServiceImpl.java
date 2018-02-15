package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.NotificationDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Notification;
import mz.ciuem.sipma.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImpl extends GenericServiceImpl<Notification>
		implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	@Override
	public List<Notification> filterByEmployee(Employee e) {
		// TODO Auto-generated method stub
		return notificationDao.filterByEmployee(e);
	}

}
