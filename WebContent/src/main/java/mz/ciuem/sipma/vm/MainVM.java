package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import mz.ciuem.sipma.entity.Notification;
import mz.ciuem.sipma.util.Breadcrumb;

public class MainVM extends AbstractVM {

	@Wire("#mainlayout")
	private Div target;

	@Wire
	private Div main;

	@Wire
	private Div breadcrumb;

	private List<String> links;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Painel Inicial");
		links.add("Meu Painel Inicial");
		Breadcrumb.drawn(breadcrumb, "Painel Inicial", links);
		
		
	}

	@Init(superclass = true)
	public void intialize() {

		loggeduser = userService.getUser(authentication.getName());

		if (loggeduser.getResponsible() != null) {
			notifications = notificationService.filterByEmployee(loggeduser.getResponsible());
		}

		if (notifications.size() > 3) {
			notifications = notifications.subList(0, 3);
		}

		logs = logService.filterByUser(loggeduser);
	}

	@Command
	public void home() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Painel Inicial");
		links.add("Meu Painel Inicial");
		Breadcrumb.drawn(breadcrumb, "Painel Inicial", links);
	}

	@Command
	public void registerCycle() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/cycle/pagRegisterCycle.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registo de Ciclo");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Criação de Ciclo", links);

	}

	@Command
	public void listCycle() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/cycle/pagListCycle.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Ciclos");
		links.add("Lista");
		Breadcrumb.drawn(breadcrumb, "Criação de Ciclo", links);
	}

	@Command
	public void profile() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagProfile.zul", target, map);

		links = new ArrayList<String>();
		links.add("Perfil");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void role() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRole.zul", target, map);

		links = new ArrayList<String>();
		links.add("Papeis");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void criteria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagCriteria.zul", target, map);

		links = new ArrayList<String>();
		links.add("Criterio de Priorizacao");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void criticalAreas() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagCriticalArea.zul", target, map);

		links = new ArrayList<String>();
		links.add("Area de Gestao");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void strategicObjectives() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagStrategicObjective.zul", target, map);

		links = new ArrayList<String>();
		links.add("Objectivos Estrategicos");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void registerOrgan() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterOrgan.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Orgão");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void registerOrganType() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterOrganType.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Tipo de Orgão");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void integAction() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/pagActionIntegration.zul", target, map);

		links = new ArrayList<String>();
		links.add("Integrar Acção");
		Breadcrumb.drawn(breadcrumb, "Alocação de Actividades", links);
	}

	@Command
	public void executions() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/execution/pagMenuExecutions.zul", target, map);

		links = new ArrayList<String>();
		links.add("Execuções");
		Breadcrumb.drawn(breadcrumb, "Alocação de Actividades", links);
	}

	@Command
	public void allocationToSector() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/pagAllocationToSector.zul", target, map);

		links = new ArrayList<String>();
		links.add("Alocar ao Sector");
		Breadcrumb.drawn(breadcrumb, "Alocação de Actividades", links);
	}

	@Command
	public void allocationToEmployee() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/pagAllocationToEmployee.zul", target, map);

		links = new ArrayList<String>();
		links.add("Alocar ao Funcionário");
		Breadcrumb.drawn(breadcrumb, "Alocação de Actividades", links);
	}

	@Command
	public void phaseMonitor() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitor/pagPhaseMonitor.zul", target, map);

		links = new ArrayList<String>();
		links.add("Monitorar Fase");
		links.add("Monitoração");
		Breadcrumb.drawn(breadcrumb, "Monitorar", links);
	}

	@Command
	public void activitiesMonitor() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitor/pagActivitiesMonitor.zul", target, map);

		links = new ArrayList<String>();
		links.add("Monitorar Actividade");
		links.add("Monitoração");
		Breadcrumb.drawn(breadcrumb, "Monitorar", links);
	}

	@PreAuthorize("hasRole('Orgao')")
	@Command
	public void registerAction() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagRegisterAction.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Acção");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);
	}

	@Command
	public void registerSpecifAction() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagRegisterSpecificAction.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Acção Específica");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);
	}

	@Command
	public void registerUser() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterUser.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Utilizadores");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void userProfile() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/user/pagUserProfile.zul", target, map);

		links = new ArrayList<String>();
		links.add("Perfil");
		links.add("Sobre");
		Breadcrumb.drawn(breadcrumb, "Utilizador", links);
	}

	@Command
	public void phases() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagPhases.zul", target, map);

		links = new ArrayList<String>();
		links.add("Fases");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void lookAllPlan() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitor/pagOllOrganPlan.zul", target, map);

		links = new ArrayList<String>();
		links.add("Fases");
		links.add("Nova");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void registerSector() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/sector/pagSectors.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Sector");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void registerSubSector() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/sector/pagSubSectores.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Sub-Sector");
		links.add("Novo");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void showEmployees() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/employees/pagShowEmployees.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Funcionários");
		links.add("Todos");
		Breadcrumb.drawn(breadcrumb, "Funcionários", links);
	}

	@Command
	public void newEmployee() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/employees/pagRegisterEmployee.zul", target, map);

		links = new ArrayList<String>();
		links.add("Novo Funcionário");
		Breadcrumb.drawn(breadcrumb, "Funcionários", links);
	}

	@Command
	public void font() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagFonts.zul", target, map);

		links = new ArrayList<String>();
		links.add("Fontes");
		Breadcrumb.drawn(breadcrumb, "Parametrização", links);
	}

	@Command
	public void permissions() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/admin/permissions.zul", target, map);

		links = new ArrayList<String>();
		links.add("Permissões");
		Breadcrumb.drawn(breadcrumb, "Administração", links);
	}

	@Command
	public void userRoles() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/admin/user_roles.zul", target, map);

		links = new ArrayList<String>();
		links.add("Perfis de Utilizadores");
		Breadcrumb.drawn(breadcrumb, "Administração", links);
	}

	@Command
	public void adminUsers() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/admin/admin_users.zul", target, map);

		links = new ArrayList<String>();
		links.add("Utilizadores");
		Breadcrumb.drawn(breadcrumb, "Administração", links);
	}

	@Command
	public void employeeExecution() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/execution/pagEmployeeExecution.zul", target, map);

		links = new ArrayList<String>();
		links.add("Execução do Funcionário");
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	@Command
	public void registerCellPhone() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/contact_point/pagAssociateCellPhone.zul", target, map);

		links = new ArrayList<String>();
		links.add("Associar Contactos de Telefone");
		Breadcrumb.drawn(breadcrumb, "Contactos de Telefone", links);
	}

	@Command
	public void organExecution() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/execution/pagOrganExecution.zul", target, map);

		links = new ArrayList<String>();
		links.add("Execução do Orgão");
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	@Command
	public void sectorChiefExecution() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/execution/pagSectorChiefExecution.zul", target, map);

		links = new ArrayList<String>();
		links.add("Execução do Chefe do Sector");
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	@Command
	public void reportExecution() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/allocation_activity/execution/pagReportRealizationExecution.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Execuções");
		links.add("Reportar");
		Breadcrumb.drawn(breadcrumb, "Execuções", links);
	}

	@Command
	public void delegateEmployees() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/delegations/pagEmployeeDelegations.zul", target, map);

		links = new ArrayList<String>();
		links.add("Delegações");
		links.add("Funcionários");
		Breadcrumb.drawn(breadcrumb, "Delegações", links);
	}

	@Command
	public void notifications() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/notifications/pagNotifications.zul", target, map);

		links = new ArrayList<String>();
		links.add("Notificações");
		links.add("Mensagens");
		Breadcrumb.drawn(breadcrumb, "Personalização", links);
	}

	@Command
	public void allNotifications() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/notification/notifications.zul", target, map);

		links = new ArrayList<String>();
		links.add("Notificações");
		links.add("Relatório");
		Breadcrumb.drawn(breadcrumb, "Notificações", links);
	}

	@NotifyChange("teachingStaff")
	@Command
	public void teachingStaff() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/teachingStaff.zul", target, map);

		links = new ArrayList<String>();
		links.add("Estatisticas");
		links.add("Corpo Docente");
		Breadcrumb.drawn(breadcrumb, "Notificações", links);
	}

	@NotifyChange("enrollment")
	@Command
	public void enrollment() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/enrollment.zul", target, map);

		links = new ArrayList<String>();
		links.add("Estatisticas");
		links.add("Mattriculas");
		Breadcrumb.drawn(breadcrumb, "Notificações", links);
	}

	@NotifyChange("receptions")
	@Command
	public void receptions() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/receptions.zul", target, map);

		links = new ArrayList<String>();
		links.add("Estatisticas");
		links.add("Admissões");
		Breadcrumb.drawn(breadcrumb, "Notificações", links);
	}

	@NotifyChange("planning")
	@Command
	public void planning() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/planning.zul", target, map);

		links = new ArrayList<String>();
		links.add("Estatisticas");
		links.add("Planificacao");
		Breadcrumb.drawn(breadcrumb, "Notificações", links);
	}

	@NotifyChange("showContentReportEnrollment")
	@Command
	public void showContentReportEnrollment(@BindingParam("reportId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/reports/PagEnrollment.zul", target, map);

	}

	@NotifyChange("showContentReportReceptions")
	@Command
	public void showContentReportReceptions(@BindingParam("reportId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/reports/pagReceptions.zul", target, map);

	}

	@NotifyChange("showContentReportTeachingStaff")
	@Command
	public void showContentReportTeachingStaff(@BindingParam("reportId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/reports/PagTeachingStaff.zul", target, map);

	}

	@NotifyChange("showContentReportPlanning")
	@Command
	public void showContentReportPlanning(@BindingParam("reportId") Long cycleId, @BindingParam("tgt") Div target,
			@BindingParam("bc") Div breadcrumb) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		target.getChildren().clear();
		Executions.createComponents("statisticsReports/reports/pagPlanning.zul", target, map);

	}

	@Command
	public void helpdesk() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/helpdesk/pagIndex.zul", target, map);

		links = new ArrayList<String>();
		links.add("Help Desk");
		links.add("Suporte");
		Breadcrumb.drawn(breadcrumb, "Suporte", links);

	}

	@NotifyChange("notifications")
	@Command
	public void watched(@BindingParam("notificationId") Long notificationId, @BindingParam("target") Div target) {
		Notification n = notificationService.find(notificationId);
		n.setWatched(true);
		notificationService.update(n);

		notifications = notificationService.getAll();

		refreshDataTable(target);
	}

	@Command
	public void planningActions() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/calendar/planning-actions.zul", target, map);

		links = new ArrayList<String>();
		links.add("Actividades");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Plano de Actividades", links);
	}

	@Command
	public void pagAssociateContactPoint() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/contact_point/pagListEmail.zul", target, map);

		links = new ArrayList<String>();
		links.add("Pontos de Contacto");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Pontos de Contacto", links);
	}

	@Command
	public void newContactPoint() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/contact_point/pagAssociateEmail.zul", target, map);

		links = new ArrayList<String>();
		links.add("Pontos de Contacto");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Pontos de Contacto", links);
	}

	@Command
	public void profession() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagProfession.zul", target, map);

		links = new ArrayList<String>();
		links.add("Profissao");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Profissao", links);
	}

	@Command
	public void listAction() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagListAction.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Actividades");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);
	}

	@Command
	public void listActionBudget() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/activity/pagListBudget.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Actividades");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Actividade", links);
	}

	@Command
	public void detailCycle() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/cycle/pagDetailCycles.zul", target, map);

		links = new ArrayList<String>();
		links.add("Detalhes do Ciclo");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Ciclo", links);
	}

	@Command
	public void showDetails() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitor/pagMonitoringActiosByOrgan.zul", target, map);

		links = new ArrayList<String>();
		links.add("Detalhes do Ciclo");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Ciclo", links);
	}

	@Command
	public void showBudget() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitor/pagMonitorigBadget.zul", target, map);

		links = new ArrayList<String>();
		links.add("Detalhes do Ciclo");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Ciclo", links);
	}

	@Command
	public void registerBank() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterBank.zul", target, map);
		links = new ArrayList<String>();
		links.add("Listar Bancos");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Bancos", links);
	}

	@Command
	public void registerCoin() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterCoin.zul", target, map);
		links = new ArrayList<String>();
		links.add("Listar Bancos");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Bancos", links);
	}

	@Command
	public void accountType() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterAccountType.zul", target, map);
		links = new ArrayList<String>();
		links.add("Listar Tipo de Contas");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Tipo de Conta", links);
	}

	@Command
	public void registerConter() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterCounter.zul", target, map);
		links = new ArrayList<String>();
		links.add("Balcao");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Balcao", links);
	}

	@Command
	public void registerPurpose() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterPurpouse.zul", target, map);
		links = new ArrayList<String>();
		links.add("Finalidade");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Finalidade", links);
	}
	
	@Command
	public void registerSubScriber() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterSubScriber.zul", target, map);
		links = new ArrayList<String>();
		links.add("Assinante");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Assinante", links);
	}
	
	@Command
	public void registerAccount() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagRegisterAccount.zul", target, map);
		links = new ArrayList<String>();
		links.add("Conta");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Conta", links);
	}
	@Command
	public void detailAccount() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("accounts/pagDetailAccount.zul", target, map);
		links = new ArrayList<String>();
		links.add("Documento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Documento", links);
	}
	
	@Command
	public void registerAttachment() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/docments/pagRegisterAttachment.zul", target, map);
		links = new ArrayList<String>();
		links.add("Documento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Documento", links);
	}
	@Command
	public void listDocument() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/docments/pagAlocActivity.zul", target, map);
		links = new ArrayList<String>();
		links.add("Documento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Documento", links);
	}
	
	@Command
	public void registerAttachmentType() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/docments/pagRegisterAttachmentType.zul", target, map);
		links = new ArrayList<String>();
		links.add("Documento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Documento", links);
	}
	
	@Command
	public void alocActivity() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/docments/detailAlocation.zul", target, map);
		links = new ArrayList<String>();
		links.add("Documento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Documento", links);
	}
	
	@Command
	public void registerProjectInvestiment() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterProjectInvestiment.zul", target, map);
		links = new ArrayList<String>();
		links.add("Projecto de Investimento");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Projecto de Investimento", links);
	}
	
	@Command
	public void registerCategoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterCategoria.zul", target, map);
		links = new ArrayList<String>();
		links.add("Categoria");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Categoria", links);
	}
	
	@Command
	public void registerSuperRubric() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterSuperRubric.zul", target, map);
		links = new ArrayList<String>();
		links.add("Super Rubrica");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Super Rubrica", links);
	}
	
	@Command
	public void registerRubric() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterRubric.zul", target, map);
		links = new ArrayList<String>();
		links.add("Grande R�brica");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "Grande R�brica", links);
	}
	
	@Command
	public void registerSubRubric() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parameterization/pagRegisterSubRubric.zul", target, map);
		links = new ArrayList<String>();
		links.add("R�brica");
		links.add("Todas");
		Breadcrumb.drawn(breadcrumb, "R�brica", links);
	}

	@Command
	public void logout() {

		Executions.sendRedirect("login.zul");
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
}
