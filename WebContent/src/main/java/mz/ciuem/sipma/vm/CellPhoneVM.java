package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.CellPhone;
import mz.ciuem.sipma.entity.Counter;
import mz.ciuem.sipma.service.CellPhoneService;
import mz.ciuem.sipma.service.CounterService;

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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CellPhoneVM extends AbstractVM {

	private CellPhone cellPhone;

	private List<CellPhone> cellPhones;

	@WireVariable
	private CellPhoneService cellPhoneService;
	
	@WireVariable
	private CounterService counterService;

	private List<Counter> counters;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		cellPhones = cellPhoneService.getAll();

		cellPhone = new CellPhone();
		
		setCounters(counterService.getAll());

	}

	public void setCellPhone(CellPhone cellPhone) {
		this.cellPhone = cellPhone;
	}

	@NotifyChange({ "cellPhone", "cellPhones" })
	@Command
	public void save() {

		cellPhoneService.create(cellPhone);

		cellPhones = cellPhoneService.getAll();

		cellPhone = new CellPhone();

		refreshDataTable(null);

	}

	@NotifyChange("cellPhone")
	@Command
	public void edit(@BindingParam("cellPhoneID") Long cellPhoneID) {

		cellPhone = (CellPhone) cellPhoneService.find(cellPhoneID);

		editButtons();

	}

	@NotifyChange({ "cellPhone", "cellPhones" })
	@Command
	public void update() {

		cellPhoneService.update(cellPhone);

		cellPhones = cellPhoneService.getAll();

		cellPhone = new CellPhone();

		refreshDataTable(null);

		cancelButtons();
	}

	public List<CellPhone> getCellPhones() {
		return cellPhones;
	}

	public CellPhone getCellPhone() {
		return cellPhone;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	
}
