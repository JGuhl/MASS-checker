// Import necessary packages and classes
package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

// Define the class BooleanEQ that extends the Expression class
public class BooleanEQ extends Expression {

    /**
     * This method evaluates the expression by calling the evaluate method with the rest of the expression's parts.
     *
     * @param e the Parent Expression
     * @return the result of evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates a list of expressions to check if their boolean values are equal.
     *
     * @param list the list of expressions to be evaluated
     * @return true if boolean values are equal, false otherwise
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        boolean firstBoolean = false;
        boolean first = true;

        // Iterate through the list of expressions
        for (Expression e : list) {
            int count = 0;

            // Iterate through the boolean array list in opNum
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;

                // Check if the expression is an instance of Boolean or matches a specific condition
                if (e instanceof Boolean || (e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0)))) {
                    if (first) {
                        firstBoolean = (boolean) e.evaluate(this);
                        first = false;
                        break;
                    }
                    // Compare boolean values and return true if they are equal
                    if (firstBoolean == (boolean) e.evaluate(this)) {
                        return true;
                    }
                } else {
                    // If expression doesn't match expected types, throw an exception
                    if (opNum.boolArrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Boolean");
                    }
                }
            }
        }
        // Return false if boolean values are not equal
        return false;
    }

    /**
     * This method returns a string representation of the BooleanEQ object.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "BooleanEQ" + "(" + super.getId() + ")";
    }
}
