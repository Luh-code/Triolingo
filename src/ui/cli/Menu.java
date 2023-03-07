package ui.cli;

import java.awt.*;

public class Menu implements IConsoleInterface {
	private String title;

	private Option[] options;

	private String error = "";

	private boolean exitAfterFinish;

	private boolean clear;

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
		System.out.printf("\033[1;31;49m%s\033[1;39;49m\n", error);
		System.out.flush();
		error = "";
		System.out.printf("%s:\n\n", title);
		/*for (int i = 0; i < options.length; i++)
		{
			System.out.printf("%d. %s\n", i+1, options[i].getText());
		}
		System.out.println();
		System.out.printf("Selection (1-%d): ", options.length);
		String literalAnswer = console.readLine();
		int answer = 0;
		try {
			answer = Integer.parseInt(literalAnswer);
		} catch (NumberFormatException e)
		{
			error = "Answer must only contain numbers!";
			return;
		}
		if (answer < 1 || answer > options.length)
		{
			error = "Answer must be in range!";
			return;
		}
		options[answer-1].execute();*/
		ChoiceDialouge cd = new ChoiceDialouge(options, true, 2) {
			@Override
			public void late() {

			}
		};
		cd.execute(console);
		//if (clear) console.cls();
		//if (exitAfterFinish) console.popStack();
	}

	public void disableClear()
	{
		clear = false;
	}
}
