package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Permission;
import mz.ciuem.sipma.service.PermissionService;

import org.zkoss.bind.annotation.AfterCompose;
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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PermissionVM extends AbstractVM {

	private Permission permission;

	private List<Permission> permissions;

	@WireVariable
	private PermissionService permissionService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		permission = new Permission();

		permissions = permissionService.getAll();
	}

	@NotifyChange({ "permission", "permissions" })
	@Command
	public void save() {

		permissionService.create(permission);

		Clients.showNotification("Permissão #" + permission.getId()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		permissions = permissionService.getAll();
		permission = new Permission();

		refreshDataTable(null);
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
