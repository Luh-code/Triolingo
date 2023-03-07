package ui.cli;

import data.PlaceholderDataManager;
import ui.cli.cards.CardMenu;
import ui.cli.cards.CardsSettingsMenu;

public class MainMenu extends Menu {
	public MainMenu(Console console) {
		super("Main Menu", new Option[]{
			new Option("Card App") {
				@Override
				public void execute() {
					console.pushStack(e -> new CardMenu(e));
				}
			},
			new Option("Exit") {
				@Override
				public void execute() {
					console.popStack();
				}
			}
		}, false);
	}
}
