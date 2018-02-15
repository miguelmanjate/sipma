package mz.ciuem.sipma.util;

public enum MaritalStatus {

	Casado("Casado"), 
	Solteiro("Solteiro"),
	Divorciado("Divorciado"),
	Viuvo("Viuvo"),
	UniaoCivil("União Civil");

	final String value;

	MaritalStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
