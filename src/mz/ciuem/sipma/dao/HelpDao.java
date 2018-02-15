package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Help;
import mz.ciuem.sipma.entity.User;

public interface HelpDao extends GenericDao<Help> {

	public List<Help> findBySender(User from);
}
