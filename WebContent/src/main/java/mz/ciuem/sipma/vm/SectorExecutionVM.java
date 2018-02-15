package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.AllocationSector;
import mz.ciuem.sipma.entity.EmployeeExecution;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.SectorExecution;
import mz.ciuem.sipma.service.AllocationEmployeeService;
import mz.ciuem.sipma.service.AllocationSectorService;
import mz.ciuem.sipma.service.EmployeeExecutionService;
import mz.ciuem.sipma.service.SectorExecutionService;
import mz.ciuem.sipma.util.Breadcrumb;

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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SectorExecutionVM extends AbstractVM {

	private List<SectorExecution> sectorExecutions;

	private SectorExecution sectorExecution;

	@WireVariable
	private SectorExecutionService sectorExecutionService;

	@WireVariable
	private AllocationSectorService allocationSectorService;

	@WireVariable
	private AllocationEmployeeService allocationEmployeeService;

	@WireVariable
	private EmployeeExecutionService employeeExecutionService;

	private List<AllocationSector> allocations;

	private AllocationSector allocation;

	private List<EmployeeExecution> empExecs;

	private List<AllocationEmployee> empAllocs;

	private AllocationEmployee empAlloc;

	private Long rate;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.setUi(view);

		sectorExecution = new SectorExecution();

		allocations = allocationSectorService.filterBySector(loggedSector);

		allocation = (AllocationSector) Executions.getCurrent().getArg()
				.get("allocation");

		if (allocation != null) {
			sectorExecutions = sectorExecutionService
					.filterByAllocation(allocation);
		}
		for (int i = 0; i < allocations.size(); i++) {

			empAllocs = allocationEmployeeService
					.filterByAllocation(allocations.get(i).getResponsible());

			for (int j = 0; j < empAllocs.size(); j++) {

				empAlloc = allocationEmployeeService.find(empAllocs.get(j)
						.getId());

				rate = allocationEmployeeService
						.filterByEmployeeExecution(empAlloc);

				System.err.println(empAllocs.get(j).getId() + "....."
						+ empAllocs.get(j).getTask() + rate);
			}

		}

	}

	@NotifyChange({ "sectorExecution", "sectorExecutions", "filesList" })
	@Command
	public void reportExecution() {

		sectorExecution.getAttachments().addAll(filesList);
		sectorExecution.setAllocationSector(allocation);

		sectorExecutionService.create(sectorExecution);

		log("Reportou nova execução: " + sectorExecution.getTaskAlso());
		Clients.showNotification("Execução reportada com sucesso!", "info", ui,
				"before_center", -1);

		sectorExecution = new SectorExecution();
		filesList.clear();

		sectorExecutions = sectorExecutionService
				.filterByAllocation(allocation);
		ui.getFellow("tbl").invalidate();

	}

	@NotifyChange({ "empAllocs", "empExecs" })
	@Command
	public void chooseSectorAllocation(@BindingParam("allocId") Long allocId) {

		allocation = allocationSectorService.find(allocId);

		if (empExecs != null) {
			empExecs.clear();
		}

		empAllocs = allocationEmployeeService
				.filterBySectorAllocation(allocation);

		ui.getFellow("tbl").invalidate();
	}

	@NotifyChange({ "empExecs" })
	@Command
	public void findEmpExecutions(@BindingParam("allocId") Long allocId) {

		empAlloc = allocationEmployeeService.find(allocId);

		empExecs = employeeExecutionService.filterByAllocation(empAlloc);

		ui.getFellow("master").invalidate();
	}

	@NotifyChange("allocation")
	@Command
	public void reportPage(@BindingParam("alloc") Long allocId,
			@BindingParam("tgt") Div target, @BindingParam("bc") Div breadcrumb) {

		allocation = allocationSectorService.find(allocId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("allocation", allocation);
		target.getChildren().clear();
		Executions
				.createComponents(
						"views/allocation_activity/execution/pagSectorChiefExecutionReportModal.zul",
						target, map);

		ArrayList<String> links = new ArrayList<String>();
		links.add("Chefe do Sector");
		links.add(allocation.getTask());
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	@NotifyChange("allocation")
	@Command
	public void viewSectorAllocation(@BindingParam("alloc") Long id) {
		allocation = allocationSectorService.find(id);
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public List<SectorExecution> getSectorExecutions() {
		return sectorExecutions;
	}

	public void setSectorExecutions(List<SectorExecution> sectorExecutions) {
		this.sectorExecutions = sectorExecutions;
	}

	public SectorExecutionService getSectorExecutionService() {
		return sectorExecutionService;
	}

	public void setSectorExecutionService(
			SectorExecutionService sectorExecutionService) {
		this.sectorExecutionService = sectorExecutionService;
	}

	public AllocationSectorService getAllocationSectorService() {
		return allocationSectorService;
	}

	public void setAllocationSectorService(
			AllocationSectorService allocationSectorService) {
		this.allocationSectorService = allocationSectorService;
	}

	public List<AllocationSector> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationSector> allocations) {
		this.allocations = allocations;
	}

	public SectorExecution getSectorExecution() {
		return sectorExecution;
	}

	public void setSectorExecution(SectorExecution sectorExecution) {
		this.sectorExecution = sectorExecution;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}

	public AllocationSector getAllocation() {
		return allocation;
	}

	public void setAllocation(AllocationSector allocation) {
		this.allocation = allocation;
	}

	public List<EmployeeExecution> getEmpExecs() {
		return empExecs;
	}

	public void setEmpExecs(List<EmployeeExecution> empExecs) {
		this.empExecs = empExecs;
	}

	public List<AllocationEmployee> getEmpAllocs() {
		return empAllocs;
	}

	public void setEmpAllocs(List<AllocationEmployee> empAllocs) {
		this.empAllocs = empAllocs;
	}

	public AllocationEmployee getEmpAlloc() {
		return empAlloc;
	}

	public void setEmpAlloc(AllocationEmployee empAlloc) {
		this.empAlloc = empAlloc;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

}
