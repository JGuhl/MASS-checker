// Import statements for required classes
package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Class definition for the LessThan expression
public class LessThan extends Expression {

    /**
     * This method evaluates the LessThan expression.
     *
     * @param e the Parent Expression
     * @return the evaluation result (true if the condition is met, false otherwise)
     * @throws Exception if there's an issue during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the evaluate method with a reduced list of expressions
        return evaluate(e.getRest(super.getId()));
        // Alternatively: return evaluate(e.getNext(id), e.getNext(id+1));
    }

    /**
     * This method evaluates the LessThan expression for a list of expressions.
     *
     * @param list the list of expressions to be evaluated
     * @return the evaluation result (true if all comparisons are true, false otherwise)
     * @throws Exception if there's an issue during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of OperatorNumbers for comparison
        OperatorNumbers opNum = new OperatorNumbers();

        // Initialize variables for comparison
        float result = 0;
        int count = 0;
        Boolean first = true;
        float value = 0;

        // Iterate through the list of expressions for comparison
        for (Expression e : list) {
            count = 0;

            // Check if the expression is an instance of Number or compatible class
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        // Evaluate the expression
                        float valueNow = (float) e.evaluate(this);

                        // Perform the comparison
                        if (first) {
                            value = valueNow;
                            first = false;
                            break;
                        }
                        if (!(value < valueNow)) {
                            return false;
                        }
                        value = valueNow;
                    } else {
                        try {
                            float valueNow = 0;
                            if (e.evaluate(this) instanceof String) {
                                valueNow = Float.parseFloat((String) e.evaluate(this));
                            } else {
                                valueNow = (float) e.evaluate(this);
                            }
                            // If it's the first value encountered, set it as the reference value
                            if (first) {
                                value = valueNow;
                                first = false;
                                break;
                            }

                            // Compare the current value with the previous one
                            if (!(value < valueNow)) {
                                return false; // Return false if the condition is not satisfied
                            }
                            value = valueNow;
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }

                    break;
                } else {
                    // If not compatible, check if we've exhausted all possibilities
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }
        return true; // All comparisons passed
    }

    /**
     * This method returns a string representation of the LessThan expression.
     *
     * @return the string representation of the expression
     */
    @Override
    public String toString() {
        return "LessThan" + "(" + super.getId() + ")";
    }
}
