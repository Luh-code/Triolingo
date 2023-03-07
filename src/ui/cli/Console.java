package ui.cli;

import data.ResourceManager;

import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

public class Console {
	private Stack<IConsoleInterface> interfaceStack = new Stack<>();
	private Scanner sc = new Scanner(System.in);

	ResourceManager resourceManager;

	public int test = 4;

	public String readLine()
	{
		return sc.nextLine();
	}

	public static void cls()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public Console(ResourceManager resourceManager, Function<Console, IConsoleInterface> beginningInterface) {
		this.resourceManager = resourceManager;
		pushStack(beginningInterface);
	}

	public void pushStack(Function<Console, IConsoleInterface> ici)
	{
		interfaceStack.push(ici.apply(this));
	}
	public void popStack()
	{
		interfaceStack.pop();
	}

	public IConsoleInterface peek()
	{
		return interfaceStack.peek();
	}

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void start()
	{
		while (interfaceStack.size() > 0) interfaceStack.peek().execute(this);
	}

	public void close()
	{
		sc.close();
	}
}
