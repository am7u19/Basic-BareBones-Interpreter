import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A basic BareBones interpreter
 *
 * Variables need to be declared through clear or incr instructions.
 * A variable declared through a clear instruction will be set to the default value (i.e. 0).
 * A variable declared through an incr instruction will be set to the default value and immediately incremented (i.e. set to 1).
 * If a decr or while is used with an undeclared variable, the interpreter stops.
 *
 * Variables have to be non-negative integers. If a variable were to become negative after an instruction (i.e. decr), then the interpreter stops.
 */
public class Main {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/input.txt");
            FileReader input = new FileReader(inputFile);
            BufferedReader bufferedInput = new BufferedReader(input);

            String currentLine;
            ArrayList<String> fileContent = new ArrayList<>();

            currentLine = bufferedInput.readLine();
            while (currentLine != null) {
                while (currentLine.charAt(0) == ' ' || currentLine.charAt(0) == '\r' ||
                        currentLine.charAt(0) == '\n' || currentLine.charAt(0) == '\t') {
                    currentLine = currentLine.substring(1);
                } // removes whitespace from the beginning of a line
                if (!currentLine.endsWith(";")) {
                    System.out.println("; missing"); // checks for semicolons
                    System.exit(-1);
                }
                fileContent.add(currentLine);
                currentLine = bufferedInput.readLine();
            }

            ArrayList<String> variables = new ArrayList<>();
            ArrayList<Integer> values = new ArrayList<>();

            for (int i = 0; i < fileContent.size(); i++) { // goes through the file
                currentLine = fileContent.get(i); // and checks which operation to do
                if (currentLine.contains("incr")) {
                    Increment.incrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("decr")) {
                    Decrement.decrementVariable(currentLine, variables, values);
                } else if (currentLine.contains("clear")) {
                    Clear.clearVariable(currentLine, variables, values);
                } else if (currentLine.contains("while")) {
                    i = WhileLoop.whileLoop(fileContent, currentLine, i, variables, values);
                } else {
                    System.out.println("Instruction not understood");
                    System.exit(-1); // if line does not match an instruction, exits program
                }
            }

            System.out.println();
            System.out.println("Final values:");
            for (int i = 0; i < variables.size(); i++) {
                System.out.println(variables.get(i) + " = " + values.get(i));
            }
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(-1);
        }
    }
}
