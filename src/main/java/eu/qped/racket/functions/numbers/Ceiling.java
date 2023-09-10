// Import necessary classes from external packages
package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Define a class named "Ceiling" that extends the "Expression" class
public class Ceiling extends Expression {

    /**
     * Evaluate the expression using the parent expression's data
     *
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the overloaded evaluate method with the expression's remaining parts
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * Evaluate the expression using a list of expressions
     *
     * @param list the list of expressions to evaluate
     * @return the evaluation result
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of the OperatorNumbers class
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0; // Initialize the result
        int count = 0; // Initialize the count
        // Iterate through the classes in the arrayList of OperatorNumbers
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            // Check if the first expression is an instance of Number or a specific class
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    result = (float)Math.ceil((float) list.get(0).evaluate(this));
                } else {
                    try {
                        result = (float) Math.ceil(Float.parseFloat((String) list.get(0).evaluate(this)));
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                // Evaluate the first expression and apply the ceil function, store the result
                break; // Exit the loop
            } else {
                // If no match is found and all classes are iterated, throw an exception
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        // Check if the result is -0 and adjust it to 0
        return result == -0 ? 0 : result;
    }

    /**
     * Return a string representation of the Ceiling object
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Ceiling" + "(" + super.getId() + ")";
    }
}
