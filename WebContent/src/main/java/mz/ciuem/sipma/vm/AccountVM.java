package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import mz.ciuem.sipma.entity.Account;
import mz.ciuem.sipma.entity.AccountType;
import mz.ciuem.sipma.entity.Bank;
import mz.ciuem.sipma.entity.Coin;
import mz.ciuem.sipma.entity.Counter;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Purpose;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.SubScriber;
import mz.ciuem.sipma.service.AccountService;
import mz.ciuem.sipma.service.AccountTypeService;
import mz.ciuem.sipma.service.BankService;
import mz.ciuem.sipma.service.CoinService;
import mz.ciuem.sipma.service.CounterService;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.PurposeService;
import mz.ciuem.sipma.service.SectorService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

public class AccountVM extends AbstractVM {

	private Account account;

	private List<Account> accounts;

	private List<Bank> banks;

	private List<Organ> organs;

	@WireVariable
	private OrganService organService;

	private List<Counter> counters;

	@WireVariable
	private CounterService counterService;

	@WireVariable
	private BankService bankService;

	@WireVariable
	private AccountService accountService;

	private SubScriber subScriber;

	private List<Employee> employees;

	private List<Sector> sectors;

	@WireVariable
	private PurposeService purposeService;

	private List<Purpose> purposes;

	private List<Coin> coins;

	@WireVariable
	private CoinService coinService;

	@WireVariable
	private SectorService sectorService;

	@WireVariable
	private EmployeeService employeeService;

	private List<AccountType> accountTypes;

	@WireVariable
	private AccountTypeService accountTypeService;

	private HashSet<SubScriber> subScribersAcount = new HashSet<SubScriber>();

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		account = new Account();

		setSubScriber(new SubScriber());

		accounts = accountService.getAll();

		setEmployees(employeeService.getAll());

		counters = counterService.getAll();

		banks = bankService.getAll();

		sectors = sectorService.getAll();

		purposes = purposeService.getAll();

		coins = coinService.getAll();

		organs = organService.getAll();

		setAccountTypes(accountTypeService.getAll());

		subScribersAcount.addAll(account.getSubScribers());

		this.ui = view;
	}

	@NotifyChange({ "subScriber", "subScribersAcount", "accounts" })
	@Command
	public void addSubScriber() {

		subScribersAcount.add(subScriber);

		log("Registou nova Assinante: " + subScriber.getNumber());

		Clients.showNotification("Assinante adicionada com sucesso!", "info",
				ui.getFellow("tblSubScriber"), "before_center", -1);

		subScriber = new SubScriber();

		ui.getFellow("tblSubScriber").invalidate();

	}

	@NotifyChange({ "account", "subScribersAcount", "accounts", "subScriber","accounts" })
	@Command
	public void save() {

		account.getSubScribers().addAll(subScribersAcount);
		accountService.createAccount(account);
		Clients.showNotification("Conta #" + account.getId()
				+ " registada com sucesso!", "info", ui, "before_center", -1);
		refreshDataTable(ui);

	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	public SubScriber getSubScriber() {
		return subScriber;
	}

	public void setSubScriber(SubScriber subScriber) {
		this.subScriber = subScriber;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<Purpose> getPurposes() {
		return purposes;
	}

	public void setPurposes(List<Purpose> purposes) {
		this.purposes = purposes;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

	public List<AccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<AccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public HashSet<SubScriber> getSubScribersAcount() {
		return subScribersAcount;
	}

	public void setSubScribersAcount(HashSet<SubScriber> subScribersAcount) {
		this.subScribersAcount = subScribersAcount;
	}

}
