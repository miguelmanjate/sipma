package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.service.ProjectInvestimentService;

import org.springframework.stereotype.Service;

@Service("projectInvestimentService")
public class ProjectInvestimentServiceImpl extends GenericServiceImpl<ProjectInvestment> implements ProjectInvestimentService{
	
	public static final String NAME="mz.ciuem.sipma.service.impl.ProjectInvestimentServiceImpl";

}
