package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.comps.DualListbox;
import mz.ciuem.sipma.entity.Bank;
import mz.ciuem.sipma.entity.CellPhone;
import mz.ciuem.sipma.entity.Counter;
import mz.ciuem.sipma.service.BankService;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CounterVM extends AbstractVM {

	@WireVariable
	private CounterService counterService;

	@WireVariable
	private BankService bankService;

	private Counter counter;

	private List<Counter> counters;

	private List<Bank> banks;

	private List<CellPhone> cellPhones;

	@WireVariable
	private CellPhoneService cellPhoneService;

	@Wire
	private DualListbox selectedRoles;

	private Window modal;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		counters = counterService.getAll();

		banks = bankService.getAll();

		cellPhones = cellPhoneService.getAll();

		counter = new Counter();

		this.ui = view;
	}

	@NotifyChange({ "counter", "counters" })
	@Command
	public void save() {

		counterService.create(counter);
		log("Registou : " + counter.getCounterNm());
		Clients.showNotification("Balcao registado com sucesso!", "info", ui, "before_center", -1);

		counters = counterService.getAll();
		counter = new Counter();
		refreshDataTable(null);
	}

	@NotifyChange("counter")
	@Command
	public void edit(@BindingParam("counterID") Long counterID) {

		this.counter = (Counter)counterService.find(counterID);
		
		editButtons();


	}

	@NotifyChange({ "counter", "counters" })
	@Command
	public void update() {

		counterService.update(counter);

		log("Actualizou os dados do Balcao: " + counter.getCounterNm());
		Clients.showNotification("Balcao Actualizado com sucesso!", "info", ui, "before_center", -1);

		refreshDataTable(null);

	}

	public Window getModal() {
		return modal;
	}

	public Counter getCounter() {
		return counter;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public List<CellPhone> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(List<CellPhone> cellPhones) {
		this.cellPhones = cellPhones;
	}

}
