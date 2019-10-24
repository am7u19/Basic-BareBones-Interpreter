import java.util.ArrayList;

public class Clear {

    /**
     * Method that clears a variable (sets its value to 0)
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void clearVariable(String currentLine, ArrayList<String> variables,
                                     ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split("[ ;]")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 2 || !(currentWords.get(0).equals("clear"))) {
            System.out.println("Clear syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToClear = currentWords.get(1);
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToClear.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToClear); // if it doesn't, declares it and sets value to 0
            values.add(0);
        } else {
            values.set(i, 0); // if it exists, sets its value to 0
        }

        System.out.println(variables.get(i) + " = " + values.get(i));
    }
}
