// Import necessary classes
package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

// Define a class that extends Expression
public class BooleanQ extends Expression {

    /**
     * Evaluate the expression using the parent expression as the context.
     *
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Evaluate using the rest of the expression
    }

    /**
     * Evaluate the list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return the evaluation result
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();

        for (Expression e : list) {
            int count = 0;
            // Iterate through the boolean array list of OperatorNumbers
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;
                // Check if the expression is a Boolean or an instance of a class from boolArrayList
                if (e instanceof Boolean || (e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0)))) {
                    // Check if the evaluation of e is true or false
                    if ((boolean) e.evaluate(this) || !(boolean) e.evaluate(this))
                        return true; // Return true if either condition is met
                } else {
                    // If not a Boolean or the correct instance, check if it's the last iteration
                    if (opNum.boolArrayList.size() == count) {
                        return false; // Return false if none of the conditions match
                    }
                }
            }
        }
        return false; // Default return value if no conditions are met
    }

    /**
     * Generate a string representation of the class.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "BooleanQ" + "(" + super.getId() + ")";
    }
}
