package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Equal extends Expression {

    /**
     * This method is used to evaluate the expression.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Delegates to the other evaluate method
    }

    /**
     * This method is used to evaluate a list of expressions for equality.
     *
     * @param list the list of expressions to be evaluated
     * @return true if the expressions are equal, false otherwise
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        Boolean first = true;
        float value = 0;
        for (Expression e : list) {
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                // Check if the current expression is either a Number or an instance of a class from the OperatorNumbers list
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                        float valueNow = (float) e.evaluate(this);
                        if (first) {
                            value = valueNow;
                            first = false;
                            continue;
                        }
                        float epsilon = 1 * (float) Math.pow(10, -10); // The accepted deviation for Float Values, here set to 1*10^-10
                        // Check if the difference between the current value and the previous value is within epsilon
                        if (!(Math.abs(value - valueNow) < epsilon)) {
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

                            if (first) {
                                value = valueNow;
                                first = false;
                                continue;
                            }
                            float epsilon = 1 * (float) Math.pow(10, -10); // The accepted deviation for Float Values, here set to 1*10^-10
                            // Check if the difference between the current value and the previous value is within epsilon
                            if (!(Math.abs(value - valueNow) < epsilon)) {
                                return false;
                            }
                            value = valueNow;
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }
                    break;
                } else {
                    // If the expression is not a Number or an instance of a class from the OperatorNumbers list, throw an exception
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method provides a string representation of the Equal expression.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Equal" + "(" + super.getId() + ")";
    }
}
