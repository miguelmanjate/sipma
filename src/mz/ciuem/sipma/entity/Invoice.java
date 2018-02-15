package mz.ciuem.sipma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="invoice")
public class Invoice extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "id_origem")
	private Long idorigem;
	
	@Column(name = "nrFacturaOrigem")
	private String nrFacturaOrigem;
	
	@Column(name = "nrFactura")
	private String nrFactura;
	
	@Column(name = "dataEmissao")
	private Date dataEmissao;
	
	@Column(name = "dataExp")
	private Date dataExpiracao;
	
	@Column(name = "paga")
	private boolean paga;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "tipoFactura")
	private String tipoFactura;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "prestacao")
	private boolean prestacao;
	
	@Column(name = "entidade")
	private String entidade;
	
	@Column(name = "referencia")
	private String referencia;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdorigem() {
		return idorigem;
	}

	public void setIdorigem(Long idorigem) {
		this.idorigem = idorigem;
	}

	public String getNrFacturaOrigem() {
		return nrFacturaOrigem;
	}

	public void setNrFacturaOrigem(String nrFacturaOrigem) {
		this.nrFacturaOrigem = nrFacturaOrigem;
	}

	public String getNrFactura() {
		return nrFactura;
	}

	public void setNrFactura(String nrFactura) {
		this.nrFactura = nrFactura;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isPrestacao() {
		return prestacao;
	}

	public void setPrestacao(boolean prestacao) {
		this.prestacao = prestacao;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//relacoes por acertar
	
	
	

}
