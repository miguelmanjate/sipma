package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.Delegation;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Permission;
import mz.ciuem.sipma.service.DelegationService;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.PermissionService;
import mz.ciuem.sipma.service.UserRoleService;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DelegationVM extends AbstractVM {

	private Set<Employee> employees;

	private List<Permission> permissions;
	private List<Delegation> delegations;

	@WireVariable
	private DelegationService delegationService;
	
	@WireVariable
	private UserRoleService userRoleService;
	
	@WireVariable
	private EmployeeService employeeService;
	
	@WireVariable
	private PermissionService permissionService;

	@Wire
	private DualListbox selectedPermissions;

	private Delegation delegation;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		delegation = new Delegation();
		delegations = delegationService.filterByResponsible(loggeduser
				.getResponsible());

		employees = new HashSet<Employee>();
		permissions = new ArrayList<Permission>();

		if (loggedOrgan != null) {
			if (loggeduser.getRoles().contains(
					userRoleService.findByName("SectorChief"))) {
				employees.clear();
				employees.addAll(employeeService
						.allWhereUserIdIsNotNull(loggedSector));
			}
			if (loggeduser.getRoles().contains(
					userRoleService.findByName("OrganChief"))) {
				employees.clear();
				employees.addAll(employeeService
						.allWhereUserIdIsNotNull(loggedOrgan));
			}
		}
		if (loggeduser.getPermissions().contains(
				permissionService.getPermission("PLANNING_OFFICE"))) {
			employees.clear();
			employees.addAll(employeeService.allWhereUserIdIsNotNull());
		}
	}

	@NotifyChange("permissions")
	@Command
	public void correctPermissions(@BindingParam("emp") Employee emp) {

		permissions.clear();
		permissions.addAll(loggeduser.getPermissions());
		
		if (loggeduser.getPermissions().contains(
				permissionService.getPermission("PLANNING_OFFICE"))) {
			permissions.clear();
			permissions.addAll(permissionService.getAll());
		}

		Set<Permission> empPermissions = emp.getUserLogin().getPermissions();
		this.permissions.removeAll(empPermissions);
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "delegation", "delegations" })
	@Command
	public void delegate() {

		if (selectedPermissions.getChosenDataList().isEmpty()) {
			Clients.showNotification("Selecione pelomenos uma permissão a delegar!", "warning",
					selectedPermissions, "before_center", -1);
		} else {
			
			delegation.getPermissions().addAll((Collection<? extends Permission>) selectedPermissions.getChosenDataList());

			delegation.setResponsible(loggeduser.getResponsible());
			delegationService.create(delegation);

			log("Delegou ao funcionário: "
					+ delegation.getEmployee().fullName()
					+ " novas permissões: " + delegation);
			
			delegation = new Delegation();
			delegations = delegationService.getAll();
			Clients.showNotification("Delegação feita com sucesso!", "info",
					ui, "before_center", -1);
			selectedPermissions.unchooseAll();
		}
		
		refreshDataTable(null);
	}

	@NotifyChange({ "delegations" })
	@Command
	public void removeDelegation(@BindingParam("delegationId") Long delegationId) {

		delegationService.delete(delegationId);

		delegations = delegationService.getAll();

		Clients.showNotification("Delegação removida com sucesso!", "info", ui,
				"before_center", -1);
		refreshDataTable(null);
	}

	@Command
	public void showPermInfo(@BindingParam("showPermInfo") Permission perm) {

		Clients.showNotification(perm.getDescription(), "info",
				selectedPermissions, "before_center", -1);
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Delegation getDelegation() {
		return delegation;
	}

	public void setDelegation(Delegation delegation) {
		this.delegation = delegation;
	}

	public List<Delegation> getDelegations() {
		return delegations;
	}

	public void setDelegations(List<Delegation> delegations) {
		this.delegations = delegations;
	}
}
