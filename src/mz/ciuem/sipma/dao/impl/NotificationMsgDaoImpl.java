package mz.ciuem.sipma.dao.impl;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.NotificationMsgDao;
import mz.ciuem.sipma.entity.NotificationMsg;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationMsgDaoImpl extends GenericDaoImpl<NotificationMsg>
		implements NotificationMsgDao {

	@SuppressWarnings("unchecked")
	@Override
	public NotificationMsg getByType(String type) {
		
		Query query = em
				.createQuery("select nm from NotificationMsg nm where nm.type = ?");
		query.setParameter(1, type);

		return (NotificationMsg) DataAccessUtils.singleResult(query.getResultList());
	}

}
