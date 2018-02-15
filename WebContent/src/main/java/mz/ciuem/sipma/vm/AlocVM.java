package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.OrganType;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.OrganTypeService;
import mz.ciuem.sipma.service.SpecifiedActionService;

import org.zkoss.bind.annotation.AfterCompose;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AlocVM extends AbstractVM {

	@WireVariable
	private OrganTypeService organTypeService;

	@WireVariable
	private OrganService organService;

	private Organ organ;

	private List<OrganType> organTypes;

	@Wire
	private Listbox lstTypes;

	private List<Organ> organs;

	private AllocationOrgan allocationOrgan;

	@WireVariable
	private SpecifiedActionService specifiedActionService;

	private List<SpecialAction> specialActions;

	@Wire
	private DualListbox selectedRoles;

	@Wire
	private Window responsibleChooser;
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		setOrganTypes(organTypeService.getAll());
		setAllocationOrgan(new AllocationOrgan());
		organs = organService.getAll();


	}

	@NotifyChange("selectedRoles")
	@Command
	public void getOrgansByType() {
		
		organs = new ListModelList<Organ>(organService.getAll());

	}
	
	@Command
	public void save(){
		log("Alocou Actividades com Sucesso" );
		Clients.showNotification("Alocou Actividades com Sucesso",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);
		
		responsibleChooser.detach();
	}

	public List<OrganType> getOrganTypes() {
		return organTypes;
	}

	public void setOrganTypes(List<OrganType> organTypes) {
		this.organTypes = organTypes;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public List<SpecialAction> getSpecialActions() {
		return specialActions;
	}

	public void setSpecialActions(List<SpecialAction> specialActions) {
		this.specialActions = specialActions;
	}

	public AllocationOrgan getAllocationOrgan() {
		return allocationOrgan;
	}

	public void setAllocationOrgan(AllocationOrgan allocationOrgan) {
		this.allocationOrgan = allocationOrgan;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

}
