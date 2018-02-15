package mz.ciuem.sipma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="payments")
public class Payments extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "montante")
	private double montante;
	
	@Column(name = "dataPagamento")
	private Date dataPagamento;
	
	@Column(name = "referencia")
	private String referencia;
	
	@Column(name="conta")
	private String conta;
	
	@Column(name="pagamentoConfirmado")
	private boolean pagamentoConfirmado;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getMontante() {
		return montante;
	}

	public void setMontante(double montante) {
		this.montante = montante;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public boolean isPagamentoConfirmado() {
		return pagamentoConfirmado;
	}

	public void setPagamentoConfirmado(boolean pagamentoConfirmado) {
		this.pagamentoConfirmado = pagamentoConfirmado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
