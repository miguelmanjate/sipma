package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.dao.AttachmentDao;
import mz.ciuem.sipma.entity.Attachment;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.service.AttachmentService;
import mz.ciuem.sipma.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attachmentService")
public class AttachmentServiceIpml extends GenericServiceImpl<Attachment>
		implements AttachmentService {

	@Autowired
	private FileService fileService;

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment create(Attachment attachment) {

		for (File file : attachment.getFiles()) {
			fileService.create(file);
		}
		return attachmentDao.create(attachment);
	}
}
