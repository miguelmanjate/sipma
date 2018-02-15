package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Profession;
import mz.ciuem.sipma.service.ProfessionService;

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
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProfessionVM extends AbstractVM {

	private Profession profession;

	private List<Profession> professions;

	@WireVariable
	private ProfessionService professionService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		ui = view;

	}

	@Init
	public void init() {

		professions = professionService.getAll();

		profession = new Profession();

	}

	@NotifyChange({ "profession", "professions" })
	@Command
	public void save() {

		professionService.create(profession);
		log("Registou nova Profissao: " + profession.getName());
		Clients.showNotification("Profissao registada com sucesso!", "info",
				ui, "before_center", -1);

		professions = professionService.getAll();
		profession = new Profession();

		refreshDataTable(null);

	}

	@NotifyChange("profession")
	@Command
	public void edit(@BindingParam("professionID") Long professionID) {

		profession = (Profession) professionService.find(professionID);
		editButtons();
	}

	@NotifyChange({ "sector", "sectors" })
	@Command
	public void update() {
		professionService.update(profession);

		cancelButtons();

		log("Actualizou a Profissao: " + profession.getName());

		Clients.showNotification(" Profissao Actualizada  com sucesso!",
				Clients.NOTIFICATION_TYPE_INFO, ui, "before_center", -1);

		profession = new Profession();
		professions = professionService.getAll();

		refreshDataTable(null);

		cancelButtons();
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public List<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}

}
