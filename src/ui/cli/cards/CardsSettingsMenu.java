package ui.cli.cards;

import ui.cli.*;

public class CardsSettingsMenu extends Menu {
	public CardsSettingsMenu(Console console) {
		super("Cards Settings", new Option[]{
			new Option("Card Amount") {
				@Override
				public void execute() {
					console.pushStack(e -> new DisplayWidget(e, String.format("The value of test is: %d", e.test)));
					//System.out.println(console.test);
				}
			},
			new Option("Change Card Amount") {
				@Override
				public void execute() {
					console.pushStack(e -> ManipulatorWidget.integerManipulatorGenerator(e, "Please enter the amount of Cards per training session", f -> {e.test = f;}));
				}
			},
			new Option("Back") {
				@Override
				public void execute() {
					console.popStack();
				}
			}
		}, false);
	}
}
