package mz.ciuem.sipma.util;

public enum Domain {

	gmail("gmail"), outlook("outlook");

	final String value;

	Domain(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
