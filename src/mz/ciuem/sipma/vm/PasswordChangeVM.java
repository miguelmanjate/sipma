package mz.ciuem.sipma.vm;

import java.io.IOException;

import mz.ciuem.sipma.entity.SubSector;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PasswordChangeVM extends AbstractVM {

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		ui = view;

	}

	@Init
	public void iinit(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);
		
		loggeduser = userService.getUser(authentication.getName());
		//loggedOrgan = loggeduser.getOrgan();
	}
	
	@NotifyChange({ "loggeduser"})
	@Command
	public void resetPassword() {
		
		Textbox tbx_novaSenha = (Textbox) ui.getFellow("novaSenha");
		Textbox tbx_novaSenha2 = (Textbox) ui.getFellow("novaSenha2");
		
		
		
		if(tbx_novaSenha.getValue().equals(tbx_novaSenha2.getValue())){
			String senha = userService.encriptarSenha(tbx_novaSenha.getValue());
			loggeduser.setPassword(senha);
			userService.update(loggeduser);
			
			log("Alterou a senha: "  + loggeduser.getUsername());
			Clients.showNotification("Senha Alterada Com Sucesso!",
					Clients.NOTIFICATION_TYPE_ERROR, ui, "before_center", -1);
			limparCAmpos();
		}else{
			Clients.showNotification("Introduza a mesma Senha para os dois campos!",
					Clients.NOTIFICATION_TYPE_ERROR, ui, "before_center", -1);
		}

	
	}
	
	public void limparCAmpos(){
		Textbox tbx_novaSenha = (Textbox) ui.getFellow("novaSenha");
		Textbox tbx_novaSenha2 = (Textbox) ui.getFellow("novaSenha2");
		
		tbx_novaSenha.setRawValue(null); 
		tbx_novaSenha2.setRawValue(null); 
		
	}
	
}
