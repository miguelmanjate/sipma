package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.CriticalArea;
import mz.ciuem.sipma.service.CriticalAreaService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Table;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CriticalAreaVM extends AbstractVM {

	private CriticalArea criticalArea;

	private List<CriticalArea> criticalAreas;

	@WireVariable
	private CriticalAreaService criticalAreaService;

	private Table dynamicTable;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		criticalArea = new CriticalArea();

		criticalAreas = criticalAreaService.getAll();
	}

	@NotifyChange({ "criticalArea", "criticalAreas" })
	@Command
	public void save() {

		criticalAreaService.create(criticalArea);

		log("Registou nova Área de Gestão: " + criticalArea.getDesignation());
		Clients.showNotification("Area de Gestão registada com sucesso!",
				"info", ui, "before_center", -1);

		criticalAreas = criticalAreaService.getAll();
		criticalArea = new CriticalArea();

		refreshDataTable(null);
	}

	@NotifyChange("criticalArea")
	@Command
	public void edit(@BindingParam("criticalAreaId") Long criticalAreaId) {
		criticalArea = (CriticalArea) criticalAreaService.find(criticalAreaId);
		editButtons();
	}

	@NotifyChange({ "criticalArea", "criticalAreas" })
	@Command
	public void update() {

		criticalAreaService.update(criticalArea);

		cancelButtons();

		log("Actualizou a Area de Gestao: " + criticalArea.getDesignation());

		Clients.showNotification(
				" Area de Gestao graActualizada  com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		setCriticalArea(new CriticalArea());

		criticalAreas = criticalAreaService.getAll();

		refreshDataTableList();
	}

	public void refreshDataTableList() {
		criticalAreaService.getAll();
		refreshDataTable(null);
	}

	public CriticalArea getCriticalArea() {
		return criticalArea;
	}

	public void setCriticalArea(CriticalArea criticalArea) {
		this.criticalArea = criticalArea;
	}

	public List<CriticalArea> getCriticalAreas() {
		return criticalAreas;
	}

	public void setCriticalAreas(List<CriticalArea> criticalAreas) {
		this.criticalAreas = criticalAreas;
	}

	public Table getDynamicTable() {
		return dynamicTable;
	}

	public void setDynamicTable(Table dynamicTable) {
		this.dynamicTable = dynamicTable;
	}
}
