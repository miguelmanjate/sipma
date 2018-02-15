package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.ErrorLog;
import mz.ciuem.sipma.service.ErrorLogService;

import org.springframework.stereotype.Service;

@Service("errorLogService")
public class ErrorLogServiceImpl extends GenericServiceImpl<ErrorLog>
		implements ErrorLogService {

}
