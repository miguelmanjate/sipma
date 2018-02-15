package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "phases")
@Access(AccessType.FIELD)
public class Phase extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "designacao")
	private String designacao;
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigo);
		hcb.append(designacao);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Phase)) {
			return false;
		}
		Phase that = (Phase) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigo, that.codigo);
		eb.append(designacao, that.designacao);
		return eb.isEquals();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

}
