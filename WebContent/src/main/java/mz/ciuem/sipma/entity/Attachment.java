package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "attachment")
@Access(AccessType.FIELD)
public class Attachment extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	@Column(name = "subject")
	private String subject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment_type")
	private AttachmentType attachmentType;

	@Column(name = "registration_data")
	private Date registrationData;

	@Column(name = "attachment_date")
	private Date attachmentDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attachment")
	private List<SpecifiedAction> specifiedActions = new ArrayList<SpecifiedAction>();

	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> files = new HashSet<File>();

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	public List<SpecifiedAction> getSpecifiedActions() {
		return specifiedActions;
	}

	public void setSpecifiedActions(List<SpecifiedAction> specifiedActions) {
		this.specifiedActions = specifiedActions;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public Date getRegistrationData() {
		return registrationData;
	}

	public void setRegistrationData(Date registrationData) {
		this.registrationData = registrationData;
	}

	public Date getAttachmentDate() {
		return attachmentDate;
	}

	public void setAttachmentDate(Date attachmentDate) {
		this.attachmentDate = attachmentDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
