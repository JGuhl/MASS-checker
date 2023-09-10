// Import necessary classes from other packages
package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Define the Max class, which extends the Expression class
public class Max extends Expression {

    /**
     * Evaluate the expression using the parent expression as input.
     *
     * @param e the Parent Expression
     * @return the maximum value obtained from evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * Evaluate a list of expressions and find the maximum value.
     *
     * @param list the list of expressions to evaluate
     * @return the maximum value obtained from evaluating the expressions
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of OperatorNumbers to access number-related operators
        OperatorNumbers opNum = new OperatorNumbers();

        // Initialize variables to track the maximum value and state
        Boolean first = true; // Indicates whether this is the first evaluated value
        float result = 0;     // Stores the maximum value found
        int count = 0;        // Counter for iterating through operator classes

        // Iterate through the list of expressions
        for (Expression e : list) {
            count = 0; // Reset the operator class counter for each expression
            // Iterate through the array of operator classes
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                // Check if the current expression is a Number or an instance of the current operator class
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        // Evaluate the current expression and get its numeric value
                        float currentValue = (float) e.evaluate(this);

                        // If this is the first value encountered, set it as the result
                        if (first) {
                            result = currentValue;
                            first = false;
                        }

                        // If the current value is greater than the stored result, update the result
                        if (result < currentValue) {
                            result = currentValue;
                        }
                    } else {
                        try {
                            float currentValue = 0;
                            if (e.evaluate(this) instanceof String) {
                                currentValue = Float.parseFloat((String) e.evaluate(this));
                            } else {
                                currentValue = (float) e.evaluate(this);
                            }
                            // If this is the first value encountered, set it as the result
                            if (first) {
                                result = currentValue;
                                first = false;
                            }

                            // If the current value is greater than the stored result, update the result
                            if (result < currentValue) {
                                result = currentValue;
                            }
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }
                    break; // Break the operator class loop after finding a match
                } else {
                    // If the expression doesn't match any operator class, check if this is the last class
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }
        return result; // Return the maximum value found
    }

    /**
     * Generate a string representation of the Max expression.
     *
     * @return a string representation of the Max expression
     */
    @Override
    public String toString() {
        return "Max" + "(" + super.getId() + ")";
    }
}
