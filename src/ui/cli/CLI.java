package ui.cli;

import data.ResourceManager;

public class CLI {
	public static void main(String[] args) {
		ResourceManager resourceManager = new ResourceManager();
		Console console = new Console(resourceManager, e -> new MainMenu(e));
		console.start();
	}
}
