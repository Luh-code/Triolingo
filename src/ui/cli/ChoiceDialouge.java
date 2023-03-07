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

    final public void disableClear()
    {
        clear = false;
    }

    @Override
    final public void execute(Console console) {
        System.out.printf("\033[1;31;49m%s\033[1;39;49m\n", error);
        System.out.flush();
        error = "";
        early();
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
            if (clear) clearSc(extraClearingLines);
            return;
        }
        if (answer < 1 || answer > options.length)
        {
            error = "Answer must be in range!";
            if (clear) clearSc(extraClearingLines);
            return;
        }
        options[answer-1].execute();
        if (clear) clearSc(extraClearingLines);
        late();
    }

    public void clearSc(int extra)
    {
        int lineAmt = options.length+3+extra;
        String movement = String.format("\033[%dA\033[#E\033[0J", lineAmt);
        System.out.print(movement);
        System.out.flush();
    }

    final public String getError() {
        return error;
    }

    public void early()
    {

    }

    public abstract void late();
}
