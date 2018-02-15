package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.entity.UserRole;
import mz.ciuem.sipma.interfaces.Entity;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.UserRoleService;
import mz.ciuem.sipma.service.UserService;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserPFIVM extends AbstractVM {

	private User user;

	private List<User> users;

	private List<Employee> employees;
	
	private List<Organ> organs;

	private List<UserRole> userRoles;

	private List<UserRole> missingUserRoles;
	
	private List<Sector> sectors;
	
	private Organ  org;

	@Wire
	private DualListbox selectedRoles;

	@WireVariable
	private UserService userService;

	@WireVariable
	private EmployeeService employeeService;
	
	@WireVariable
	private SectorService sectorService;
	
	@WireVariable
	private OrganService organService;

	@WireVariable
	private UserRoleService userRoleService;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;
		
		loggeduser = userService.getUser(authentication.getName());

		loggedOrgan = loggeduser.getOrgan();

		loggedSector = sectorService.getSectorByResponsible(loggeduser.getResponsible());

		sectors = sectorService.filterByOrgan(loggeduser.getOrgan());

		User found = (User) Executions.getCurrent().getArg().get("user");

		missingUserRoles = new ArrayList<UserRole>();
		users = userService.getUserByOrgan(loggedOrgan);
		
		//organs = organService.getAll();

		userRoles = userRoleService.getAll();

		if (found == null) {
			user = new User();
		}

		else {
			this.user = found;
			missingRoles();
		}
	}
	
	@NotifyChange({ "organs", "employees" })
	@Command
	public void onSelectOrgan(@BindingParam("sector_id") Long id) {
	    	Sector  sect = sectorService.find(id);
	    	user.setOrgan(sect.getOrgan());
			employees = employeeService.filterBySector(sect);
			refreshDataTable(null);
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "user", "users", "selectedRoles", "employees" })
	@Command
	public void save() {

		if (selectedRoles.getChosenDataList().isEmpty()) {
			Clients.showNotification(
					"Selecione um ou mais perfis para o utilizador", "warning",
					selectedRoles, "before_center", -1);

		}

		else {
			user.getRoles().addAll(
					(Collection<? extends UserRole>) selectedRoles
							.getChosenDataList());
			userService.create(user);
			

			log("Registou novo Utilizador: " + user.getUsername());
			Clients.showNotification("Utilizador registado com sucesso!",
					"info", ui, "before_center", -1);

			userService.getUserByOrgan(loggedOrgan);
			user = new User();

			selectedRoles.unchooseAll();

			if(org!=null){
			employees = employeeService.filterByOrgan(org);
			}
		}

		refreshDataTable(null);
//		  String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());  
//		  Messagebox.show(hashed);

	}

	@NotifyChange({ "user", "missingUserRoles" })
	@Command
	public void showUser(@BindingParam("user") Long userId,
			@BindingParam("tgt") Div target) {

		this.user = userService.find(userId);

		missingRoles();

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("user", user);
		map.put("missingUserRoles", missingUserRoles);
		target.getChildren().clear();
		Executions.createComponents("views/admin/user/show.zul", target, map);

	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "selectedRoles", "user", "missingUserRoles" })
	@Command
	public void addNewRoles(@BindingParam("userId") Long userId) {
		
		this.user = userService.find(userId);

		List<Entity> roles = selectedRoles.getChosenDataList();

		if (roles.isEmpty()) {
			Clients.showNotification("Selecione um ou mais perfis!", "info",
					selectedRoles, "before_center", -1);
		} else {
			user.getRoles().addAll((Collection<? extends UserRole>) roles);
			userService.update(user);

			for (Entity ur : roles) {
				missingUserRoles.remove(ur);
			}

			selectedRoles.unchooseAll();
			log("Adicionou novos perfis ao utilizador: " + user.getUsername());
			Clients.showNotification("Perfis adicionados com sucesso!", "info",
					ui, "before_center", -1);
		}
		ui.getFellow("tblRoles").invalidate();
	}

	@NotifyChange({ "user", "missingUserRoles" })
	@Command
	public void removeRole(@BindingParam("roleId") Long roleId) {

		UserRole found = userRoleService.find(roleId);

		this.user.getRoles().remove(found);

		missingUserRoles.add(found);

		userService.update(user);

		log("Removeu perfil do utilizador: " + user.getUsername());
		Clients.showNotification("Perfil removido com sucesso!", "info", ui,
				"before_center", -1);

		this.user = userService.find(user.getId());
		ui.getFellow("tblRoles").invalidate();
	}

	@NotifyChange("user")
	@Command
	public void setState(@BindingParam("state") final boolean state) {
		
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
            public void onEvent(ClickEvent event) throws Exception {
                if(Messagebox.Button.YES.equals(event.getButton())) {
            		user.setEnabled(state);
            		userService.update(user);
            		
            		log("Mudou o estado da conta de: " + user.getUsername()+ " para: " + i18n(user.isEnabled()));
            		
            		Clients.showNotification("Utilizador esta agora " + i18n(state) + "!", "info", ui, "before_center", -1);
            		BindUtils.postGlobalCommand(null, null, "user", null);
                }
            }

			private String i18n(boolean state) {
				if (state) {
					return "Activo";
				} else {
					return "Inactivo";
				}
			}
        };
        
        Messagebox.show("Tem certeza que deseja esta operação?", "Activar/Desactivar Utilizador", new Messagebox.Button[]{
                Messagebox.Button.YES, Messagebox.Button.NO }, Messagebox.QUESTION, clickListener);
        
        this.user = userService.find(user.getId());
		ui.getFellow("tblRoles").invalidate();
	}

	private List<UserRole> missingRoles() {

		missingUserRoles.clear();

		for (UserRole ur : userRoles) {

			if (!user.getRoles().contains(ur)) {
				missingUserRoles.add(ur);
			}
		}
		return missingUserRoles;
	}

	public Validator getDateValidator() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				Map<String, Property> formData = ctx.getProperties(ctx
						.getProperty().getValue());
				String password = (String) formData.get("password").getValue();
				String confirmPassword = (String) formData.get(
						"confirmPassword").getValue();

				if (password != null && confirmPassword != null
						&& !(password.equals(confirmPassword))) {
					addInvalidMessage(ctx, "confirmPassword",
							"Os campos das senhas devem corresponder!");
				}

			}
		};
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<UserRole> getMissingUserRoles() {
		return missingUserRoles;
	}

	public void setMissingUserRoles(List<UserRole> missingUserRoles) {
		this.missingUserRoles = missingUserRoles;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	
	
	
}
