package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Role;
import mz.ciuem.sipma.service.RoleService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RoleVM extends AbstractVM {

	private Role role;

	private List<Role> roles;

	@WireVariable
	private RoleService roleService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

//		role = new Role();

		roles = roleService.getAll();
	}

//	@NotifyChange({ "role", "roles" })
//	@Command
//	public void save() {
//
//		roleService.create(role);
//
//		Clients.showNotification("Papel #" + role.getId()
//				+ " registado com sucesso!", "info", ui, "before_center", -1);
//
//		roles = roleService.getAll();
//		role = new Role();
//
//		refreshDataTable(null);
//	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
