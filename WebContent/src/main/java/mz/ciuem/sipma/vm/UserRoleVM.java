package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.Permission;
import mz.ciuem.sipma.entity.UserRole;
import mz.ciuem.sipma.interfaces.Entity;
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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserRoleVM extends AbstractVM {

	private UserRole userRole;

	private List<UserRole> userRoles;

	private List<Permission> permissions;

	private List<Permission> missedPermissions;

	@WireVariable
	private UserRoleService userRoleService;

	@WireVariable
	private PermissionService permissionService;

	@Wire
	private DualListbox selectedPermissions;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		UserRole found = (UserRole) Executions.getCurrent().getArg()
				.get("userRole");

		userRoles = userRoleService.getAll();

		permissions = permissionService.getAll();

		missedPermissions = new ArrayList<Permission>();

		if (found == null) {

			userRole = new UserRole();
		}

		else {

			this.userRole = found;
			missedPermissions();
		}
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "userRole", "userRoles", "selectedPermissions" })
	@Command
	public void save() {

		userRole.getPermissions().addAll(
				(Collection<? extends Permission>) selectedPermissions
						.getChosenDataList());
		userRoleService.create(userRole);

		log("Registou novo Perfil de Utilizadores: " + userRole.getRolename());
		Clients.showNotification("Peril registado com sucesso!", "info", ui,
				"before_center", -1);

		userRoles = userRoleService.getAll();
		userRole = new UserRole();
		selectedPermissions.unchooseAll();

		refreshDataTable(null);

	}

	@NotifyChange({ "userRole", "missedPermissions" })
	@Command
	public void showRole(@BindingParam("roleId") Long roleId,
			@BindingParam("tgt") Div target) {

		this.userRole = userRoleService.find(roleId);

		this.selectedPermissions.getChosenDataList()
				.addAll(missedPermissions());

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("userRole", userRole);
		map.put("missedPermissions", missedPermissions);
		target.getChildren().clear();
		Executions.createComponents("views/admin/user_role/showRole.zul",
				target, map);

	}

	private List<Permission> missedPermissions() {

		missedPermissions.clear();
		missedPermissions.addAll(permissionService.getAll());

		for (Permission perm : userRole.getPermissions()) {

			missedPermissions.remove(perm);
		}

		return missedPermissions;
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "selectedPermissions", "userRole", "missedPermissions" })
	@Command
	public void addNewPermissions() {

		if (selectedPermissions.getChosenDataList().isEmpty()) {
			Clients.showNotification("Selecione uma ou mais permissões!",
					"warning", selectedPermissions, "before_center", -1);
			
		} else {
			
			userRole.getPermissions().addAll(
					(Collection<? extends Permission>) selectedPermissions
							.getChosenDataList());
			userRoleService.update(userRole);

			for (Entity per : selectedPermissions.getChosenDataList()) {
				missedPermissions.remove((Permission) per);
			}

			selectedPermissions.unchooseAll();

			this.userRole = userRoleService.find(userRole.getId());
			log("Adicionou novas permissões ao perfil: "
					+ userRole.getRolename());
			Clients.showNotification("Permissões adicionados com sucesso!",
					"info", ui, "before_center", -1);
		}
		
		refreshDataTable(null);
	}

	@NotifyChange({ "userRole", "missedPermissions" })
	@Command
	public void removePermission(@BindingParam("permId") Long permId) {

		Permission found = permissionService.find(permId);

		this.userRole.getPermissions().remove(found);

		missedPermissions.add(found);

		userRoleService.update(userRole);

		log("Removeu permissão do perfil: " + userRole.getRolename());
		Clients.showNotification("Permissão removido com sucesso!", "info", ui,
				"before_center", -1);

		this.userRole = userRoleService.find(userRole.getId());
		ui.getFellow("tblPerms").invalidate();
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Permission> getMissedPermissions() {
		return missedPermissions();
	}

	public void setMissedPermissions(List<Permission> missedPermissions) {
		this.missedPermissions = missedPermissions;
	}
}
