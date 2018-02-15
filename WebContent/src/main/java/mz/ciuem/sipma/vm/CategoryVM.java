package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.entity.ProjectInvestment;
import mz.ciuem.sipma.service.CategoryService;
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
public class CategoryVM extends AbstractVM {

    private Categoria categoria;
	
	private List<Categoria> categorys;
	
	@Wire
	private Div categoryList;

	@WireVariable
	private CategoryService categoryService;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init() {
		categoria = new Categoria();
		categorys = categoryService.getAll();
	}
	
	
	@NotifyChange({ "categoria", "categorys" })
	@Command
	public void save() {

		categoryService.create(categoria);

		log("Registou nova Categoria: "
				+ categoria.getDescription());
		Clients.showNotification(categoria.getDescription()
				+ " registado com sucesso!", "info", categoryList,
				"before_center", -1);

		setCategorys(categoryService.getAll());

		categoria = new Categoria();
		refreshDataTable(null);
	}
	
	@NotifyChange("categoria")
	@Command
	public void edit(@BindingParam("typeId") Long id) {

		setCategoria(categoryService.find(id));
		editButtons();
	}

	@NotifyChange({ "categoria", "categorys" })
	@Command
	public void update() {

		categoryService.update(categoria);

		cancelButtons();

		log("Actualizou Categoria: " + categoria.getDescription());
		Clients.showNotification(categoria.getDescription()
				+ " actualizado com sucesso!", "info", categoryList,
				"before_center", -1);

		categorys = categoryService.getAll();

		categoria = new Categoria();
		
		refreshDataTable(null);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Categoria> categorys) {
		this.categorys = categorys;
	}

	public Div getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Div categoryList) {
		this.categoryList = categoryList;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	
	
}
