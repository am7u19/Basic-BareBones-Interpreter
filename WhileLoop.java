import java.util.ArrayList;

public class WhileLoop {

    /**
     * Method that emulates a while loop
     *
     * @param fileContent An ArrayList that contains the contents of the input file
     * @param currentLine The line from the input file that is currently accessed
     * @param startIndex The index of the line from which the loop starts
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @param values An ArrayList that contains the values of the variables used in the input
     * @return The index of the line where the loop ends
     */
    public static int whileLoop(ArrayList<String> fileContent, String currentLine, int startIndex,
                                 ArrayList<String> variables, ArrayList<Integer> values) {

        ArrayList<String> currentWords = new ArrayList<>();
        for (String s : currentLine.split("[ ;]")) { // splits the line into words
            currentWords.add(s);
        }

        int conditionVariableIndex = checkLoopSyntax(currentWords, variables);

        int whileCount = 0; // counts how many unended while loops are inside the current loop
        int endIndex = 0; // the index of the line that ends the loop
        for (int i = startIndex + 1; i < fileContent.size(); i++) {

            if (fileContent.get(i).contains("while")) {
                whileCount++; // if there is a while loop inside the loop, increments count
            }
            if (fileContent.get(i).contains("end;")) { // if the end of a loop is found
                if (whileCount == 0) { // and there are no unended while loops, this is the index
                    endIndex = i;
                    break;
                } else { // otherwise, a nested while loop is ended at this point
                    whileCount--;
                }
            }
        }

        if (endIndex == 0) { // if an end couldn't be found, program is incorrect and is exited
            System.out.println("While loop not closed");
            System.exit(-1);
        }

        while (values.get(conditionVariableIndex) > 0) {
            for (int i = startIndex + 1; i < endIndex; i++) { // goes through the file
                currentLine = fileContent.get(i); // and checks which operation to do
                if (currentLine.contains("incr")) {
                    Increment.incrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("decr")) {
                    Decrement.decrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("clear")) {
                    Clear.clearVariable(currentLine, variables, values);
                } else if (currentLine.contains("while")) {
                    i = whileLoop(fileContent, currentLine, i, variables, values);
                } else {
                    System.out.println("Instruction not found");
                    System.exit(-1); // if line does not match an instruction, exits program
                }
            }
        }

        return endIndex; // returns the index of the end of the loop
    }

    /**
     * Method that checks syntax of the first line of a while loop
     *
     * @param currentWords The words of the line that is currently accessed
     * @param variables An ArrayList that contains the name of the variables used in the input
     * @return The index of the condition variable in the variable ArrayList
     */
    public static int checkLoopSyntax(ArrayList<String> currentWords, ArrayList<String> variables) {

        // if a syntax error is found, exits program
        if(currentWords.size() != 5) {
            System.out.println("While syntax incorrect length");
            System.exit(-1);
        }

        if(!currentWords.get(0).equals("while")) {
            System.out.println("While syntax missing while");
            System.exit(-1);
        }

        if(!currentWords.get(2).equals("not")) {
            System.out.println("While syntax missing not");
            System.exit(-1);
        }

        if(!currentWords.get(3).equals("0")) {
            System.out.println("While syntax missing 0");
            System.exit(-1);
        }

        if(!currentWords.get(4).equals("do")) {
            System.out.println("While syntax missing do");
            System.exit(-1);
        }

        // if there are no syntax errors, checks whether the condition variable is already declared
        String conditionVariable = currentWords.get(1);
        int i;
        for (i = 0; i < variables.size(); i++) {
            if (conditionVariable.equals(variables.get(i))) {
                break;
            }
        }
        if (i == variables.size()) { // if the condition variable is not declared, exits program
            System.out.println("Variable " + conditionVariable +
                    " must be declared using incr or clear instruction");
            System.exit(-1);
        } else {
            return i; // otherwise, returns its index in the ArrayList
        }

        return 0;
    }
}
