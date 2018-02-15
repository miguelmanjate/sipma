package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.OrganType;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.OrganTypeService;
import mz.ciuem.sipma.service.UserRoleService;

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
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrganVM extends AbstractVM {

	private Organ organ;

	private Organ selectedOrgan;

	private List<OrganType> organTypes;

	private List<Organ> organs;

	private List<Employee> responsibles;

	private List<Employee> organEmployees;

	@WireVariable
	private OrganTypeService organTypeService;

	@WireVariable
	private OrganService organService;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private UserRoleService userRoleService;

	private Component ui;

	private Window window;

	private Employee currentResp;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		setOrganTypes(organTypeService.getAll());

		organ = new Organ();

		organs = organService.getAll();

		responsibles = employeeService.allWhereUserIdIsNotNull();

		this.ui = view;
	}

	@NotifyChange({ "organs", "organ" })
	@Command
	public void save() {

		organService.create(organ);

		log("Registou novo Orgão: " + organ.getDesignation());
		Clients.showNotification(organ.getInitials()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		organs = organService.getAll();
		organ = new Organ();

		refreshDataTable(null);
	}

	@NotifyChange("organ")
	@Command
	public void edit(@BindingParam("organId") Long organId) {

		organ = (Organ)organService.find(organId);

		editButtons();
		
	}

	@NotifyChange({ "organs", "organ" })
	@Command
	public void update() {

		organService.update(organ);

		log("Actualizou o Orgão: " + organ.getDesignation());
		Clients.showNotification(organ.getInitials()
				+ " Actualizado com sucesso!", "info", ui, "before_center", -1);

		organs = organService.getAll();
		organ = new Organ();

		refreshDataTable(null);
	}

	@Command
	public void setResponsible(@BindingParam("orgId") Long organId) {

		selectedOrgan = organService.find(organId);

		organEmployees = employeeService.allWhereUserIdIsNotNull(selectedOrgan);
		
		currentResp = selectedOrgan.getResponsible();

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("organ", selectedOrgan);
		window = (Window) Executions.createComponents(
				"/views/parameterization/organ/choose-responsible-modal.zul",
				ui, map);
		window.doModal();
	}

	@NotifyChange({ "organs" })
	@Command
	public void updateOrgan() {

		organService.update(selectedOrgan);

		if (currentResp != null) {
			User pastResp = currentResp.getUserLogin();
			pastResp.getRoles()
					.remove(userRoleService.findByName("OrganChief"));
			userService.update(pastResp);
		}

		User currResp = selectedOrgan.getResponsible().getUserLogin();
		currResp.getRoles().add(userRoleService.findByName("OrganChief"));
		userService.update(currResp);

		log("Atribuiu novo responsável ("
				+ selectedOrgan.getResponsible().fullName() + ") ao Orgão: "
				+ selectedOrgan.getDesignation());
		Clients.showNotification("Responsável selecionado com sucesso!",
				"info", ui, "before_center", -1);

		organs = organService.getAll();

		window.detach();
		refreshDataTable(null);
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
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

	public List<Employee> getResponsibles() {
		return responsibles;
	}

	public void setResponsibles(List<Employee> responsibles) {
		this.responsibles = responsibles;
	}

	public Organ getSelectedOrgan() {
		return selectedOrgan;
	}

	public void setSelectedOrgan(Organ selectedOrgan) {
		this.selectedOrgan = selectedOrgan;
	}

	public List<Employee> getOrganEmployees() {
		return organEmployees;
	}

	public void setOrganEmployees(List<Employee> organEmployees) {
		this.organEmployees = organEmployees;
	}
}
