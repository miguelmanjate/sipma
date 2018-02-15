package mz.ciuem.sipma.dao;

import mz.ciuem.sipma.entity.NotificationMsg;

public interface NotificationMsgDao extends GenericDao<NotificationMsg> {

	public NotificationMsg getByType(String type);
}
