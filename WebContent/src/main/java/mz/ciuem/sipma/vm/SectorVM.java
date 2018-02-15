package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.SectorService;
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
public class SectorVM extends AbstractVM {

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private OrganService organService;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private UserRoleService userRoleService;

	private Sector sector;

	private List<Sector> sectors;

	private List<Organ> organs;

	private List<Employee> responsibles;

	private Component ui;

	private Sector selectedSector;

	private List<Employee> sectorEmployees;

	private Window window;

	private Employee currentResp;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);

		sector = new Sector();
		sectors = sectorService.getAll();

		setOrgans(organService.getAll());

		setResponsibles(responsibles = employeeService
				.allWhereUserIdIsNotNull());

		this.ui = view;
	}

	@NotifyChange({ "sector", "sectors" })
	@Command
	public void saveSector() {

		sectorService.create(sector);

		sector = new Sector();

		sectors = sectorService.getAll();

		log("Registou novo sector: " + sector.getDesignation());
		Clients.showNotification("Sector gravado com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		refreshDataTable(null);
	}

	@NotifyChange("sector")
	@Command
	public void edit(@BindingParam("sectId") Long sectId) {
		sector = (Sector) sectorService.find(sectId);
		editButtons();
	}

	@NotifyChange({ "sector", "sectors" })
	@Command
	public void update() {

		sectorService.update(sector);

		sector = new Sector();

		sectors = sectorService.getAll();

		log("Actualizou novo sector: " + sector.getDesignation());
		Clients.showNotification("Sector Actualizado com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		refreshDataTable(null);

		cancelButtons();

	}

	@Command
	public void setResponsible(@BindingParam("sectId") Long sectorId) {

		selectedSector = sectorService.find(sectorId);

		currentResp = selectedSector.getResponsible();

		sectorEmployees = employeeService
				.allWhereUserIdIsNotNull(selectedSector);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sector", selectedSector);
		window = (Window) Executions.createComponents(
				"/views/parameterization/sector/choose-responsible-modal.zul",
				ui, map);
		window.doModal();
	}

	@NotifyChange({ "sectors" })
	@Command
	public void updateSector() {

		sectorService.update(selectedSector);

		if (currentResp != null) {
			User pastResp = currentResp.getUserLogin();
			pastResp.getRoles().remove(
					userRoleService.findByName("SectorChief"));
			userService.update(pastResp);
		}

		User currResp = selectedSector.getResponsible().getUserLogin();
		currResp.getRoles().add(userRoleService.findByName("SectorChief"));
		userService.update(currResp);

		Clients.showNotification("Responsável selecionado com sucesso!",
				"info", ui, "before_center", -1);

		sectors = sectorService.getAll();

		window.detach();
		refreshDataTable(null);
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
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

	public List<Employee> getSectorEmployees() {
		return sectorEmployees;
	}

	public void setSectorEmployees(List<Employee> sectorEmployees) {
		this.sectorEmployees = sectorEmployees;
	}

	public Sector getSelectedSector() {
		return selectedSector;
	}

	public void setSelectedSector(Sector selectedSector) {
		this.selectedSector = selectedSector;
	}
}
