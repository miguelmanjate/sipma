package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.sipma.entity.Bank;
import mz.ciuem.sipma.service.BankService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BankVM extends AbstractVM {

	private Bank bank;

	@WireVariable
	private BankService bankService;

	private List<Bank> banks;

	private Window modal;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		banks = bankService.getAll();

		bank = new Bank();

		this.ui = view;
	}

	@NotifyChange({ "bank", "banks" })
	@Command
	public void save() {

		bankService.create(bank);

		log("Registou novo critério: " + bank.getBankInitials());
		Clients.showNotification("Banco registado com sucesso!", "info", ui, "before_center", -1);

		banks = bankService.getAll();
		bank = new Bank();

		refreshDataTable(null);
	}

	@NotifyChange("bank")
	@Command
	public void edit(@BindingParam("bankID") Long bankID) {

		bank = (Bank) bankService.find(bankID);

		editButtons();

	}

	@NotifyChange({ "bank", "banks" })
	@Command
	public void update() {

		bankService.update(bank);

		banks = bankService.getAll();
		bank = new Bank();

		log("Actualizou os dados do Banco: " + bank.getBankInitials());
		Clients.showNotification("Banco Actualizado com sucesso!", "info", ui, "before_center", -1);

		refreshDataTable(null);

		cancelButtons();

	}

	@NotifyChange({ "bank", "banks" })
	@Command
	public void show(@BindingParam("bankId") Long bankId) {

		bank = bankService.find(bankId);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		
		modal = (Window) Executions.createComponents("accounts/pagShowBank.zul", ui, map);
		modal.doModal();
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

}
