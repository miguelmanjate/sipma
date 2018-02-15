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
@Table(name = "purposes")
public class Purpose extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	private String code;

	@Column(name = "porpose")
	private String porpose;

	@Column(name = "purpose_description")
	private String purposeDescription;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purpose")
	private List<Account> accounts = new ArrayList<Account>();

	public String getPurposeDescription() {
		return purposeDescription;
	}

	public void setPurposeDescription(String purposeDescription) {
		this.purposeDescription = purposeDescription;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public String getPorpose() {
		return porpose;
	}

	public void setPorpose(String porpose) {
		this.porpose = porpose;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
