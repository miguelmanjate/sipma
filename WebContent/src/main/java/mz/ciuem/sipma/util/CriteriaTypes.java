package mz.ciuem.sipma.util;

public enum CriteriaTypes {

	Criterio1("Criterio 1", "Impacto no desempenho da unidade orgânica"), Criterio2(
			"Criterio 2", "Capacidade e recursos"), Criterio3("Criterio 3",
			"Satisfação dos grupos de Interesse");

	final String value;

	final String description;

	CriteriaTypes(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
