package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class GreaterOrEqualThan extends Expression {

    /**
     * This method evaluates the expression by calling the overloaded evaluate method.
     * It returns the result of evaluating the expression using the rest of the parent's parts.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates a list of expressions to check if they satisfy the "greater than or equal" condition.
     * It iterates through the list of expressions, comparing their values to the previous value encountered.
     * If any value is not greater than or equal to the previous one, it returns false. Otherwise, it returns true.
     *
     * @param list the list of expressions to evaluate
     * @return true if all expressions satisfy the condition, false otherwise
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        Boolean first = true;
        float value = 0;

        // Iterate through the list of expressions
        for (Expression e : list) {
            count = 0;

            // Check if the expression is an instance of Number or matches a class in the opNum arrayList
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        float valueNow = (float) e.evaluate(this);
                        // If it's the first value encountered, set it as the reference value
                        if (first) {
                            value = valueNow;
                            first = false;
                            break;
                        }

                        // Compare the current value with the previous one
                        if (!(value >= valueNow)) {
                            return false; // Return false if the condition is not satisfied
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
                            if (!(value >= valueNow)) {
                                return false; // Return false if the condition is not satisfied
                            }
                            value = valueNow;
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }

                    break;
                } else {
                    // If the expression doesn't match any expected class, throw an exception
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }

        return true; // All expressions satisfy the condition
    }

    /**
     * This method returns a string representation of the GreaterOrEqualThan expression.
     *
     * @return the string representation of the expression
     */
    @Override
    public String toString() {
        return "GreaterThanOrEqualThan" + "(" + super.getId() + ")";
    }
}
