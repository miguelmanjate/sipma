package mz.ciuem.sipma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="invoice_item")
public class InvoiceItem extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	

		private String desigancaoDaUnidade;
		private int faturasEmitidas;
		private double totalArecadado;
		private String dataDePagamento;
		private String servico =  "PAGAMENTO DE EXAME";
		private String universidade;
		private String rubrica = "VENDA DE SERVICO";
		

		private String descricao;

		public String getDesigancaoDaUnidade() {
			return desigancaoDaUnidade;
		}

		public void setDesigancaoDaUnidade(String desigancaoDaUnidade) {
			this.desigancaoDaUnidade = desigancaoDaUnidade;
		}

		public int getFaturasEmitidas() {
			return faturasEmitidas;
		}

		public void setFaturasEmitidas(int faturasEmitidas) {
			this.faturasEmitidas = faturasEmitidas;
		}

		public double getTotalArecadado() {
			return totalArecadado;
		}



		public String getDataDePagamento() {
			return dataDePagamento;
		}

		public void setDataDePagamento(String dataDePagamento) {
			this.dataDePagamento = dataDePagamento;
		}

		public void setTotalArecadado(double totalArecadado) {
			this.totalArecadado = totalArecadado;
		}

		public String getServico() {
			return servico;
		}

		public void setServico(String servico) {
			this.servico = servico;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getUniversidade() {
			return universidade;
		}

		public void setUniversidade(String universidade) {
			this.universidade = universidade;
		}

		public String getRubrica() {
			return rubrica;
		}

		public void setRubrica(String rubrica) {
			this.rubrica = rubrica;
		}
	
}
