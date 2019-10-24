import java.util.ArrayList;

public class Decrement {

    /**
     * Method that decrements a variable
     *
     * @param currentLine The line from the input file that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     */
    public static void decrementVariable(String currentLine, ArrayList<String> variables,
                                         ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split("[ ;]")) { // splits the line into words
            currentWords.add(s);
        }

        if (currentWords.size() != 2 || !(currentWords.get(0).equals("decr"))) {
            System.out.println("Decrement syntax incorrect");
            System.exit(-1); // exits program if syntax is incorrect
        }

        String variableToDecrement = currentWords.get(1);
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (variableToDecrement.equals(variables.get(i))) { // checks whether variable exists
                break;
            }
        }
        if (i == variables.size()) {
            System.out.println("Variable " + variableToDecrement +
                    " must be declared using incr or clear instruction");
            System.exit(-1); // if it doesn't, exits program
        } else {
            int valueToDecrement = values.get(i);
            if (valueToDecrement > 0) { // if variable value is positive, decrements it
                values.set(i, valueToDecrement - 1);
            } else { // otherwise, exits program as there can't be negative values
                System.out.println("Variable " + variableToDecrement + " cannot be negative");
                System.exit(-1);
            }
        }

        System.out.println(variables.get(i) + " = " + values.get(i));
    }
}
