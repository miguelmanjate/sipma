package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import mz.ciuem.sipma.util.Breadcrumb;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class NovaReferenciaPagVM extends AbstractVM{
	
	private List<String> links ;
	
	private Component ui;

	private Window window;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}
	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		
		this.ui = view;
	}
	
	@Command
	public void voltarPagamento(@BindingParam("tgt") Div target, @BindingParam("bc") Div breadcrumb){
		
		final HashMap< String , Object> map = new HashMap<String , Object >();
		map.put("target", target);
		
		target.getChildren().clear();
		
		Executions.createComponents("views/parameterization/faturar/pagPagamentos.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Pagamentos");
		links.add("Executar");
		
		Breadcrumb.drawn(breadcrumb, "Pagamentos", links);
	}

}
