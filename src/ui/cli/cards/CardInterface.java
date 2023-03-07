package ui.cli.cards;

import app.cards.Card;
import ui.cli.CardsInterface;
import ui.cli.Console;
import ui.cli.IConsoleInterface;

public class CardInterface implements IConsoleInterface {

	private Card c;

	public CardInterface(Card c)
	{
		this.c = c;
	}

	@Override
	public void execute(Console console) {
		console.cls();
		System.out.printf("Word: %s\n\nPress enter to reveal translation...", c.getWord());
		console.readLine();
		String movement = String.format("\033[%dA\033[#E", 1);
		System.out.printf("%s\033[0J%s", movement, String.format("Translation: %s", c.getTranslation());

	}
}
