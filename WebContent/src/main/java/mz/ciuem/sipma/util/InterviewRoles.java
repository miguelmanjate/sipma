package mz.ciuem.sipma.util;

public enum InterviewRoles {

	Primary("Primario"), Secundary("Secundario");

	final String value;

	InterviewRoles(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
