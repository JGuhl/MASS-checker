// Import necessary classes
package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Define a class named "Division" that extends the "Expression" class
public class Division extends Expression {

    // Override the "evaluate" method to accept an Expression and delegate to another version of "evaluate"

    /**
     * Evaluates the given Expression by delegating to another version of "evaluate".
     *
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    // Override the "evaluate" method to accept a list of Expressions and perform division operation

    /**
     * Evaluates a list of Expressions by performing a division operation.
     *
     * @param list the list of Expressions to evaluate
     * @return the result of the division operation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of OperatorNumbers to check for supported classes
        OperatorNumbers opNum = new OperatorNumbers();

        int count = 0;
        boolean first = true;
        float result = 0;

        // Loop through each expression in the list
        for (Expression e : list) {
            count = 0;

            // Loop through the supported class list
            for (Class<?> clazz : opNum.arrayList) {
                count++;

                // Check if the expression is an instance of Number or a supported class
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        if (first) {
                            // If it's the first expression, set the result to its evaluation
                            result = (float) e.evaluate(this);
                            first = false;
                            break;
                        } else {
                            // If it's not the first expression, perform division
                            result = result / (float) e.evaluate(this);
                            break;
                        }
                    } else {
                        try {
                            if (first) {
                                // If it's the first expression, set the result to its evaluation
                                result = (float) Float.parseFloat((String) e.evaluate(this));
                                first = false;
                                break;
                            } else {
                                // If it's not the first expression, perform division
                                result = result / Float.parseFloat((String) e.evaluate(this));
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }
                } else {
                    // If expression is not a supported class
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }
        return result; // Return the final result of the division
    }

    // Override the "toString" method to provide a string representation of the Division object

    /**
     * Generates a string representation of the Division object.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Division" + "(" + super.getId() + ")";
    }
}
