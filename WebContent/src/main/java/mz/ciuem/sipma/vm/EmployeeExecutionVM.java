package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.AllocationEmployee;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.EmployeeExecution;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.service.AllocationEmployeeService;
import mz.ciuem.sipma.service.EmployeeExecutionService;

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
import org.zkoss.zul.Radiogroup;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeExecutionVM extends AbstractVM {

	private EmployeeExecution employeeExecution;

	private List<EmployeeExecution> employeeExecutions;

	@WireVariable
	private EmployeeExecutionService employeeExecutionService;

	@WireVariable
	private AllocationEmployeeService allocationEmployeeService;

	private List<AllocationEmployee> allocations;

	private Employee loggedEmployee;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass=true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		employeeExecution = new EmployeeExecution();

		loggedEmployee = loggedEmployee();

		allocations = allocationEmployeeService
				.filterByEmployee(loggedEmployee);

		employeeExecutions = employeeExecutionService
				.filterByAllocation(employeeExecution.getAllocationEmployee());
	}

	@NotifyChange({ "employeeExecution", "employeeExecutions", "filesList" })
	@Command
	public void save() {

		employeeExecution.getAttachments().addAll(filesList);
		employeeExecutionService.create(employeeExecution);

		log("Executou nova tarrefa: " + employeeExecution.getTaskAlso());
		Clients.showNotification("Execução feita com sucesso!", "info",
				ui, "before_center", -1);

		employeeExecution = new EmployeeExecution();
		filesList.clear();

		employeeExecutions = employeeExecutionService
				.filterByAllocation(employeeExecution.getAllocationEmployee());

		refreshDataTable(null);
		ui.getFellow("tbl").invalidate();
		Radiogroup rg = (Radiogroup) ui.getFellow("rgChosed");
		rg.setSelectedItem(null);
	}

	@NotifyChange({ "employeeExecution", "employeeExecutions" })
	@Command
	public void chooseEmpAllocation(@BindingParam("allocId") Long allocId) {

		employeeExecution.setAllocationEmployee(allocationEmployeeService
				.find(allocId));

		employeeExecutions = employeeExecutionService
				.filterByAllocation(employeeExecution.getAllocationEmployee());

		ui.getFellow("tbl").invalidate();
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public EmployeeExecution getEmployeeExecution() {
		return employeeExecution;
	}

	public void setEmployeeExecution(EmployeeExecution employeeExecution) {
		this.employeeExecution = employeeExecution;
	}

	public List<EmployeeExecution> getEmployeeExecutions() {
		return employeeExecutions;
	}

	public void setEmployeeExecutions(List<EmployeeExecution> employeeExecutions) {
		this.employeeExecutions = employeeExecutions;
	}

	public List<AllocationEmployee> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationEmployee> allocations) {
		this.allocations = allocations;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}


}
