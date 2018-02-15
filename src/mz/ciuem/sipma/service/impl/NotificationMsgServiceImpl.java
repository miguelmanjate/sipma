package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.dao.NotificationMsgDao;
import mz.ciuem.sipma.entity.NotificationMsg;
import mz.ciuem.sipma.service.NotificationMsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationMsgService")
public class NotificationMsgServiceImpl extends GenericServiceImpl<NotificationMsg>
		implements NotificationMsgService {

	@Autowired
	private NotificationMsgDao notificationMsgDao;
	
	@Override
	public NotificationMsg getByType(String type) {
		// TODO Auto-generated method stub
		return notificationMsgDao.getByType(type);
	}

}
