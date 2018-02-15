package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.OrganExecution;
import mz.ciuem.sipma.entity.SectorExecution;
import mz.ciuem.sipma.service.AllocationOrganService;
import mz.ciuem.sipma.service.OrganExecutionService;
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
public class OrganExecutionVM extends AbstractVM {

	private List<OrganExecution> organExecutions;

	private List<SectorExecution> sectorExecutions;

	private OrganExecution organExecution;

	@WireVariable
	private OrganExecutionService organExecutionService;

	@WireVariable
	private SectorExecutionService sectorExecutionService;

	@WireVariable
	private AllocationOrganService allocationOrganService;

	private List<AllocationOrgan> allocations;

	private AllocationOrgan allocation;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.setUi(view);

		organExecution = new OrganExecution();

		allocations = allocationOrganService.filterByOrgan(loggedOrgan);

		organExecutions = organExecutionService
				.filterByAllocation(organExecution.getAllocationOrgan());

		allocation = (AllocationOrgan) Executions.getCurrent().getArg()
				.get("allocation");

		if (allocation != null) {
			organExecutions = organExecutionService
					.filterByAllocation(allocation);
		}
	}

	@NotifyChange({ "organExecution", "organExecutions", "filesList" })
	@Command
	public void reportExecution() {

		organExecution.getAttachments().addAll(filesList);
		organExecution.setAllocationOrgan(allocation);
		organExecutionService.create(organExecution);

		log("Reportou nova execução: " + organExecution.getTaskAlso());
		Clients.showNotification("Execução reportada com sucesso!", "info", ui,
				"before_center", -1);

		organExecution = new OrganExecution();
		filesList.clear();

		organExecutions = organExecutionService.filterByAllocation(allocation);
		ui.getFellow("tbl").invalidate();
	}

	@NotifyChange({ "organExecution", "sectorExecutions" })
	@Command
	public void chooseOrganAllocation(@BindingParam("allocId") Long allocId) {

		organExecution.setAllocationOrgan(allocationOrganService.find(allocId));

		sectorExecutions = sectorExecutionService
				.filterByAllocationOrgan(allocationOrganService.find(allocId));

		ui.getFellow("tbl").invalidate();
	}

	@NotifyChange("allocation")
	@Command
	public void reportPage(@BindingParam("alloc") Long allocId,
			@BindingParam("tgt") Div target, @BindingParam("bc") Div breadcrumb) {

		allocation = allocationOrganService.find(allocId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("allocation", allocation);
		target.getChildren().clear();
		Executions
				.createComponents(
						"views/allocation_activity/execution/pagOrganExecutionReportModal.zul",
						target, map);

		ArrayList<String> links = new ArrayList<String>();
		links.add("Execução do Orgão");
		links.add(allocation.getTaskAlso());
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	public List<OrganExecution> getOrganExecutions() {
		return organExecutions;
	}

	public void setOrganExecutions(List<OrganExecution> organExecutions) {
		this.organExecutions = organExecutions;
	}

	public OrganExecution getOrganExecution() {
		return organExecution;
	}

	public void setOrganExecution(OrganExecution organExecution) {
		this.organExecution = organExecution;
	}

	public OrganExecutionService getOrganExecutionService() {
		return organExecutionService;
	}

	public void setOrganExecutionService(
			OrganExecutionService organExecutionService) {
		this.organExecutionService = organExecutionService;
	}

	public AllocationOrganService getAllocationOrganService() {
		return allocationOrganService;
	}

	public void setAllocationOrganService(
			AllocationOrganService allocationOrganService) {
		this.allocationOrganService = allocationOrganService;
	}

	public List<AllocationOrgan> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOrgan> allocations) {
		this.allocations = allocations;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}

	public Component getUi() {
		return ui;
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

	public AllocationOrgan getAllocation() {
		return allocation;
	}

	public void setAllocation(AllocationOrgan allocation) {
		this.allocation = allocation;
	}

}
