package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.OrganType;
import mz.ciuem.sipma.service.OrganTypeService;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrganTypeVM extends AbstractVM {

	private OrganType organType;

	private List<OrganType> organTypes;

	@Wire
	private Div organList;

	@WireVariable
	private OrganTypeService organTypeService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init() {

		setOrganType(new OrganType());
		organTypes = organTypeService.getAll();
	}

	@NotifyChange({ "organType", "organTypes" })
	@Command
	public void saveOrganType() {

		organTypeService.create(organType);

		log("Registou novo Tipo de Orgão: " + organType.getTitle());
		Clients.showNotification(organType.getInitials()
				+ " registado com sucesso!", "info", organList,
				"before_center", -1);

		organTypes = organTypeService.getAll();
		setOrganType(new OrganType());

		refreshDataTable(null);
	}

	@NotifyChange("organType")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setOrganType(organTypeService.find(id));
		editButtons();
	}

	@NotifyChange({ "organType", "organTypes" })
	@Command
	public void update() {

		organTypeService.update(organType);

		cancelButtons();

		log("Actualizou tipo de orgão: " + organType.getTitle());
		Clients.showNotification(organType.getInitials()
				+ " actualizado com sucesso!", "info", organList,
				"before_center", -1);

		organTypes = organTypeService.getAll();
		setOrganType(new OrganType());

		refreshDataTable(null);
	}

	@NotifyChange("*")
	@Command
	public void show(@BindingParam("typeId") Long id) {

	}

	@NotifyChange("*")
	@Command
	public void cancelButton() {

		setOrganType(new OrganType());
		cancelButtons();
	}

	public OrganType getOrganType() {
		return organType;
	}

	public void setOrganType(OrganType organType) {
		this.organType = organType;
	}

	public List<OrganType> getOrganTypes() {
		return organTypes;
	}

	public void setOrganTypes(List<OrganType> organTypes) {
		this.organTypes = organTypes;
	}
}
