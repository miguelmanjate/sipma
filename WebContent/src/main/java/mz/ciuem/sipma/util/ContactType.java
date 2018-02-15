package mz.ciuem.sipma.util;

public enum ContactType {
	Fixo("Fixo"), Movel("Movel");

	final String value;

	ContactType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
