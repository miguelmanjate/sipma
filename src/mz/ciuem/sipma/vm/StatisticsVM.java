package mz.ciuem.sipma.vm;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class StatisticsVM {

	private Component ui;

	@Wire("#mainlayout")
	private Div target;

	@Wire
	private Div main;

	@Wire
	private Div breadcrumb;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {

	}

	@NotifyChange("Publicar")
	@Command
	public void watched(@BindingParam("Publicar") Long pagID,
			@BindingParam("target") Div target) {

		Clients.showNotification(
				"Seja bem vindo ao Sistema Integrado de Partilha e Monitoria de Actividades",
				"info", main, "before_end", 3000);
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

}
