package mz.ciuem.sipma.util;

public enum PermissionsUtil {

	EMPLOYEE_EXECUTIONS("O utilizador poderÃ¡ ver as execuÃ§Ãµes dos funcionÃ¡rios."),
	MANAGE_ORGANS("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os OrgÃ£os, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_EMPLOYEES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os FuncionÃ¡rios, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_SECTORS("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Sectores, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_CRITICAL_AREAS("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Ã�reas de GestÃ£o, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	IS_SUPER_ADMIN("Super utilizador com acesso as Ã¡reas de administraÃ§Ã£o do sistema e outras configuraÃ§Ãµes crÃ­ticas do sistema."),
	MANAGE_ORGAN_ROLES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Papeis dos Ã³rgÃ£os intervenientes, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_CRITERIAS("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os CritÃ©rios de Actividade, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_SOURCES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Fontes, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_CYCLES("O utilizador poderÃ¡ criar, configurar os ciclos, adicionar fases e respectivos perÃ­odos de execuÃ§Ã£o."),
	MANAGE_ACTIONS("O utilizador poderÃ¡ registar actividades, actualizar."),
	SECTOR_ALLOCATIONS("O utilizador poderÃ¡, alocar actividades ao sector."),
	MANAGE_ORGAN_TYPES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Tipos de OrgÃ£os, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_SPECIFIED_ACTIONS("O utilizador poderÃ¡ registar, actualizar actividades especÃ­ficas."),
	MANAGE_PHASES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Fases, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	MANAGE_STRATEGIC_OBJECTIVES("O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Objectivos EstratÃ©gicos, criaÃ§Ã£o, actualizaÃ§Ã£o."),
	EMPLOYEE_ALLOCATIONS("O utilizador poderÃ¡ alocar actividades aos funcionÃ¡rios."),
	IS_EMPLOYEE("O utilizador poderÃ¡ autenticar no sistema e ver alocaÃ§Ãµes feitas a si pelo seu orgÃ£o ou sector."),
	PLANNING_OFFICE("Essa permissÃ£o assumi o utilizador como funcionÃ¡rio do Gabinete de PlanificaÃ§Ã£o, com acesso a visualizaÃ§Ã£o de todos planos de actividades"),
	MANEGE_ACCOUNT("O utilizador poderÃ¡ Realiza todas operacoes relacionadas com as Contas dos  OrgÃ£os"),
	MANEGE_ATTACHMENT("O utilizador poderÃ¡ Realiza todas operacoes relacionadas com as Contas dos  OrgÃ£os");


	final String value;

	PermissionsUtil(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
