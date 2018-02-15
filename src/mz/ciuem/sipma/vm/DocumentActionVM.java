package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Attachment;
import mz.ciuem.sipma.entity.SpecifiedAction;
import mz.ciuem.sipma.service.AttachmentService;
import mz.ciuem.sipma.service.SpecifiedActionService;

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
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DocumentActionVM extends AbstractVM {

	private SpecifiedAction specifiedAction;

	private Window modal;

	private Attachment attachment;
	
	private List<Attachment> attachments;
	
	@WireVariable
	private AttachmentService attachmentService;

	@WireVariable
	private SpecifiedActionService specifiedActionService;

	private List<SpecifiedAction> specifiedActions;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;
		
		attachments=attachmentService.getAll();

		Attachment found = (Attachment) Executions.getCurrent()
				.getArg().get("attachment");

		if (found == null) {
			attachment = new Attachment();
		}

		else {
			this.attachment = found;

		}

		specifiedAction = new SpecifiedAction();
		setAttachment(attachment);
		specifiedActions = new ArrayList<SpecifiedAction>();

	}

	@NotifyChange({ "specifiedActions", "specifiedAction" })
	@Command
	public void addAction() {
		
		attachment = specifiedAction.getAttachment();
		specifiedAction.setUpdated(Calendar.getInstance().getTime());
		specifiedAction.setAttachment(attachment);
		specifiedActions.add(specifiedAction);

		log("Adicionou: " + specifiedAction.getDesignation());
		Clients.showNotification("Accao adicionada com sucesso!", "info",
				ui.getFellow("tblBaseCal"), "before_center", -1);
		refreshDataTable(null);
	}

	@NotifyChange({ "specifiedActions", "specifiedAction" })
	@Command
	public void save() {
		 specifiedActionService.createActions(specifiedActions);
		log("Registou: " + specifiedAction.getDesignation());
		Clients.showNotification("Accoes Registadas com sucesso!", "info",
				ui.getFellow("tblBaseCal"), "before_center", -1);
		refreshDataTable(null);

	}

	@NotifyChange({ "specifiedActions", "specifiedAction" })
	@Command
	public void edit(@BindingParam("specID") Long specID,
			@BindingParam("tgt") Div target) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		modal = (Window) Executions.createComponents(
				"views/docments/alocActOrgans.zul", ui, map);
		modal.doModal();
	}

	public SpecifiedAction getSpecifiedAction() {
		return specifiedAction;
	}

	public void setSpecifiedAction(SpecifiedAction specifiedAction) {
		this.specifiedAction = specifiedAction;
	}

	public List<SpecifiedAction> getSpecifiedActions() {
		return specifiedActions;
	}

	public void setSpecifiedActions(List<SpecifiedAction> specifiedActions) {
		this.specifiedActions = specifiedActions;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}
