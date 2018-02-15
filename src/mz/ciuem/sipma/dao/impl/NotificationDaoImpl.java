package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.NotificationDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Notification;

import org.springframework.stereotype.Repository;

@Repository
public class NotificationDaoImpl extends GenericDaoImpl<Notification> implements
		NotificationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> filterByEmployee(Employee e) {
		
		Query query = em
				.createQuery("select n from Notification n where n.watched = false and n.employee = ?");
		query.setParameter(1, e);

		return query.getResultList();
	}

}
