package ui.cli;

public abstract class ChoiceDialouge implements IConsoleInterface {
    private String error = "";
    private Option[] options;

    private boolean clear = true;
    private int extraClearingLines;

    public ChoiceDialouge(Option[] options, boolean clear, int extraClearingLines) {
        this.options = options;
        this.clear = clear;
        this.extraClearingLines = extraClearingLines;
    }

    @Override
    final public void execute(Console console) {
        for (int i = 0; i < options.length; i++)
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
        options[answer-1].execute();
        if (clear) clearSc(extraClearingLines);
        late();
    }

    public void clearSc(int extra)
    {
        int lineAmt = options.length+3+extra;
        String movement = String.format("\033[%dA\033[#E", lineAmt);
        System.out.printf("%s\003[0J", movement);
        System.out.flush();
    }

    public

    public abstract void late();
}
