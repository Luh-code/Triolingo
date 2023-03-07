package ui.cli;

import java.awt.*;
import java.util.function.Consumer;

public class Menu implements IConsoleInterface {
	private String title;

	private Option[] options;

	private String error = "";

	private boolean exitAfterFinish;

	private boolean clear;

	private Consumer<Boolean> clearConsumer;

	public Menu(String title, Option[] options, boolean exitAfterFinish)
	{
		this.title = title;
		this.options = options;
	}

	@Override
	public void execute(Console console)
	{
		console.cls();
		clear = true;
		ChoiceDialouge cd = new ChoiceDialouge(options, true, 2) {
			@Override
			public void early()
			{
				System.out.printf("%s:\n\n", title);
			}
			@Override
			public void late() {

			}
		};
		clearConsumer = e -> cd.disableClear();
		do { cd.execute(console); } while(!cd.getError().equals(""));
	}

	public void disableClear()
	{
		clear = false;
		clearConsumer.accept(clear);
	}
}
