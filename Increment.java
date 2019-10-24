import java.util.ArrayList;

public class Increment {

    /**
     * Method that increments a variable
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void incrementVariable(String currentLine, ArrayList<String> variables,
                                         ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split("[ ;]")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 2 || !(currentWords.get(0).equals("incr"))) {
            System.out.println("Increment syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToIncrement = currentWords.get(1);
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToIncrement.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            variables.add(variableToIncrement); // if it doesn't, declares it and sets value to 1
            values.add(1);
        } else {
            int valueToIncrement = values.get(i);
            values.set(i, valueToIncrement + 1); // if it exists, increments it
        }

        System.out.println(variables.get(i) + " = " + values.get(i));
    }
}
