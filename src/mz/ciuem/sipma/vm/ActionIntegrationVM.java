package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Window;

import mz.ciuem.sipma.entity.AllocationOrgan;
import mz.ciuem.sipma.entity.Comment;
import mz.ciuem.sipma.service.AllocationOrganService;
import mz.ciuem.sipma.service.CommentService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ActionIntegrationVM extends AbstractVM {

	@WireVariable
	private AllocationOrganService allocationOrganService;

	@WireVariable
	private CommentService commentService;

	private List<AllocationOrgan> allocations;

	private AllocationOrgan selected;

	private Comment comment;

	private Component ui;

	boolean acept;

	@Wire
	private Checkbox checkBoxYes;

	@Wire
	private Checkbox checkBoxNo;

	private Window modal;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		allocations = allocationOrganService.findAllNotIntegrated(loggedOrgan);

		comment = new Comment();

	}

	@NotifyChange("comment")
	@Command
	public void comment(@BindingParam("alloc") Long allocId) {

		selected = allocationOrganService.find(allocId);

		if (selected.isSeen()) {

			comment = selected.getComment();

			final HashMap<String, Object> map = new HashMap<String, Object>();
			modal = (Window) Executions.createComponents("views/allocation_activity/modalCommentActInteg.zul", ui, map);
			modal.doModal();

		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			modal = (Window) Executions.createComponents("views/allocation_activity/modalCommentActInteg.zul", ui, map);
			modal.doModal();

		}

	}

	@NotifyChange("allocations")
	@Command
	public void commentAllocation() {

		selected.setState(acept);

		if (selected.isSeen()) {
			commentService.update(comment);
		} else {
			commentService.create(comment);
		}

		selected.setSeen(true);

		selected.setComment(comment);

		allocationOrganService.update(selected);
		allocations = allocationOrganService.findAllNotIntegrated(loggedOrgan);

		log("Integrou a Acção: " + selected.getTaskAlso());
		Clients.showNotification("Acção integrada com sucesso", "info", ui, "before_center", -1);

		modal.detach();
		refreshDataTable(null);
	}

	@Command
	public void checkAYesllocation(@BindingParam("allocation") AllocationOrgan allocation,
			@BindingParam("state") boolean state) {

		acept = true;
		allocation.setState(state);
		Button btn = (Button) ui.getFellow("alloc" + allocation.getId());

		if (allocation.isState()) {
			btn.setDisabled(false);
		} else {
			btn.setDisabled(true);
		}

	}

	@Command
	public void checkNoAllocation(@BindingParam("allocation") AllocationOrgan allocation,
			@BindingParam("state") boolean state) {

		acept = false;
		allocation.setState(state);
		Button btn = (Button) ui.getFellow("alloc" + allocation.getId());
		if (allocation.isState()) {
			btn.setDisabled(false);
		} else {
			btn.setDisabled(true);
		}

	}

	public List<AllocationOrgan> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOrgan> allocations) {
		this.allocations = allocations;
	}

	public AllocationOrgan getSelected() {
		return selected;
	}

	public void setSelected(AllocationOrgan selected) {
		this.selected = selected;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
