package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
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
import org.zkoss.zul.Textbox;

import mz.ciuem.sipma.entity.Email;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.service.EmailService;
import mz.ciuem.sipma.service.EmployeeService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmailVM extends AbstractVM {

	private Email email;

	private List<Email> emails;

	private List<Employee> employees;

//	@WireVariable
//	private EmailService emailService;

	@WireVariable
	private EmployeeService employeeService;

	@Wire
	private Textbox txtUser;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		email = new Email();

//		emails = emailService.getAll();

		employees = employeeService.getAll();

	}

	@NotifyChange({ "email", "emails" })
	@Command
	public void save() {

	}

	@Command
	public void clean() {
		txtUser.setText("");
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
