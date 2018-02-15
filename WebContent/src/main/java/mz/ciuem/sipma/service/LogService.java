package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.User;

public interface LogService extends GenericService<Log> {

	public List<Log> filterByUser(User u);
}
