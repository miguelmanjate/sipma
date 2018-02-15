package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Purpose;
import mz.ciuem.sipma.service.PurposeService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
public class PurposeVM extends AbstractVM {

	private Purpose purpose;

	private List<Purpose> purposes;

	@WireVariable
	private PurposeService purposeService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		purpose = new Purpose();

		purposes = purposeService.getAll();
	}
	
	@NotifyChange({ "purpose", "purposes" })
	@Command
	public void save() {

		purposeService.create(purpose);

		log("Registou nova Finalidade: " + purpose.getPorpose());
		Clients.showNotification("Fonte #" + purpose.getId()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		purposes = purposeService.getAll();
		purpose = new Purpose();

		refreshDataTable(null);
	}

	@NotifyChange("purpose")
	@Command
	public void edit(@BindingParam("purposeID") Long purposeID) {

		purpose = (Purpose)purposeService.find(purposeID);

		editButtons();
	}

	@NotifyChange({ "purpose", "purposes" })
	@Command
	public void update() {

		purposeService.update(purpose);

		log("Actualizou a finalidade : " + purpose.getPorpose());
		Clients.showNotification("Fonte #" + purpose.getId()
				+ " Actualizada com sucesso!", "info", ui, "before_center", -1);

		purposes = purposeService.getAll();
		
		purpose = new Purpose();

		refreshDataTable(null);
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public List<Purpose> getPurposes() {
		return purposes;
	}

	public void setPurposes(List<Purpose> purposes) {
		this.purposes = purposes;
	}
}
