package mz.ciuem.sipma.vm;

import java.io.IOException;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.service.CategoryService;
import mz.ciuem.sipma.service.ProjectInvestimentService;
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
public class SuperRubricVM extends AbstractVM {

	private SuperRubric superRubric;

	private java.util.List<Categoria> categorias;

	private java.util.List<SuperRubric> superRubrics;

	@Wire
	private Div projectList;

	@WireVariable
	private CategoryService categoryService;

	@WireVariable
	private SuperRubricService superRubricService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init() {
		superRubric = new SuperRubric();
		categorias = categoryService.getAll();
		superRubrics = superRubricService.getAll();
	}

	@NotifyChange({ "superRubric", "superRubrics" })
	@Command("save")
	public void save() {

		superRubricService.create(superRubric);

		log("Registou nova Super Rúbrica: " + superRubric.getName());
		Clients.showNotification(superRubric.getName()
				+ " registado com sucesso!", "info", projectList,
				"before_center", -1);

		setCategorias(categoryService.getAll());
		
		superRubrics = superRubricService.getAll();
		superRubric = new SuperRubric();

		refreshDataTable(null);
	}

	@NotifyChange("superRubric")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setSuperRubric(superRubricService.find(id));
		editButtons();
	}

	@NotifyChange({ "superRubric", "superRubrics" })
	@Command
	public void update() {

		superRubricService.update(superRubric);

		cancelButtons();

		log("Actualizou Super Rúbrica: " + superRubric.getName());
		Clients.showNotification(superRubric.getName()
				+ " actualizado com sucesso!", "info", projectList,
				"before_center", -1);

		superRubrics = superRubricService.getAll();
		superRubric = new SuperRubric();
		refreshDataTable(null);
	}

	public SuperRubric getSuperRubric() {
		return superRubric;
	}

	public void setSuperRubric(SuperRubric superRubric) {
		this.superRubric = superRubric;
	}



	public java.util.List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(java.util.List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public java.util.List<SuperRubric> getSuperRubrics() {
		return superRubrics;
	}

	public void setSuperRubrics(java.util.List<SuperRubric> superRubrics) {
		this.superRubrics = superRubrics;
	}

}
