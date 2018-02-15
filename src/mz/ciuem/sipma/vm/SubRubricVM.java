package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.entity.ProjectRubric;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.service.ProjectInvestimentService;
import mz.ciuem.sipma.service.ProjectRubricService;
import mz.ciuem.sipma.service.RubricService;
import mz.ciuem.sipma.service.SubRubricService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SubRubricVM extends AbstractVM {

	private SubRubric subRubric;

	private java.util.List<SubRubric> subRubrics;
	private java.util.List<Rubric> rubrics;
	List<ProjectInvestment> listProjectInvestiment = new ArrayList<ProjectInvestment>();
	List<ProjectRubric> listProjectRubric = new ArrayList<ProjectRubric>();

	private Component ui;

	@Wire
	private Div projectList;

	@WireVariable
	private RubricService rubricService;
	
	@Wire
	private Div modal;

	@WireVariable
	private SubRubricService subRubricService;
	
	@WireVariable
	private ProjectInvestimentService projectInvestimentService;
	
	@WireVariable
	private ProjectRubricService projectRubricService;
	
	@WireVariable
	private ProjectRubricService ProjectRubricService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);
		listarProjectos();

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view)  {
		this.ui = view;
		subRubric = new SubRubric();

//		rubrics = rubricService.getAll();
		subRubrics = subRubricService.getAll();
		rubrics= rubricService.buscarTodasAsRubricasOrdenadas();
		
	}
	
	@Command
	private void listarProjectos() {
		List<ProjectInvestment> list = projectInvestimentService.getAll();
		ListModelList<ProjectInvestment> l = new ListModelList<ProjectInvestment>(list);
		l.setMultiple(true);
		Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
		lbx_projectos.setModel(l);
	}
	
	@SuppressWarnings("static-access")
	@Command
	public void printActpO() throws JRException{
		if (rubrics.isEmpty()) {

			Clients.showNotification("Informação Vazia", "info", modal,
					"middle_center", 3000);

		} else {

			mz.ciuem.sipma.util.MasterRep mas = new mz.ciuem.sipma.util.MasterRep();
			Map<String, Object> m = new HashMap<String, Object>();
			//Devo preencher a lista com accoes especificas
			//asdas;
			
			//SpecifiedAction  sa = (SpecifiedAction) specifiedActionList.get(1);
			//Action ac = sa.getAction();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/u4.jpg");
			m.put("imagemLogo", inputV);
		//	m.put("orgao", loggedOrgan.getDesignation());
			//m.put("areaPrioritaria", ac.getCriticalArea().getDesignation());
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			m.put("SUBREPORT_DIR", realPath);
			//m.put("ano", ""+ac.getBeginDate().getYear());
			Div modal = (Div) ui.getFellow("modal");
			mas.imprimir("/reports/rubricasFinal.jrxml", rubrics, m, modal);
		

		}

	}

	@NotifyChange({ "subRubric", "subRubrics" })
	@Command
	public void save() {

		subRubricService.create(subRubric);
		
		Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
		Set<Listitem> listI = lbx_projectos.getSelectedItems();
		for(Listitem l : listI){
			ProjectInvestment pi = (ProjectInvestment) l.getValue();
			ProjectRubric pr = new ProjectRubric();
			pr.setProjectInvestment(pi);
			pr.setSubRubric(subRubric);
			pr.setEmUso(true);
			projectRubricService.create(pr);
		}
		lbx_projectos.clearSelection();

		log("Registou uma Nova Rubrica: " + subRubric.getName());
		Clients.showNotification(subRubric.getName()
				+ " registado com sucesso!", "info", projectList,
				"before_center", -1);

		subRubrics = subRubricService.getAll();
		subRubric = new SubRubric();
		refreshDataTable(null);
	}

	@NotifyChange("subRubric")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setSubRubric(subRubricService.find(id));
	    Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
	    Listbox lbx_projectosRubricas = (Listbox) ui.getFellow("lbx_projectosRubricas");
	    lbx_projectos.setVisible(true);
	    lbx_projectosRubricas.setVisible(false);
		editButtons();
	}
	
	@NotifyChange("subRubric")
	@Command
	public void view(@BindingParam("typeId") Long id) {

	    SubRubric sb = subRubricService.find(id);
	    List<ProjectRubric> listPS = projectRubricService.getProjectRubricBySubRubric(sb);
	    ListModelList<ProjectRubric> l = new ListModelList<ProjectRubric>(listPS);
	    
	    Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
	    Listbox lbx_projectosRubricas = (Listbox) ui.getFellow("lbx_projectosRubricas");
	    lbx_projectos.setVisible(false);
	    lbx_projectosRubricas.setVisible(true);
	    lbx_projectosRubricas.setModel(l);
	    editButtons();
	    
	}

	@NotifyChange({ "subRubric", "subRubrics" })
	@Command
	public void update() {

		subRubricService.update(subRubric);
		
		List<ProjectRubric> listPS = projectRubricService.getProjectRubricBySubRubric(subRubric);
		
		for(ProjectRubric p : listPS){
			p.setEmUso(false);
			projectRubricService.update(p);
		}
		
		Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
		Set<Listitem> listI = lbx_projectos.getSelectedItems();
		for(Listitem l : listI){
			ProjectInvestment pi = (ProjectInvestment) l.getValue();
			ProjectRubric pr = new ProjectRubric();
			pr.setProjectInvestment(pi);
			pr.setSubRubric(subRubric);
			pr.setEmUso(true);
			projectRubricService.create(pr);
		}
		lbx_projectos.clearSelection();

		cancelButtons();

		log("Actualizou Rubrica: " + subRubric.getName());
		Clients.showNotification(subRubric.getName()
				+ " actualizado com sucesso!", "info", projectList,
				"before_center", -1);

		subRubrics = subRubricService.getAll();
		subRubric = new SubRubric();
		refreshDataTable(null);
	}
	
	@NotifyChange("subRubric")
	@Command
	public void cancelButton() {
		cancelButtons();
	    Listbox lbx_projectos = (Listbox) ui.getFellow("lbx_projectos");
	    Listbox lbx_projectosRubricas = (Listbox) ui.getFellow("lbx_projectosRubricas");
	    lbx_projectos.setVisible(true);
	    lbx_projectosRubricas.setVisible(false);
	}

	public SubRubric getSubRubric() {
		return subRubric;
	}

	public void setSubRubric(SubRubric subRubric) {
		this.subRubric = subRubric;
	}

	public java.util.List<SubRubric> getSubRubrics() {
		return subRubrics;
	}

	public void setSubRubrics(java.util.List<SubRubric> subRubrics) {
		this.subRubrics = subRubrics;
	}

	public java.util.List<Rubric> getRubrics() {
		return rubrics;
	}

	public void setRubrics(java.util.List<Rubric> rubrics) {
		this.rubrics = rubrics;
	}
	
	public List<ProjectInvestment> getListProjectInvestiment() {
		return listProjectInvestiment;
	}

	public void setListProjectInvestiment(
			List<ProjectInvestment> listProjectInvestiment) {
		this.listProjectInvestiment = listProjectInvestiment;
	}
	

}
