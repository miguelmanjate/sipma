package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Profession;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.ProfessionService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.SubSectorService;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeePfiVM extends AbstractVM {

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private OrganService organService;

	@WireVariable
	private SubSectorService subSectorService;

	@WireVariable
	private ProfessionService professionService;

	private List<Profession> professions;
	// Filters actions
	private boolean clearFilters;
	private boolean emptyResults;

	private Employee employee;

	private List<Employee> employees;

	private List<Organ> organs;
	
	private List<Organ> organS;

	private List<Sector> sectors;

	private List<SubSector> subSectors;

	private Component ui;
	
	private Window win;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		this.ui = view;

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);
		
		loggeduser = userService.getUser(authentication.getName());

		loggedOrgan = organService.getOrganByResponsible(loggeduser.getResponsible());

		loggedSector = sectorService.getSectorByResponsible(loggeduser.getResponsible());

		sectors = sectorService.filterByOrgan(loggeduser.getOrgan());
		
		Employee found = (Employee) Executions.getCurrent().getArg().get("employee");
		
		if (found == null) {
			employee = new Employee();
		}

		else {
			this.employee = found;

		}
		
		//Por acertar
	//	organs = lorgan;
		
//		organS = organService.getAll();
		
		employees = employeeService.filterByOrgan(loggedOrgan);

		professions = professionService.getAll();
		
	//	subSectors= subSectorService.getAll();

		//employees = employeeService.filterByOrgan(found.getOrgan())

		setClearFilters(false);

		emptyResults = false;
	}
	
	@NotifyChange({"organs","employees"})
	@Command
	public void onSelectOrgan(@BindingParam("sector_id") Long id) {
		Sector  sect = sectorService.find(id);
	//	employees = employeeService.filterByOrgan(sect.getOrgan());
		employees = employeeService.filterBySector(sect);
		refreshDataTable(null);
	}

	@NotifyChange({ "employees", "clearFilters", "emptyResults" })
	@Command
	public void findByFilter(@BindingParam("filter") String filter) {

		setClearFilters(true);

		employees = employeeService.findByFilter(filter);

		if (employees.size() == 0) {
			emptyResults = true;
		} else {
			emptyResults = false;
		}
	}

	@NotifyChange("sectors")
	@Command
	public void selectSector(@BindingParam("organ_id") Long id) {

		Organ org = organService.find(id);

		sectors = sectorService.getOrganBySector(org);

	}

	@NotifyChange("subSectors")
	@Command
	public void getSubSectorsBySector(@BindingParam("sector_id") Long id) {

		Sector sect = sectorService.find(id);

		subSectors = subSectorService.getSubSectorsBySector(sect);
		List<Organ> lorgan = new ArrayList<Organ>();
		employee.setOrgan(sect.getOrgan());
		
		
	}

	@NotifyChange({ "employee" })
	@Command
	public void save() {

		employeeService.create(employee);

		log("Registou novo funcionário: " + employee.fullName());
		Clients.showNotification("Funcionario registado com sucesso!", "info",
				ui.getFellow("panelFunc"), "before_center", -1);

		employee = new Employee();
	}

	@NotifyChange({ "employees", "clearFilters" })
	@Command
	public void cancelFilters() {

		//employees = employeeService.getAll();

		setClearFilters(false);
	}

	@NotifyChange({ "employee", "employees" })
	@Command
	public void edit(@BindingParam("empId") Long empId,
			@BindingParam("tgt") Div target) {

		this.employee = employeeService.find(empId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("employee", employee);
		map.put("employees", employees);
		target.getChildren().clear();
		Executions.createComponents("views/employees/pagRegisterEmployee.zul",
				target, map);

	}

	@NotifyChange({ "employee", "employees" })
	@Command
	public void update() {

		employeeService.update(employee);

		log("Actualizou o Funcionario" + employee.getName());
		Clients.showNotification("Funcionario " + employee.getName()
				+ " Actualizada com sucesso!", Clients.NOTIFICATION_TYPE_INFO,
				ui.getFellow("emp"), "before_center", -1);

		employee = new Employee();

		//employees = employeeService.getAll();

	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<SubSector> getSubSectors() {
		return subSectors;
	}

	public void setSubSectors(List<SubSector> subSectors) {
		this.subSectors = subSectors;
	}

	public boolean isClearFilters() {
		return clearFilters;
	}

	public void setClearFilters(boolean clearFilters) {
		this.clearFilters = clearFilters;
	}

	public boolean isEmptyResults() {
		return emptyResults;
	}

	public void setEmptyResults(boolean emptyResults) {
		this.emptyResults = emptyResults;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public List<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}

	public List<Organ> getOrganS() {
		return organS;
	}

	public void setOrganS(List<Organ> organS) {
		this.organS = organS;
	}
	
	
}
