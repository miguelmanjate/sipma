package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubSector;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.SectorService;
import mz.ciuem.sipma.service.SubSectorService;

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
public class SubSectorVM extends AbstractVM {

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private SubSectorService subSectorService;

	@WireVariable
	private EmployeeService employeeService;

	private Sector sector;

	private List<Sector> sectors;

	private SubSector subSector;

	private List<SubSector> subSectors;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);

		sectors = sectorService.getAll();

		subSector = new SubSector();
		subSectors = subSectorService.getAll();

		this.ui = view;
	}

	@NotifyChange({ "subSector", "subSectors" })
	@Command
	public void saveSubSector() {

		subSectorService.create(subSector);

		subSector = new SubSector();

		subSectors = subSectorService.getAll();

		log("Registou novo subsector: " + subSector.getDesignation()
				+ " ao sector: " + subSector.getDesignation());
		Clients.showNotification("SubSector gravado com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		refreshDataTable(null);
	}

	@NotifyChange("subSector")
	@Command
	public void edit(@BindingParam("subSectorId") Long subSectorId) {

		subSector = (SubSector) subSectorService.find(subSectorId);

		editButtons();
	}

	@NotifyChange({ "subSector", "subSectors" })
	@Command
	public void update() {

		subSectorService.update(subSector);

		subSector = new SubSector();

		subSectors = subSectorService.getAll();

		log("Actualizou novo subsector: " + subSector.getDesignation()
				+ " ao sector: " + subSector.getDesignation());
		Clients.showNotification("SubSector Actulizado com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		refreshDataTable(null);
		
		cancelButtons();
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public SubSector getSubSector() {
		return subSector;
	}

	public void setSubSector(SubSector subSector) {
		this.subSector = subSector;
	}

	public List<SubSector> getSubSectors() {
		return subSectors;
	}

	public void setSubSectors(List<SubSector> subSectors) {
		this.subSectors = subSectors;
	}
}
