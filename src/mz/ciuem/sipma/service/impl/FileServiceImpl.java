package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.service.FileService;

import org.springframework.stereotype.Service;

@Service("fileService")
public class FileServiceImpl extends GenericServiceImpl<File> implements
		FileService {

}
