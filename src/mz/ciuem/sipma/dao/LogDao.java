package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.User;

public interface LogDao extends GenericDao<Log> {

	public List<Log> filterByUser(User u);
}
