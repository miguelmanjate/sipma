package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.AttachmentType;
import mz.ciuem.sipma.service.AttachmentTypeService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AttachmentTypeVM extends AbstractVM {

	private AttachmentType attachmentType;

	@WireVariable
	private AttachmentTypeService attachmentTypeService;
	
	private List<AttachmentType> attachmentTypes;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		attachmentType = new AttachmentType();
		
		attachmentTypes = attachmentTypeService.getAll();

	}

	@Command("save")
	@NotifyChange({ "attachmentType", "attachmentTypes" })
	public void save() {

		attachmentTypeService.create(attachmentType);
		
		log("Reportou novo Tipo de Documento : " + attachmentType.getType());
		Clients.showNotification("Tipo de Documento #" + attachmentType.getType()+ " registado com sucesso!", "info", ui, "before_center", -1);
		
		attachmentTypes = attachmentTypeService.getAll();
		attachmentType = new AttachmentType();

		refreshDataTable(null);
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public List<AttachmentType> getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(List<AttachmentType> attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}
}
