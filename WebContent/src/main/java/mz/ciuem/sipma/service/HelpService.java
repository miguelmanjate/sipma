package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Help;
import mz.ciuem.sipma.entity.User;

public interface HelpService extends GenericService<Help> {

	public List<Help> findBySender(User from);
}
