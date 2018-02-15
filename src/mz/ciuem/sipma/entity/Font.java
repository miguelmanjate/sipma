package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "fonts")
@Access(AccessType.FIELD)
public class Font extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	@Column(name = "designation")
	private String designation;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(code);
		hcb.append(designation);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Font)) {
			return false;
		}
		Font that = (Font) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(code, that.code);
		eb.append(designation, that.designation);
		return eb.isEquals();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

}
