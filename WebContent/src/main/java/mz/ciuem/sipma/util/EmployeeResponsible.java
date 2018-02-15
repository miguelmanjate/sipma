package mz.ciuem.sipma.util;

public enum EmployeeResponsible {

	Organ("Orgao"), Sector("Sector"), None("Nenhuma");

	final String value;

	EmployeeResponsible(String value) {
		this.value = value;
	}

	public static String valueOf(int i) {

		if (i == 1)
			return Organ.value;
		else if (i == 2)
			return Sector.value;
		else if (i == 3)
			return None.value;

		return null;
	}
}
