package ui.cli;

import java.util.function.Function;

public enum InputDataType {
	STRING("String"),
	INT("Integer"),
	DOUBLE("Double");

	private String type;

	InputDataType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
