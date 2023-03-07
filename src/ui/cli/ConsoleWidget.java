package ui.cli;

import java.util.Arrays;

public abstract class ConsoleWidget implements IConsoleInterface {
	private String prompt;
	private String error = "";

	private boolean killAfterUse;

	public ConsoleWidget(Console console,String prompt) {
		this.prompt = prompt;
		IConsoleInterface ici = console.peek();
		if (ici instanceof Menu) ((Menu) ici).disableClear();
	}

	private void makeRoom()
	{
		long linesRequired = prompt.chars()
			.filter(ch -> ch == '\n')
			.count()+1;
		String movement = String.format("\033[%dA\033[#E", linesRequired);
		System.out.printf("%s\033[0J%s", movement, "");
	}

	@Override
	final public void execute(Console console) {
		killAfterUse = true;
		prompt = String.format("\033[1;31;49m%s\033[1;39;49m%s%s", error, (error.length() != 0 ? "\n" : ""), prompt);
		makeRoom();
		System.out.flush();
		System.out.printf("\033[48;5;235m%s", prompt);
		System.out.flush();
		late();
		System.out.print("\033[1;39;49m");
		System.out.flush();
		if (killAfterUse) console.popStack();
	}

	public abstract void late();

	final protected void setError(String err)
	{
		error = err;
	}

	final public void keepAfterUse()
	{
		killAfterUse = false;
	}
}
