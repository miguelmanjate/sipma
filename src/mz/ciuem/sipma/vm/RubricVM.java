package mz.ciuem.sipma.vm;

import java.io.IOException;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.service.CategoryService;
import mz.ciuem.sipma.service.RubricService;
import mz.ciuem.sipma.service.SuperRubricService;

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
public class RubricVM extends AbstractVM {

	private Rubric rubric;
	
	private SuperRubric superRubric;

	private java.util.List<SuperRubric> superRubrics;
	private java.util.List<Rubric> rubrics;

	private java.util.List<Categoria> categorias;

	@Wire
	private Div projectList;

	@WireVariable
	private SuperRubricService superRubricService;
	
	@WireVariable
	private RubricService rubricService;
	
	@WireVariable
	private CategoryService categoryService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init() {
		rubric = new Rubric();
//		categorias = categoryService.getAll();
		rubrics = rubricService.getAll();
		superRubrics = superRubricService.getAll();
		
	}

	@NotifyChange({ "rubric", "rubrics" })
	@Command
	public void save() {

		rubricService.create(rubric);

		log("Registou uma Nova Grande Rúbrica: " + rubric.getName());
		Clients.showNotification(rubric.getName() + " registado com sucesso!",
				"info", projectList, "before_center", -1);

		setRubrics(rubricService.getAll());
		superRubrics = superRubricService.getAll();
		rubrics = rubricService.getAll();
//		setCategorias(categoryService.getAll());

		rubric = new Rubric();
		
		refreshDataTable(null);
	}
	
	public java.util.List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(java.util.List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@NotifyChange("rubric")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setRubric(rubricService.find(id));
		editButtons();
	}

	@NotifyChange({ "rubric", "rubrics" })
	@Command
	public void update() {

		rubricService.update(rubric);

		cancelButtons();

		log("Actualizou Grande Rúbrica: " + rubric.getName());
		Clients.showNotification(
				rubric.getName() + " actualizado com sucesso!", "info",
				projectList, "before_center", -1);

		rubrics = rubricService.getAll();
		superRubrics = superRubricService.getAll();
		
		rubric = new Rubric();
		refreshDataTable(null);
	}

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public java.util.List<SuperRubric> getSuperRubrics() {
		return superRubrics;
	}

	public void setSuperRubrics(java.util.List<SuperRubric> superRubrics) {
		this.superRubrics = superRubrics;
	}

	public java.util.List<Rubric> getRubrics() {
		return rubrics;
	}

	public void setRubrics(java.util.List<Rubric> rubrics) {
		this.rubrics = rubrics;
	}

	public SuperRubric getSuperRubric() {
		return superRubric;
	}

	public void setSuperRubric(SuperRubric superRubric) {
		this.superRubric = superRubric;
	}
	
	

}
