package mz.ciuem.sipma.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import mz.ciuem.sipma.entity.InvoiceItem;

public class ClienteFacturasExames {

	public static void main(String[] args) {		
	Client client = Client.create();
		
		WebResource webResourse = client.resource("http://196.3.101.56/sgea/rest/faturas");
		ClientResponse response  = webResourse.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			
			throw new RuntimeException("ERRO: Erro na requisicao"+ response.getStatus());
			
		}
		
		List<InvoiceItem> faturas = (List<InvoiceItem>) response.getEntity(new GenericType<List<InvoiceItem>>(){});
		
		
			for (InvoiceItem fatura : faturas) {
				System.out.println(fatura.getDataDePagamento()+ " "+fatura.getServico()+" TOTAL:"+fatura.getTotalArecadado());


	}

}
}
