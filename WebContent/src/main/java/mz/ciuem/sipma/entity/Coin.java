package mz.ciuem.sipma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.sipma.entity.IdEntity;

@Entity
@Table(name = "coins")
public class Coin extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "coin_desc")
	private String coinDescription;

	@Column(name = "coin_initials")
	private String coinInitials;

	public String getCoinInitials() {
		return coinInitials;
	}

	public String getCoinDescription() {
		return coinDescription;
	}

	public void setCoinDescription(String coinDescription) {
		this.coinDescription = coinDescription;
	}

	public void setCoinInitials(String coinInitials) {
		this.coinInitials = coinInitials;
	}

}
