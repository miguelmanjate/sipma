package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Attachment;
import mz.ciuem.sipma.entity.AttachmentType;
import mz.ciuem.sipma.service.AttachmentService;
import mz.ciuem.sipma.service.AttachmentTypeService;
import mz.ciuem.sipma.util.Breadcrumb;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AttachmentVM extends AbstractVM {

	private Attachment attach;

	private List<AttachmentType> attachTypes;

	private List<Attachment> attachments;

	@WireVariable
	private AttachmentTypeService attachmentTypeService;

	@WireVariable
	private AttachmentService attachmentService;
	

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		attach = new Attachment();
		
		attachments= attachmentService.getAll();

		Attachment found = (Attachment) Executions.getCurrent().getArg().get("attach");
		
		if (found == null) {
			attach = new Attachment();
		}

		else {
			this.attach = found;

		}

		setAttachTypes(attachmentTypeService.getAll());

		attachments = attachmentService.getAll();

	}

	@NotifyChange({ "attach", "attachments", "filesList" })
	@Command
	public void save() {

		attach.getFiles().addAll(filesList);
		attach.setRegistrationData(Calendar.getInstance().getTime());
		attachmentService.create(attach);

		log("Reportou novodocumento : " + attach.getCode());
		Clients.showNotification("Documento #" + attach.getCode()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		attachments = attachmentService.getAll();
		attach = new Attachment();

		refreshDataTable(null);
	}

	@NotifyChange
	@Command
	public void edit(@BindingParam("attachId") Long attachId,
			@BindingParam("target") Div target,
			@BindingParam("bread") Div breadcrumb) {
		Attachment found = attachmentService.find(attachId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("attach", found);
		target.getChildren().clear();
		Executions.createComponents("views/docments/pagRegisterActivity.zul",target, map);

		List<String> links = new ArrayList<String>();
		links.add("Registar Acções ");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);

	}

	public Attachment getAttach() {
		return attach;
	}

	public void setAttach(Attachment attach) {
		this.attach = attach;
	}

	public List<AttachmentType> getAttachTypes() {
		return attachTypes;
	}

	public void setAttachTypes(List<AttachmentType> attachTypes) {
		this.attachTypes = attachTypes;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

}
