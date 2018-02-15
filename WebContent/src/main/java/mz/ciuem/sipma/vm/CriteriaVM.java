package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Criteria;
import mz.ciuem.sipma.service.CriteriaService;
import mz.ciuem.sipma.util.CriteriaTypes;

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
import org.zkoss.zul.Label;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CriteriaVM extends AbstractVM {

	private Criteria criteria;

	private List<Criteria> criterias;

	@WireVariable
	private CriteriaService criteriaService;

	private Long criteriaId;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		criterias = criteriaService.getAll();

		setCriteria(new Criteria());

	}

	@NotifyChange({ "criteria", "criterias" })
	@Command
	public void save() {

		criteriaService.create(criteria);

		log("Registou novo critério: " + criteria.getDescription());
		Clients.showNotification("Criterio registado com sucesso!", "info", ui,
				"before_center", -1);

		criterias = criteriaService.getAll();
		criteria = new Criteria();

		refreshDataTable(null);

		ui.getFellow("desc").setVisible(false);
	}

	@Command
	public void showDescription(@BindingParam("type") String type) {

		Label lbl = (Label) ui.getFellow("desc");

		lbl.setVisible(true);

		CriteriaTypes criteria = CriteriaTypes.valueOf(type);

		lbl.setValue(criteria.getDescription());
	}

	@NotifyChange("criteria")
	@Command
	public void edit(@BindingParam("criteriaId") Long criteriaId) {

		criteria = (Criteria) criteriaService.find(criteriaId);

		editButtons();
	}

	@NotifyChange({ "criteria", "criterias" })
	@Command
	public void update() {

		criteriaService.update(criteria);

		cancelButtons();

		log("Actualizou o Criterio: " + criteria.getName());
		Clients.showNotification("Criterio graActualizado ado com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);
		setCriteria(new Criteria());

		criterias = criteriaService.getAll();

		refreshDataTable(null);

	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public List<Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}

	public CriteriaService getCriteriaService() {
		return criteriaService;
	}

	public void setCriteriaService(CriteriaService criteriaService) {
		this.criteriaService = criteriaService;
	}

	public Long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(Long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public static void main(String[] args) {

	}
}
