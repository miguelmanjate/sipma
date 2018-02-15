package mz.ciuem.sipma.vm;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PasswordChangeVM extends AbstractVM {

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		ui = view;

	}

	@Init
	public void init() {

	}
	
}
