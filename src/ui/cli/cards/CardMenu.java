package ui.cli.cards;

import app.cards.CardApp;
import data.PlaceholderDataManager;
import data.ResourceManager;
import ui.cli.Console;
import ui.cli.Menu;
import ui.cli.Option;

import java.util.BitSet;

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
			new Option("Learn") {
				@Override
				public void execute() {
					ResourceManager rm = console.getResourceManager();
					CardApp ca = rm.getResource("CardApp", CardApp.class);

					BitSet activeBoxes = new BitSet();
					activeBoxes.set(0, 4, true);
					rm.getResource("CardApp", CardApp.class).init(rm.getResource("CardAmount", Integer.class), activeBoxes);

					while (ca.cardsLeft() > 0)
					{
						CardInterface ci = new CardInterface(ca.useCard(), false);
						ci.execute(console);
					}
					ca.sortCardsInBoxes();
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

		if (rm.getResourceArray(Integer.class) == null) rm.registerResourceType(Integer.class);
		if (rm.getResource("CardAmount", Integer.class) == null) rm.setResource("CardAmount", 10);
	}
}
