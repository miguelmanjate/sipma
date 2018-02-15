package mz.ciuem.sipma.util;

public enum UserRolesUtil {

	SectorChief("SectorChief"),
	OrganChief("OrganChief");

	final String value;

	UserRolesUtil(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
