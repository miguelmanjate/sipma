package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.Attachment;

public interface AttachmentService extends GenericService<Attachment> {
	public Attachment create(Attachment attachment);
}
