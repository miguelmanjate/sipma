package mz.ciuem.sipma;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Permission;
import mz.ciuem.sipma.entity.Profession;
import mz.ciuem.sipma.entity.Role;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.entity.UserRole;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.PermissionService;
import mz.ciuem.sipma.service.ProfessionService;
import mz.ciuem.sipma.service.RoleService;
import mz.ciuem.sipma.service.UserRoleService;
import mz.ciuem.sipma.service.UserService;
import mz.ciuem.sipma.util.PermissionsUtil;
import mz.ciuem.sipma.util.UserRolesUtil;

@Repository("starter")
public class AppStartup implements InitializingBean {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProfessionService professionService;

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@PostConstruct
	public void start() {
		initRoles();
		initPermissions();
		initUserRoles();
		initAdminUser();
	}

	@Transactional
	protected void initRoles() {

		if (roleService.count() == 0) {
			roleService.create(new Role("Principal"));
			roleService.create(new Role("Secundario"));
		}
	}

	@Transactional
	protected void initPermissions() {

		if (PermissionsUtil.values().length > permissionService.count()) {
			for (PermissionsUtil permUtil : PermissionsUtil.values()) {
				Permission found = permissionService.getPermission(permUtil
						.name());
				if (found == null) {
					Permission perm = new Permission();
					perm.setPermissionname(permUtil.name());
					perm.setDescription(permUtil.getValue());
					permissionService.create(perm);
				} else
					continue;
			}
		}
	}

	@Transactional
	protected void initAdminUser() {

		if (userService.count() == 0) {

			Profession profession = new Profession();

			profession.setName("Adminstrador de Sistemas");
			profession.setDescription("Administrador de Sistemas de Software");
			profession.setUpdated(Calendar.getInstance().getTime());
			Profession prof = professionService.create(profession);

			UserRole userRole = new UserRole();
			userRole.setRolename("Admin");
			userRole.setType("Normal");
			userRole.getPermissions().addAll(permissionService.getAll());
			userRole = userRoleService.create(userRole);

			Employee admin = new Employee();
			admin.setGender("Male");
			admin.setProfession(prof);
			admin.setName("Admin");
			admin.setLastName("Admin");
			admin.setAcademicLevel("High Level");
			admin.setAddress("CIUEM");
			admin = employeeService.create(admin);

			User user = new User();
			user.setResponsible(admin);
			user.getRoles().add(userRole);
			user.setUsername("admin@admin.com");
			user.setPassword("admin");
			userService.create(user);
		}
	}

	@Transactional
	protected void initUserRoles() {

		if (UserRolesUtil.values().length > userRoleService.count()) {
			for (UserRolesUtil roleUtil : UserRolesUtil.values()) {
				UserRole found = userRoleService.findByName(roleUtil.name());
				if (found == null) {
					UserRole ur = new UserRole();
					ur.setRolename(roleUtil.name());
					ur.setType("Delegated");
					userRoleService.create(ur);
				} else
					continue;
			}
		}
	}

}
