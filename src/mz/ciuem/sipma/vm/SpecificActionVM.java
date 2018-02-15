package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import mz.ciuem.sipma.entity.Action;
import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.BasedCalculating;
import mz.ciuem.sipma.entity.Font;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Role;
import mz.ciuem.sipma.entity.Rubric;
import mz.ciuem.sipma.entity.SpecifiedAction;
import mz.ciuem.sipma.entity.SubRubric;
import mz.ciuem.sipma.entity.SuperRubric;
import mz.ciuem.sipma.service.ActionService;
import mz.ciuem.sipma.service.BasedCalculatingService;
import mz.ciuem.sipma.service.FontService;
import mz.ciuem.sipma.service.OrganService;
import mz.ciuem.sipma.service.RoleService;
import mz.ciuem.sipma.service.RubricService;
import mz.ciuem.sipma.service.SpecifiedActionService;
import mz.ciuem.sipma.service.SubRubricService;
import mz.ciuem.sipma.service.SuperRubricService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Tbody;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Text;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SpecificActionVM extends AbstractVM {

	private List<SpecifiedAction> specifiedActions;

	private Textbox tbx_codigo;

	@WireVariable
	private ActionService actionService;

	@WireVariable
	private FontService fontService;

	@WireVariable
	private OrganService organService;

	@WireVariable
	private RoleService roleService;

	@WireVariable
	private BasedCalculatingService basedCalculatingService;

	@WireVariable
	private SpecifiedActionService specifiedActionService;

	@WireVariable
	private SuperRubricService superRubricService;

	@WireVariable
	private RubricService rubricService;

	@WireVariable
	private SubRubricService subRubricService;

	private String code;

	private List<Organ> organs;

	private List<Action> actions;

	private List<Font> fonts;

	private List<SuperRubric> superRubrics;

	private List<Rubric> rubrics;

	private SubRubric subRubric;

	public SubRubric getSubRubric() {
		return subRubric;
	}

	public void setSubRubric(SubRubric subRubric) {
		this.subRubric = subRubric;
	}

	private List<SubRubric> subRubrics;

	private List<Role> roles;

	private HashSet<BasedCalculating> specifiedBaseCalcs = new HashSet<BasedCalculating>();

	private HashSet<AllocationOrgan> allocationOrgans = new HashSet<AllocationOrgan>();

	private AllocationOrgan allocation;

	private Component ui;

	private Action action;

	// one to many
	private BasedCalculating basedCalc;

	private SpecifiedAction specifiedAction;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		basedCalc = new BasedCalculating();

		setActions(actionService.getAll());

		specifiedAction = new SpecifiedAction();

		action = (Action) Executions.getCurrent().getArg().get("action");
		specifiedAction.setAction(action);

		fonts = fontService.getAll();

		superRubrics = superRubricService.getAll();

		// subRubrics = subRubricService.getAll();

		specifiedBaseCalcs.addAll(specifiedAction.getBasedCalcs());

		organs = organService.getFilteredOrgans(loggedOrgan);

		roles = roleService.getAll();

		allocation = new AllocationOrgan();

		specifiedActions = action.getSpecifiedActions();
		subRubric = new SubRubric();

	}

	@NotifyChange("*")
	@Command
	public void showDetail(@BindingParam("btn") Button btn,
			@BindingParam("tr") String trId) {

		Tbody list = (Tbody) ui.getFellow("list");

		Tr tr = (Tr) ui.getFellow(trId);

		if (btn.getImage().contains("details_close")) {

			btn.setImage("images/details_open.png");

		} else {

			btn.setImage("images/details_close.png");
			list.insertBefore(drawnTable(), tr);
		}
	}

	// @NotifyChange({"basedCalc", "code"})
	// @Command
	// public void selectFont(@BindingParam("font_id") Long id) {
	// Font f = fontService.find(id);
	// code = f.getCode();
	//
	// }

	// @Command
	// public void selectFont(@BindingParam("font_id") Long id) {
	//
	// Label lbl = (Label) ui.getFellow("desc");
	//
	// Font f = fontService.find(id);
	//
	// lbl.setVisible(true);
	//
	// lbl.setValue(f.getCode());
	// }

	@NotifyChange({ "basedCalc", "specifiedBaseCalcs", "action" })
	@Command
	public void addCalBase() {

		basedCalc.setCost(basedCalc.getQuantity() * basedCalc.getUnitCost());
		basedCalc.setBarCode(basedCalc.getBarCode());
		specifiedBaseCalcs.add(basedCalc);
		log("Registou nova Base Calculo: " + basedCalc.getDesignation());
		Clients.showNotification("Base calculo adicionada com sucesso!",
				"info", ui.getFellow("tblBaseCal"), "before_center", -1);

		basedCalc = new BasedCalculating();
		ui.getFellow("tblBaseCal").invalidate();
	}

	@NotifyChange({ "superRubrics", "rubrics" })
	@Command
	public void onSelectSuperRubrica(@BindingParam("superRubric_id") Long id) {
		SuperRubric sr = superRubricService.find(id);
		rubrics = rubricService.getRubricBySuperRubric(sr);
	}

	@NotifyChange({ "rubrics", "subRubrics" })
	@Command
	public void onSelectRubrica(@BindingParam("rubric_id") Long id) {
		Rubric r = rubricService.find(id);
		subRubrics = subRubricService.getSubRubricByRubric(r);
	}

	@NotifyChange({ "basedCalc", "specifiedBaseCalcs", "action" })
	@Command
	public void removeBasedCals(
			@BindingParam("baseCalc") BasedCalculating baseCalc) {

		specifiedBaseCalcs.contains(baseCalc);

		specifiedBaseCalcs.remove(basedCalc);

		Clients.showNotification("Base de c�lculo removida com sucesso!",
				"info", ui, "before_center", -1);

		refreshDataTable(null);
	}

	@NotifyChange({ "allocation", "allocationOrgans" })
	@Command
	public void addOtherInterviews() {

		allocationOrgans.add(allocation);

		allocation = new AllocationOrgan();

		ui.getFellow("tblOtherItw").invalidate();

		Clients.showNotification("Alocação adicionada com sucesso!", "info",
				ui.getFellow("tblOtherItw"), "before_center", -1);
	}

	@NotifyChange({ "basedCalc", "allocation", "specifiedBaseCalcs",
			"allocationOrgans", "specifiedAction", "specifiedActions" })
	@Command
	public void save() {

		specifiedAction.getBasedCalcs().addAll(specifiedBaseCalcs);

		specifiedAction.getAllocations().addAll(allocationOrgans);

		specifiedActionService.create(specifiedAction);

		Clients.showNotification(
				"Actividade Específica #" + specifiedAction.getId()
						+ " registada com sucesso!", "info", ui,
				"before_center", -1);

		allocationOrgans.clear();
		specifiedBaseCalcs.clear();

		specifiedAction = new SpecifiedAction();
		specifiedAction.setAction(action);

		specifiedActions = actionService.find(action.getId())
				.getSpecifiedActions();

		refreshDataTable(null);
	}

	@Command
	public void limitEndDate(@BindingParam("begin") Date begin,
			@BindingParam("end") String end) {

		Datebox dbxEnd = (Datebox) ui.getFellow("dbxEnd");

		dbxEnd.setDisabled(false);

		dbxEnd.setRawValue(null);

		dbxEnd.setConstraint("between " + format(begin) + " and " + end
				+ ", no empty");
	}

	private Tr drawnTable() {

		Tr tr = new Tr();

		Td td = new Td();
		td.setSclass("details");
		td.setDynamicProperty("colspan", 5);
		td.setParent(tr);

		Table table = new Table();
		table.setDynamicProperty("cellpadding", 5);
		table.setDynamicProperty("cellspacing", 0);
		table.setDynamicProperty("border", 0);
		table.setStyle("padding-left:50px;");
		table.setParent(td);

		Tr tr01 = new Tr();

		Td td01 = new Td();
		Text txt01 = new Text("Texto");
		txt01.setParent(td01);
		td01.setParent(tr01);

		tr01.setParent(table);

		return tr;
	}

	public Component getUi() {
		return ui;
	}

	public void setUi(Component ui) {
		this.ui = ui;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public SpecifiedAction getSpecifiedAction() {
		return specifiedAction;
	}

	public void setSpecifiedAction(SpecifiedAction specifiedAction) {
		this.specifiedAction = specifiedAction;
	}

	public List<SpecifiedAction> getSpecifiedActions() {
		return specifiedActions;
	}

	public void setSpecifiedActions(List<SpecifiedAction> specifiedActions) {
		this.specifiedActions = specifiedActions;
	}

	public BasedCalculating getBasedCalc() {
		return basedCalc;
	}

	public void setBc(BasedCalculating basedCalc) {
		this.basedCalc = basedCalc;
	}

	public void setBasedCalc(BasedCalculating basedCalc) {
		this.basedCalc = basedCalc;
	}

	public HashSet<BasedCalculating> getSpecifiedBaseCalcs() {
		return specifiedBaseCalcs;
	}

	public void setSpecifiedBaseCalcs(
			HashSet<BasedCalculating> specifiedBaseCalcs) {
		this.specifiedBaseCalcs = specifiedBaseCalcs;
	}

	public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	public HashSet<AllocationOrgan> getAllocationOrgans() {
		return allocationOrgans;
	}

	public void setAllocationOrgans(HashSet<AllocationOrgan> allocationOrgans) {
		this.allocationOrgans = allocationOrgans;
	}

	public AllocationOrgan getAllocation() {
		return allocation;
	}

	public void setAllocation(AllocationOrgan allocation) {
		this.allocation = allocation;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<SuperRubric> getSuperRubrics() {
		return superRubrics;
	}

	public void setSuperRubrics(List<SuperRubric> superRubrics) {
		this.superRubrics = superRubrics;
	}

	public List<Rubric> getRubrics() {
		return rubrics;
	}

	public void setRubrics(List<Rubric> rubrics) {
		this.rubrics = rubrics;
	}

	public List<SubRubric> getSubRubrics() {
		return subRubrics;
	}

	public void setSubRubrics(List<SubRubric> subRubrics) {
		this.subRubrics = subRubrics;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Textbox getTbx_codigo() {
		return tbx_codigo;
	}

	public void setTbx_codigo(Textbox tbx_codigo) {
		this.tbx_codigo = tbx_codigo;
	}

}
