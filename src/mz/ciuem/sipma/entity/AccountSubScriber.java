package mz.ciuem.sipma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.IdEntity;

@Entity
@Table(name = "account_sub_scriber")
public class AccountSubScriber extends IdEntity {

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_sub_scribers", joinColumns = @JoinColumn(name = "account_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "sub_scriber_id", nullable = false, updatable = false))
	private Set<Account> accountSubScribers = new HashSet<Account>();

	private Date startDate;

	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
