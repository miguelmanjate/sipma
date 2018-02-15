package mz.ciuem.sipma.vm;

import java.util.HashMap;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Div;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ConfiguracaoServicoVM {
	
	
	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		

	}
	
	@Command
	public void voltar(@BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();

		Executions.createComponents("views/parameterization/registoDeRubricaUEMReceitas.zul",target,map);
		
	}

}
