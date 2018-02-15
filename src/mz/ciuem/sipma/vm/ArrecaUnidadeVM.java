package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import mz.ciuem.sipma.entity.CriticalArea;
import mz.ciuem.sipma.entity.FaturasDeCandidatura;
import mz.ciuem.sipma.entity.Invoice;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Table;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Div;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ArrecaUnidadeVM extends AbstractVM{
	
			
		List<Invoice> listInvoice;
		
		Invoice _invoice= new Invoice();
		
		private List<CriticalArea> criticalAreas;

		private Table dynamicTable;

		private Component ui;
		

		@AfterCompose
		public void initSetup(@ContextParam(ContextType.VIEW) Component view)
				throws IOException {

			Selectors.wireComponents(view, this, false);

		}

		@Init(superclass = true)
		public void init(@ContextParam(ContextType.VIEW) Component view) {
			
			int nrFacturas=0;
			long totalArrecadado=0;
			
			Client client = Client.create();
			
			WebResource webResourse = client.resource("http://196.3.101.56/sgea/rest/faturas");
			ClientResponse response  = webResourse.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				
				throw new RuntimeException("ERRO: Erro na requisicao"+ response.getStatus());
				
			}
			
			List<FaturasDeCandidatura> faturas = (List<FaturasDeCandidatura>) response.getEntity(new GenericType<List<FaturasDeCandidatura>>(){});
			
			
				for (FaturasDeCandidatura fatura : faturas) {
					System.out.println(fatura.getDataDePagamento()+ " "+fatura.getServico()+" TOTAL:"+fatura.getTotalArecadado());
					nrFacturas+=1;  totalArrecadado+=fatura.getTotalArecadado(); 
				}
				

			this.ui = view;
					
			listInvoice= new ArrayList<Invoice>();
			
			_invoice.setDescricao("Direcção Pedagógica");
			_invoice.setEntidade(nrFacturas+"");
			_invoice.setNrFactura(totalArrecadado+"");
			
			listInvoice.add(_invoice);

		}
		
		@Command
		public void view(@BindingParam("cycleId") Long cycleId, @BindingParam("tgt") Div target,
				@BindingParam("bc") Div breadcrumb) {
			
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			target.getChildren().clear();

			Executions.createComponents("views/financas/pagArrecadacoes.zul",target,map);
			
		}

		public List<Invoice> getListInvoice() {
			return listInvoice;
		}

		public void setListInvoice(List<Invoice> listInvoice) {
			this.listInvoice = listInvoice;
		}

		public List<CriticalArea> getCriticalAreas() {
			return criticalAreas;
		}

		public void setCriticalAreas(List<CriticalArea> criticalAreas) {
			this.criticalAreas = criticalAreas;
		}

		public Table getDynamicTable() {
			return dynamicTable;
		}

		public void setDynamicTable(Table dynamicTable) {
			this.dynamicTable = dynamicTable;
		}

		public Component getUi() {
			return ui;
		}

		public void setUi(Component ui) {
			this.ui = ui;
		}
	

}
