package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

// Define a class named FalseQ that extends the Expression class
public class FalseQ extends Expression {

    /**
     * Evaluate method that takes a parent Expression and returns a result.
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Call the overloaded evaluate method with rest of the expression
    }

    /**
     * Overloaded evaluate method that takes a list of Expressions and returns a result.
     * @param list the list of Expressions
     * @return the evaluation result
     * @throws Exception if an error occurs during evaluation
     */
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers(); // Create an OperatorNumbers instance

        // Loop through each Expression in the list
        for (Expression e : list) {
            int count = 0;

            // Loop through the boolean ArrayList in the opNum instance
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;

                // Check if the current Expression is an instance of Boolean or if its first part is of the expected type
                if (e instanceof Boolean || (e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0)))) {
                    Object result = e.evaluate(this); // Evaluate the Expression
                    System.out.println("Heyyy 1: " + result);

                    // Check if the result is false
                    if (!(boolean) result) {
                        System.out.println(result.getClass());
                        return true; // Return true if the result is false
                    } else {
                        System.out.println("Heyyyyyyy");
                        return false; // Return false if the result is true
                    }
                } else {
                    // Check if all types in boolArrayList have been checked
                    if (opNum.boolArrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Boolean"); // Throw an exception if not of expected type
                    }
                }
            }
        }
        return false; // Default return false
    }

    /**
     * Returns a string representation of the FalseQ object.
     * @return a string representation
     */
    @Override
    public String toString() {
        return "FalseQ" + "(" + super.getId() + ")"; // Return a string in the format "FalseQ(ID)"
    }
}
