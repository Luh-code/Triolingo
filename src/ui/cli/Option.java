package ui.cli;

import java.util.function.Function;

public abstract class Option implements Interactible {
	private String text;

	public Option(String text) {
		this.text = text;
	}

	final public String getText() {
		return text;
	}
}
