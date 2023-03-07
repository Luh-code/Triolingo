package ui.cli;

public class DisplayWidget extends ConsoleWidget {
	private Console console;

	public DisplayWidget(Console console, String prompt) {
		super(console, String.format("%s\n\nEnter to continue...", prompt));
		this.console = console;
	}

	@Override
	public void late() {
		console.readLine();
	}
}
