package mz.ciuem.sipma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.IdEntity;
import mz.ciuem.sipma.entity.Sector;

@Entity
@Table(name = "subscribers")
public class SubScriber extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "number")
	private String number;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee")
	private Employee employee;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector")
	private Sector sector;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

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



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

}
