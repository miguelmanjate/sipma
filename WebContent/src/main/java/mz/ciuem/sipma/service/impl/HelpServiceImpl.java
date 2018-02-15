package mz.ciuem.sipma.service.impl;

import java.util.List;

import mz.ciuem.sipma.dao.HelpDao;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.Help;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.FileService;
import mz.ciuem.sipma.service.HelpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("helpService")
public class HelpServiceImpl extends GenericServiceImpl<Help> implements
		HelpService {

	@Autowired
	private FileService fileService;
	
	@Autowired
	private HelpDao hpDao;

	@Override
	public Help create(Help prob) {

		File att = prob.getAttach();

		if (att != null) {
			fileService.create(att);
		}
		return hpDao.create(prob);
	}

	@Override
	public List<Help> findBySender(User from) {
		return hpDao.findBySender(from);
	}
}
