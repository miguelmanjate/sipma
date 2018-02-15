package mz.ciuem.sipma.vm;

import java.io.IOException;

import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.service.ProjectInvestimentService;

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
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProjectInvestimentVM extends AbstractVM {

	private ProjectInvestment projectInvestment;

	private java.util.List<ProjectInvestment> projectInvestments;

	@Wire
	private Div projectList;

	@WireVariable
	private ProjectInvestimentService projectInvestimentService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init() {
		projectInvestment = new ProjectInvestment();
		projectInvestments = projectInvestimentService.getAll();
	}

	@NotifyChange({ "projectInvestment", "projectInvestments" })
	@Command
	public void save() {

		projectInvestimentService.create(projectInvestment);

		log("Registou novo Projecto de Investimento: "
				+ projectInvestment.getName());
		Clients.showNotification(projectInvestment.getName()
				+ " registado com sucesso!", "info", projectList,
				"before_center", -1);

		setProjectInvestments(projectInvestimentService.getAll());
		projectInvestment = new ProjectInvestment();
		
		refreshDataTable(null);
	}
	
	@NotifyChange("projectInvestment")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setProjectInvestment(projectInvestimentService.find(id));
		editButtons();
	}

	@NotifyChange({ "projectInvestment", "projectInvestments" })
	@Command
	public void update() {

		projectInvestimentService.update(projectInvestment);

		cancelButtons();

		log("Actualizou Projecto de Investimento: " + projectInvestment.getName());
		Clients.showNotification(projectInvestment.getName()
				+ " actualizado com sucesso!", "info", projectList,
				"before_center", -1);

		projectInvestments = projectInvestimentService.getAll();

		projectInvestment = new ProjectInvestment();
		
		refreshDataTable(null);
	}
	
	

	public ProjectInvestment getProjectInvestment() {
		return projectInvestment;
	}

	public void setProjectInvestment(ProjectInvestment projectInvestment) {
		this.projectInvestment = projectInvestment;
	}

	public java.util.List<ProjectInvestment> getProjectInvestments() {
		return projectInvestments;
	}

	public void setProjectInvestments(
			java.util.List<ProjectInvestment> projectInvestments) {
		this.projectInvestments = projectInvestments;
	}
}
