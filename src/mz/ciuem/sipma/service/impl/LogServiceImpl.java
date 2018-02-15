package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.LogDao;
import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl extends GenericServiceImpl<Log> implements
		LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public List<Log> filterByUser(User u) {
		// TODO Auto-generated method stub
		return logDao.filterByUser(u);
	}

}
