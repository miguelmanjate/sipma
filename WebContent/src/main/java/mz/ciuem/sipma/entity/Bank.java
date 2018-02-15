package mz.ciuem.sipma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.IdEntity;

@Entity
@Table(name = "banks")
public class Bank extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "bank_description", nullable = false)
	private String bankDescription;

	@Column(name = "bank_initials")
	private String bankInitials;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
	private List<Account> accounts = new ArrayList<Account>();

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getBankDescription() {
		return bankDescription;
	}

	public void setBankDescription(String bankDescription) {
		this.bankDescription = bankDescription;
	}

	public String getBankInitials() {
		return bankInitials;
	}

	public void setBankInitials(String bankInitials) {
		this.bankInitials = bankInitials;
	}

}
