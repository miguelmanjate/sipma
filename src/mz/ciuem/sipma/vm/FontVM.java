package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Font;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.service.FontService;
import mz.ciuem.sipma.service.SubRubricService;

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
public class FontVM extends AbstractVM {

	private Font font;

	private List<Font> fonts;

	@WireVariable
	private FontService fontService;
	
	@WireVariable
	private SubRubricService subRubricService;
	
	private List<SubRubric> subRubrics;

	private Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		font = new Font();

		fonts = fontService.getAll();
		
		setSubRubrics(subRubricService.getAll());
	}

	@NotifyChange({ "font", "fonts" })
	@Command
	public void save() {

		fontService.create(font);

		log("Reportou nova Fonte de Orçamento: " + font.getDesignation());
		Clients.showNotification("Fonte #" + font.getId()
				+ " registado com sucesso!", "info", ui, "before_center", -1);

		fonts = fontService.getAll();
		font = new Font();

		refreshDataTable(null);
	}

	@NotifyChange("font")
	@Command
	public void edit(@BindingParam("fontId") Long fontId) {

		font = (Font)fontService.find(fontId);

		editButtons();
	}

	@NotifyChange({ "font", "fonts" })
	@Command
	public void update() {

		fontService.update(font);

		log("Reportou nova Fonte de Orçamento: " + font.getDesignation());
		Clients.showNotification("Fonte #" + font.getId()
				+ " Actualizada com sucesso!", "info", ui, "before_center", -1);

		fonts = fontService.getAll();
		
		font = new Font();

		refreshDataTable(null);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public List<SubRubric> getSubRubrics() {
		return subRubrics;
	}

	public void setSubRubrics(List<SubRubric> subRubrics) {
		this.subRubrics = subRubrics;
	}
}
