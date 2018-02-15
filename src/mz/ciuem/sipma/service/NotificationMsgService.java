package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.NotificationMsg;

public interface NotificationMsgService extends GenericService<NotificationMsg> {

	public NotificationMsg getByType(String type);
}
