package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Define a class named GreaterThan that extends the Expression class
public class GreaterThan extends Expression {

    /**
     * Evaluate the expression using a parent expression.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Delegate to the other evaluate method
    }

    /**
     * Evaluate the expression using a list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return true if all expressions are greater than the first one, false otherwise
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        Boolean first = true;
        float value = 0;

        // Iterate through each expression in the list
        for (Expression e : list) {
            count = 0;

            // Check if the current expression is an instance of Number or matches any class in the opNum arrayList
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        float valueNow = (float) e.evaluate(this);

                        // Compare the current value with the previous one
                        if (first) {
                            value = valueNow;
                            first = false;
                            break;
                        }
                        if (!(value > valueNow)) {
                            return false; // Return false if not all expressions are greater than the previous one
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
                            if (!(value > valueNow)) {
                                return false; // Return false if the condition is not satisfied
                            }
                            value = valueNow;
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }

                    break;
                } else {
                    // Throw an exception if the expression isn't an instance of Number or any class in the opNum arrayList
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }
        return true; // Return true if all expressions are greater than the previous one
    }

    /**
     * Get a string representation of the GreaterThan expression.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "GreaterThan" + "(" + super.getId() + ")";
    }
}
