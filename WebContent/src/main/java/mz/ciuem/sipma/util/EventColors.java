package mz.ciuem.sipma.util;

public enum EventColors {

	Blue("Blue"), Red("Red"), Orange("Orange"), Brown("Brown"), Yellow(
			"Yellow"), Green("Green"), Purple("Purple"), Teal("Teal"), Olive(
			"Olive"), Silver("Silver");

	final String value;

	EventColors(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
