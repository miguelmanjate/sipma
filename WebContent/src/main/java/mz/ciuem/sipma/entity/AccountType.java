package mz.ciuem.sipma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.IdEntity;

@Entity
@Table(name = "account_types")
public class AccountType extends IdEntity {
	
	private String type;

	private static final long serialVersionUID = 1L;

	@Column(name = "account_type_desc")
	private String accountTypeDesc;

	public String getAccountTypeDesc() {
		return accountTypeDesc;
	}

	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
