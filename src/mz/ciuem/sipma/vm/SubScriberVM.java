package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubScriber;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.SubScriberService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

public class SubScriberVM extends AbstractVM {

	private SubScriber subScriber;

	private List<SubScriber> subScribers;

	private Employee employee;

	private List<Employee> employees;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private SubScriberService subScriberService;

	private List<Sector> sectors;

	@WireVariable
	private SectorService sectorService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		subScriber = new SubScriber();

		setSubScribers(subScriberService.getAll());

		sectors = sectorService.getAll();

		employee = new Employee();
	}

	@NotifyChange({ "subScriber", "subScriber" })
	@Command
	public void save() {

		subScriberService.create(subScriber);
		log("Registou novo Assinante: " + subScriber.getEmployee().getName());
		Clients.showNotification("Assinante #" + subScriber.getEmployee().getId() + " registado com sucesso!", "info",
				ui, "before_center", -1);

		subScriber = new SubScriber();

		setSubScribers(subScriberService.getAll());
		refreshDataTable(null);
	}

	@NotifyChange("employee")
	@Command
	public void getEmployeeBySector(@BindingParam("sector_id") Long id) {

		Sector sect = sectorService.find(id);

		employees = employeeService.filterBySector(sect);

	}

	@NotifyChange("subScriber")
	@Command
	public void edit(@BindingParam("subScriberID") Long subScriberID) {

		subScriber = subScriberService.find(subScriberID);

		editButtons();

	}

	@NotifyChange({ "subScriber", "subScribers" })
	@Command
	public void update() {

		subScriberService.update(subScriber);

		log("Actualizou os dados do Assinante: " + subScriber.getEmployee().getName());
		Clients.showNotification("Assinante Actualizado com sucesso!", "info", ui, "before_center", -1);

		refreshDataTable(null);

		cancelButtons();

	}

	public List<SubScriber> getSubScribers() {
		return subScribers;
	}

	public void setSubScribers(List<SubScriber> subScribers) {
		this.subScribers = subScribers;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public SubScriber getSubScriber() {
		return subScriber;
	}

	public void setSubScriber(SubScriber subScriber) {
		this.subScriber = subScriber;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
