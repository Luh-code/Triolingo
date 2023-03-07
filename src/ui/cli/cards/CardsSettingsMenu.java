package ui.cli.cards;

import data.ResourceManager;
import ui.cli.*;

public class CardsSettingsMenu extends Menu {
	public CardsSettingsMenu(Console console) {
		super("Cards Settings", new Option[]{
			new Option("Card Amount") {
				@Override
				public void execute() {
					((Menu)console.peek()).disableClear();
					ResourceManager rm = console.getResourceManager();
					console.pushStack(e -> new DisplayWidget(e, String.format("The amount of cards to be learnt at a time is set to: %d", rm.getResource("CardAmount", Integer.class))));
				}
			},
			new Option("Change Card Amount") {
				@Override
				public void execute() {
					((Menu)console.peek()).disableClear();
					ResourceManager rm = console.getResourceManager();
					console.pushStack(e -> ManipulatorWidget.integerManipulatorGenerator(e, "Please enter the amount of Cards per training session", f -> rm.setResource("CardAmount", f)));
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
