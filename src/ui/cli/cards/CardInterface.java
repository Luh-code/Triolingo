package ui.cli.cards;

import app.cards.Card;
import app.cards.CardApp;
import data.ResourceManager;
import ui.cli.*;

import java.awt.*;

public class CardInterface implements IConsoleInterface {

	private Card c;

	private boolean killAfterUse = true;

	public CardInterface(Card c, boolean killAfterUse)
	{
		this.c = c;
		this.killAfterUse = killAfterUse;
	}

	@Override
	public void execute(Console console) {
		console.cls();
		System.out.printf("Word: %s\n\nPress enter to reveal translation...", c.getWord());
		System.out.flush();
		console.readLine();
		String movement = String.format("\033[%dA\033[#E", 1);
		System.out.printf("%s\033[0J%s\n", movement, String.format("Translation: %s", c.getTranslation()));
		System.out.flush();

		ResourceManager rm = console.getResourceManager();
		CardApp ca = rm.getResource("CardApp", CardApp.class);
		ChoiceDialouge cd = new ChoiceDialouge(new Option[] {
				new Option("Correct") {
					@Override
					public void execute() {
						ca.translationCorrect();
					}
				},
				new Option("Incorrect") {
					@Override
					public void execute() {
						ca.translationWrong();
					}
				}
		}, true, 0) {
			@Override
			public void late() {

			}
		};
		do { cd.execute(console); } while(!cd.getError().equals(""));

		Console.cls();
		if (killAfterUse) console.popStack();
	}
}
