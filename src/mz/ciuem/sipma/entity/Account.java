package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.IdEntity;
import mz.ciuem.sipma.entity.Organ;

@Entity
@Table(name = "accounts")
public class Account extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "account_number")
	private String accountNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bank")
	private Bank bank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "counter")
	private Counter counter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purpose")
	private Purpose purpose;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin")
	private Coin coin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_type")
	private AccountType accountType;

	@Column(name = "date_opening")
	private Date dateOpening;

	@Column(name = "date_waxing")
	private Date dateWaxing;

	@Column(name = "account_state")
	private String accountState;

	@Column(name = "account_styling")
	private String accountStyling;

	@Column(name = "account_Iban")
	private String accountIban;

	@Column(name = "account_nib")
	private String accountNib;

	@Column(name = "account_condMov")
	private String accountCondMov;

	@Column(name = "comments")
	private String Comments;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<SubScriber> subScribers = new ArrayList<SubScriber>();
	
	

	@ManyToOne
	@JoinColumn(name = "organ")
	private Organ organ;

	public List<SubScriber> getSubScribers() {
		return subScribers;
	}

	public void setSubScribers(List<SubScriber> subScribers) {
		this.subScribers = subScribers;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getDateOpening() {
		return dateOpening;
	}

	public void setDateOpening(Date dateOpening) {
		this.dateOpening = dateOpening;
	}

	public Date getDateWaxing() {
		return dateWaxing;
	}

	public void setDateWaxing(Date dateWaxing) {
		this.dateWaxing = dateWaxing;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	public String getAccountStyling() {
		return accountStyling;
	}

	public void setAccountStyling(String accountStyling) {
		this.accountStyling = accountStyling;
	}

	public String getAccountIban() {
		return accountIban;
	}

	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}

	public String getAccountNib() {
		return accountNib;
	}

	public void setAccountNib(String accountNib) {
		this.accountNib = accountNib;
	}

	public String getAccountCondMov() {
		return accountCondMov;
	}

	public void setAccountCondMov(String accountCondMov) {
		this.accountCondMov = accountCondMov;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
