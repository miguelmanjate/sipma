package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import mz.ciuem.sipma.entity.CriticalArea;
import mz.ciuem.sipma.entity.FaturasDeCandidatura;
import mz.ciuem.sipma.service.InvoiceService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.PaymentsService;

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
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ArreacadacoesVM extends AbstractVM{
		
	List<FaturasDeCandidatura> listPayments;
	
	private List<CriticalArea> criticalAreas;

	private Table dynamicTable;

	private Component ui;
	
	@WireVariable
	private PaymentsService _paymentsService;
	
	@WireVariable
	private OrganService organService;
	
	@WireVariable
	private InvoiceService invoiceService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;
				
		listPayments= new ArrayList<FaturasDeCandidatura>();
		
		Client client = Client.create();
		
		WebResource webResourse = client.resource("http://196.3.101.56/sgea/rest/faturas");
		ClientResponse response  = webResourse.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			
			throw new RuntimeException("ERRO: Erro na requisicao"+ response.getStatus());
			
		}
		
		List<FaturasDeCandidatura> faturas = (List<FaturasDeCandidatura>) response.getEntity(new GenericType<List<FaturasDeCandidatura>>(){});
		
		
			for (FaturasDeCandidatura fatura : faturas) {
				System.out.println(fatura.getDataDePagamento()+ " "+fatura.getServico()+" TOTAL:"+fatura.getTotalArecadado());
				listPayments.add(fatura);
			}
				
	
		
	

	}
	
	@NotifyChange("organ")
	@Command
	public void view(@BindingParam("orgId") Long organId) {

		Executions.createComponents("views/financas/receitas_unidade.zul",null,null);
		
	}

	public List<FaturasDeCandidatura> getListPayments() {
		return listPayments;
	}

	public void setListPayments(List<FaturasDeCandidatura> listPayments) {
		this.listPayments = listPayments;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public PaymentsService get_paymentsService() {
		return _paymentsService;
	}

	public void set_paymentsService(PaymentsService _paymentsService) {
		this._paymentsService = _paymentsService;
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
