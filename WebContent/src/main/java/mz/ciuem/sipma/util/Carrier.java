package mz.ciuem.sipma.util;

public enum Carrier {
	Mcel("Mcel"), Vodacom("Vodacom"), Movitel("Movitel"), TDM("TDM");

	final String value;

	Carrier(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
