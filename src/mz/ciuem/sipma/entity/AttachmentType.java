package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_type")
@Access(AccessType.FIELD)
public class AttachmentType extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String code;

	private String type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attachmentType")
	private List<Attachment> attachments = new ArrayList<Attachment>();

	public String getType() {
		return type;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
