package mz.ciuem.sipma.util;

public enum Nature {

	Nova("Nova"), EmCurso("Em Curso");

	final String value;

	Nature(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
