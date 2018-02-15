package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.File;
import mz.ciuem.sipma.entity.IdEntity;
import mz.ciuem.sipma.entity.Log;
import mz.ciuem.sipma.entity.Notification;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.Sector;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.FileService;
import mz.ciuem.sipma.service.LogService;
import mz.ciuem.sipma.service.NotificationMsgService;
import mz.ciuem.sipma.service.NotificationService;
import mz.ciuem.sipma.service.UserService;
import mz.ciuem.sipma.util.FileManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Table;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AbstractVM {

	@Wire
	protected Button btnSave;

	@Wire
	protected Button btnEdit;

	@Wire
	protected Button btnCancel;

	@Wire
	protected Div divForm;

	@Wire("#dynamic-table")
	protected Table table;

	@Wire(".adv-table")
	protected Div advTable;

	protected Notification notification;

	@WireVariable
	protected UserService userService;

	@WireVariable
	private FileService fileService;

	@WireVariable
	protected NotificationMsgService notificationMsgService;

	@WireVariable
	protected NotificationService notificationService;
	protected List<Notification> notifications;

	@WireVariable
	protected LogService logService;
	protected List<Log> logs;

	protected Authentication authentication = SecurityContextHolder
			.getContext().getAuthentication();

	protected User loggeduser;
	protected Organ loggedOrgan;
	protected Sector loggedSector;

	private Locale pt = new Locale("pt", "pt");
	private SimpleDateFormat sdf = new SimpleDateFormat("dd, MMM yyyy", pt);
	private SimpleDateFormat hour = new SimpleDateFormat("HH:mm", pt);

	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", pt);

	protected List<File> filesList = new ArrayList<File>();

	protected File file;

	protected Component ui;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void intialize() {

		loggeduser = userService.getUser(authentication.getName());
		loggedSector = findAutenticatedSector();
		loggedOrgan = findAutenticatedOrgan();
	}

	@Command
	public void download(@BindingParam("attachId") Long id) {

		File resume = fileService.find(id);
		FileManager fileManager = new FileManager();
		Filedownload.save(fileManager.byteToFile(resume.getFileName(),
				resume.getFileType(), resume.getContent()));
	}

	@Command
	@NotifyChange("filesList")
	public void doUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {

		setFile(new File());
		FileManager fileManager = new FileManager();
		setFile(fileManager.uploadImage(ctx));

		filesList.add(file);
		ui.getFellow("tblList").invalidate();
		// refreshDataTable(null);

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Command
	@NotifyChange("filesList")
	public void onDelete(@BindingParam("currentFile") File curFile) {
		filesList.remove(curFile);
		ui.getFellow("tblList").invalidate();
	}

	protected void editButtons() {

		btnEdit.setDisabled(false);
		btnCancel.setDisabled(false);
		btnSave.setDisabled(true);
	}

	protected void cancelButtons() {

		btnEdit.setDisabled(true);
		btnCancel.setDisabled(true);
		btnSave.setDisabled(false);
	}

	public void refreshDataTable(Component ui) {

		if (ui == null) {
			advTable.invalidate();
			Clients.evalJavaScript("initDataTable()");
		} else {
			ui.invalidate();
			Clients.evalJavaScript("dataInit()");
		}
	}

	public Organ findAutenticatedOrgan() {

		if (loggedSector != null) {
			loggedOrgan = loggedSector.getOrgan();
		}
		return loggedOrgan;
	}

	public Sector findAutenticatedSector() {
		Employee currentEmployee = loggedEmployee();

		if (currentEmployee != null) {
			loggedSector = currentEmployee.getSector();
		}
		return loggedSector;
	}

	public Employee loggedEmployee() {
		return loggeduser.getResponsible();
	}

	public void log(String log) {

		Log logDb = new Log(userService.getUser(authentication.getName()), log);
		logService.create(logDb);
	}

	public void notify(Employee emp, String title, String desc) {

		notification = new Notification();
//		notification.setEmployee(emp);
		notification.setTitle(title);
		notification.setDescription(desc);
		notificationService.create(notification);

	}

	@SuppressWarnings("unused")
	private String parse(IdEntity obj, String desc) {

		String msg = desc;

		if (obj instanceof Cycle) {
			Cycle cycle = (Cycle) obj;

			msg = msg.replace("#{cycle_name}", cycle.toString());
			msg = msg.replace("#{cycle_begin}",
					sdf.format(cycle.getStartDate()));
			msg = msg.replace("#{cycle_end}", sdf.format(cycle.getEndDate()));
		}

		return msg;
	}

	public String format(Date date) {

		return f.format(date);
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public User getLoggeduser() {
		return loggeduser;
	}

	public Organ getLoggedOrgan() {
		return loggedOrgan;
	}

	public Sector getLoggedSector() {
		return loggedSector;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public SimpleDateFormat getHour() {
		return hour;
	}

	public void setHour(SimpleDateFormat hour) {
		this.hour = hour;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}

}
