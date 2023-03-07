package ui.cli.cards;

import app.cards.CardApp;
import data.PlaceholderDataManager;
import data.ResourceManager;
import ui.cli.Console;
import ui.cli.Menu;
import ui.cli.Option;

public class CardMenu extends Menu {
	private PlaceholderDataManager phdm = new PlaceholderDataManager();

	public CardMenu(Console console) {
		super("Card Menu", new Option[]{
			new Option("Card Options") {
				@Override
				public void execute() {
					console.pushStack(e -> new CardsSettingsMenu(e));
				}
			},
			new Option("Back") {
				@Override
				public void execute() {
					console.popStack();
				}
			}
		}, false);
		ResourceManager rm = console.getResourceManager();
		if (rm.getResourceArray(CardApp.class) == null) rm.registerResourceType(CardApp.class);
		if (rm.getResource("CardApp", CardApp.class) == null) rm.setResource("CardApp", new CardApp(phdm.getTestDrawer()));
	}
}
