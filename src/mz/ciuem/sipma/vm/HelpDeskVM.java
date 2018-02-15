package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.Help;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.HelpService;
import mz.ciuem.sipma.util.FileManager;

import org.zkoss.bind.BindContext;
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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class HelpDeskVM extends AbstractVM {

	private Help problem;

	private String fileName;

	private List<Help> problems;

	@WireVariable
	private HelpService helpService;

	private Set<User> users;

	private User selectedUser;

	private Component ui;

	private SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a dd MMM yyyy");

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		users = new HashSet<User>();
		problem = new Help();

		problems = helpService.getAll();

		for (Help prob : problems) {
			users.add(prob.getWho());
		}

		if (!problems.isEmpty()) {
			selectedUser = problems.get(0).getWho();

			problems = helpService.findBySender(selectedUser);
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void submitHelp() {

		problem.setWho(loggeduser);
		problem.setSupport(userService.getUser("admin@admin.com"));
		helpService.create(problem);

		log("Solicitou Ajuda: " + problem.getWhat());
		Clients.showNotification(
				"Obrigado por submeter ajuda..! Aguarde um feedback brevemente!",
				"info", ui, "before_center", -1);

		problem = new Help();
		fileName = "";
	}

	@NotifyChange("fileName")
	@Command
	public void doUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {

		setFile(new File());
		FileManager fileManager = new FileManager();
		setFile(fileManager.uploadImage(ctx));

		problem.setAttach(file);

		setFileName(file.getFileName());
	}

	@NotifyChange({ "*" })
	@Command
	public void showUserProblems(@BindingParam("userId") Long userId) {

		selectedUser = userService.find(userId);

		problems = helpService.findBySender(selectedUser);
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public Help getProblem() {
		return problem;
	}

	public void setProblem(Help problem) {
		this.problem = problem;
	}

	public List<Help> getProblems() {
		return problems;
	}

	public void setProblems(List<Help> problems) {
		this.problems = problems;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public SimpleDateFormat getF() {
		return f;
	}

	public void setF(SimpleDateFormat f) {
		this.f = f;
	}
}
