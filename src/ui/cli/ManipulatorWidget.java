package ui.cli;

import java.util.function.Consumer;

public class ManipulatorWidget extends ConsoleWidget {

	private Consumer<String> s;
	private Consumer<Integer> i;
	private Consumer<Double> d;

	private InputDataType type;

	Console console;

	private <T> ManipulatorWidget(Console console, String prompt, InputDataType type, Consumer<T> value) {
		super(console, String.format("%s\n\nPlease enter value(%s): ", prompt, type.getType()));
		this.type = type;
		this.console = console;
		if (type.getType().equals("String"))
		{
			s = (Consumer<String>) value;
		}
		else if (type.getType().equals("Integer"))
		{
			i = (Consumer<Integer>) value;
		}
		else if (type.getType().equals("Double"))
		{
			d = (Consumer<Double>) value;
		}
	}

	@Override
	public void late()
	{
		if (type.getType().equals("String"))
		{
			s.accept(console.readLine());
		}
		else if (type.getType().equals("Integer"))
		{
			String literalAnswer = console.readLine();
			int answer = 0;
			try {
				answer = Integer.parseInt(literalAnswer);
			} catch (NumberFormatException e)
			{
				setError("Answer must only contain numbers!");
				keepAfterUse();
				return;
			}
			i.accept(answer);
		}
		else if (type.getType().equals("Double"))
		{
			String literalAnswer = console.readLine();
			double answer = 0.0;
			try {
				answer = Double.parseDouble(literalAnswer);
			} catch (NumberFormatException e)
			{
				setError("Answer must only contain numbers!");
				keepAfterUse();
				return;
			}
			d.accept(answer);
		}
	}

	public static ManipulatorWidget stringManipulatorGenerator(Console console, String prompt, Consumer<String> value)
	{
		return new ManipulatorWidget(console, prompt, InputDataType.STRING, value);
	}
	public static ManipulatorWidget integerManipulatorGenerator(Console console, String prompt, Consumer<Integer> value)
	{
		return new ManipulatorWidget(console, prompt, InputDataType.INT, value);
	}
	public static ManipulatorWidget doubleManipulatorGenerator(Console console, String prompt, Consumer<Double> value)
	{
		return new ManipulatorWidget(console, prompt, InputDataType.DOUBLE, value);
	}
}
