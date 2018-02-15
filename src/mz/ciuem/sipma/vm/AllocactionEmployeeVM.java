package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;
import mz.ciuem.sipma.service.AllocationEmployeeService;
import mz.ciuem.sipma.service.AllocationSectorService;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.SubSectorService;
import mz.ciuem.sipma.util.SendSMS;

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

import com.mashape.unirest.http.exceptions.UnirestException;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AllocactionEmployeeVM extends AbstractVM {

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private SubSectorService subSectorService;

	@WireVariable
	private AllocationEmployeeService allocationEmployeeService;

	@WireVariable
	private AllocationSectorService allocationSectorService;

	private List<AllocationSector> allocationSectors;

	private List<AllocationEmployee> allocationEmployees;

	private List<Employee> responsibles;

	private List<Sector> sectors;

	private List<SubSector> subSectors;

	private Component ui;

	private String term;

	private AllocationEmployee allocationEmployee;

	@WireVariable
	private EmployeeService employeeService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		allocationEmployee = new AllocationEmployee();

		allocationSectors = allocationSectorService.filterIntegratedBySector(loggedSector);

		subSectors = subSectorService.getSubSectorsBySector(loggedSector);

		responsibles = employeeService.filterBySector(loggedSector);

		allocationEmployees = allocationEmployeeService.filterBySector(loggedSector);

	}

	@NotifyChange("*")
	@Command
	public void saveAllocationEmployee() throws UnirestException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		allocationEmployeeService.create(allocationEmployee);

		SendSMS.sms(allocationEmployee.getResponsible().getCellPhone(), "Tem uma actividade a realizar ate "
				+ sdf.format(allocationEmployee.getEndDate()) + ".");

		log("Alocou a actividade: " + allocationEmployee.getTask() + " ao funcionario: "
				+ allocationEmployee.getResponsible().fullName());
		Clients.showNotification(
				"Actividade alocada ao funcionario " + allocationEmployee.getResponsible().fullName() + " com sucesso!",
				"info", ui, "before_center", -1);

		SendSMS.sms(allocationEmployee.getResponsible().getCellPhone(),
				"Tem uma actividade a realizar ate " + allocationEmployee.getEndDate() + ".");

		allocationEmployee = new AllocationEmployee();

		allocationEmployees = allocationEmployeeService.filterBySector(loggedSector);

		refreshDataTable(null);

	}

	@NotifyChange("responsibles")
	@Command
	public void searchResponsibles(@BindingParam("selectedSubSector") Sector sector) {

		responsibles = employeeService.filterBySector(sector);

	}

	@NotifyChange("term")
	@Command
	public void defineDateRange(@BindingParam("alloc") AllocationSector alloc) {

		ui.getFellow("term").setVisible(true);

		term = getSdf().format(alloc.getEndDate());
	}

	public List<AllocationEmployee> getAllocationEmployees() {
		return allocationEmployees;
	}

	public void setAllocationEmployees(List<AllocationEmployee> allocationEmployees) {
		this.allocationEmployees = allocationEmployees;
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public AllocationEmployee getAllocationEmployee() {
		return allocationEmployee;
	}

	public void setAllocationEmployee(AllocationEmployee allocationEmployee) {
		this.allocationEmployee = allocationEmployee;
	}

	public AllocationSectorService getAllocationSectorService() {
		return allocationSectorService;
	}

	public void setAllocationSectorService(AllocationSectorService allocationSectorService) {
		this.allocationSectorService = allocationSectorService;
	}

	public List<AllocationSector> getAllocationSectors() {
		return allocationSectors;
	}

	public void setAllocationSectors(List<AllocationSector> allocationSectors) {
		this.allocationSectors = allocationSectors;
	}

	public List<SubSector> getSubSectors() {
		return subSectors;
	}

	public void setSubSectors(List<SubSector> subSectors) {
		this.subSectors = subSectors;
	}

	public List<Employee> getResponsibles() {
		return responsibles;
	}

	public void setResponsibles(List<Employee> responsibles) {
		this.responsibles = responsibles;
	}

}
