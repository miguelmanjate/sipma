package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.AccountType;
import mz.ciuem.sipma.service.AccountTypeService;

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
public class AccountTypeVM extends AbstractVM {

	private AccountType accountType;

	private List<AccountType> accountTypes;

	@WireVariable
	private AccountTypeService accountTypeService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		accountType = new AccountType();
		accountTypes = accountTypeService.getAll();
	}

	@NotifyChange({ "accountType", "accountTypes" })
	@Command
	public void save() {

		accountTypeService.create(accountType);

		log("Registou novo Tipo de Conta: " + accountType.getType());
		Clients.showNotification("Tipo de Conta registado com sucesso!", "info", ui, "before_center", -1);

		accountTypes = accountTypeService.getAll();

		accountType = new AccountType();

		refreshDataTable(null);
	}
	

	@NotifyChange("accountType")
	@Command
	public void edit(@BindingParam("accountTypeId") Long accountTypeId) {

		accountType = (AccountType) accountTypeService.find(accountTypeId);

		editButtons();

	}

	@NotifyChange({ "accountType", "accountTypes" })
	@Command
	public void update() {

		accountTypeService.update(accountType);

		log("Actualizou Tipo de Conta: " + accountType.getType());
		Clients.showNotification("Tipo de Conta registado com sucesso!", "info", ui, "before_center", -1);

		accountTypes = accountTypeService.getAll();

		accountType = new AccountType();

		refreshDataTable(null);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public List<AccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<AccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}
}
