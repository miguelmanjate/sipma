package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.CellPhone;
import mz.ciuem.sipma.entity.IdEntity;

@Entity
@Table(name = "counters")
public class Counter extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "counter_nm")
	private String counterNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id")
	private Bank bank;

	@Column(name = "conter_address")
	private String conterAddress;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "counter")
	private List<Account> accounts = new ArrayList<Account>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "counter")
	private List<CellPhone> cellPhones = new ArrayList<CellPhone>();

	public String getCounterNm() {
		return counterNm;
	}

	public void setCounterNm(String counterNm) {
		this.counterNm = counterNm;
	}

	public List<CellPhone> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(List<CellPhone> cellPhones) {
		this.cellPhones = cellPhones;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getConterAddress() {
		return conterAddress;
	}

	public void setConterAddress(String conterAddress) {
		this.conterAddress = conterAddress;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
