package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;

import com.mashape.unirest.http.exceptions.UnirestException;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.service.AllocationOrganService;
import mz.ciuem.sipma.service.AllocationSectorService;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.util.SendSMS;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AllocationSectorVM extends AbstractVM {

	private AllocationSector allocationSector;

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private AllocationSectorService allocationSectorService;

	@WireVariable
	private AllocationOrganService allocationOrganService;

	private List<Sector> sectors;

	private Div allocationSectorList;

	private List<AllocationSector> allocationSectors;

	private List<AllocationOrgan> allocationOrgans;

	private AllocationOrgan selectedAllocation;

	private List<Employee> responsibles;

	private Component ui;

	private String term;

	@WireVariable
	private EmployeeService employeeService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		sectors = sectorService.filterByOrgan(loggedOrgan);

		allocationSectors = allocationSectorService.filterByOrgan(loggedOrgan);

		allocationSector = new AllocationSector();

		allocationOrgans = allocationOrganService.filterIntegratedByOrgan(loggedOrgan);
	}

	@SuppressWarnings("unused")
	@NotifyChange({ "allocationSectors", "allocationSector" })
	@Command
	public void saveAllocationSector() throws UnirestException {
		Calendar c = Calendar.getInstance();
		allocationSectorService.create(allocationSector);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		SendSMS.sms(allocationSector.getResponsible().getCellPhone(), "Tem uma actividade a realizar ate "
				+ sdf.format(allocationSector.getEndDate()) + "");

		log("Alocou a actividade: " + allocationSector.getTask() + " ao sector: "
				+ allocationSector.getSector().getDesignation());
		Clients.showNotification("Actividade alocada ao sector " + allocationSector.getSector() + " com sucesso!",
				"info", ui, "before_center", -1);

		setAllocationSector(new AllocationSector());

		allocationSectors = allocationSectorService.filterByOrgan(loggedOrgan);

		refreshDataTable(null);

	}

	@NotifyChange("responsibles")
	@Command
	public void searchResponsibles(@BindingParam("selectedSector") Sector sector) {

		responsibles = employeeService.filterBySector(sector);
	}

	@NotifyChange("term")
	@Command
	public void defineDateRange(@BindingParam("alloc") AllocationOrgan alloc) {

		ui.getFellow("term").setVisible(true);

		term = getSdf().format(alloc.getEndDate());

		selectedAllocation = alloc;

		Datebox begin = (Datebox) ui.getFellow("dbxBegin");
		begin.setRawValue(null);
		begin.setConstraint(
				"between " + format(new Date()) + " and " + format(selectedAllocation.getEndDate()) + ",no empty");

		Datebox end = (Datebox) ui.getFellow("dbxEnd");
		end.setRawValue(null);
		end.setDisabled(true);
	}

	@Command
	public void defineEndDate(@BindingParam("begin") Date begin) {

		Datebox end = (Datebox) ui.getFellow("dbxEnd");
		end.setRawValue(null);
		end.setDisabled(false);

		end.setConstraint("between " + format(begin) + " and " + format(selectedAllocation.getEndDate()) + ",no empty");
	}

	public SectorService getSectorService() {
		return sectorService;
	}

	public void setSectorService(SectorService sectorService) {
		this.sectorService = sectorService;
	}

	public AllocationSectorService getAllocationSectorService() {
		return allocationSectorService;
	}

	public void setAllocationSectorService(AllocationSectorService allocationSectorService) {
		this.allocationSectorService = allocationSectorService;
	}

	public Div getAllocationSectorList() {
		return allocationSectorList;
	}

	public void setAllocationSectorList(Div allocationSectorList) {
		this.allocationSectorList = allocationSectorList;
	}

	public List<AllocationSector> getAllocationSectors() {
		return allocationSectors;
	}

	public List<Employee> getResponsibles() {
		return responsibles;
	}

	public void setResponsibles(List<Employee> responsibles) {
		this.responsibles = responsibles;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public AllocationSector getAllocationSector() {
		return allocationSector;
	}

	public void setAllocationSector(AllocationSector allocationSector) {
		this.allocationSector = allocationSector;
	}

	public void setAllocationSectors(List<AllocationSector> allocationSectors) {
		this.allocationSectors = allocationSectors;
	}

	public List<AllocationOrgan> getAllocationOrgans() {
		return allocationOrgans;
	}

	public void setAllocationOrgans(List<AllocationOrgan> allocationOrgans) {
		this.allocationOrgans = allocationOrgans;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

}