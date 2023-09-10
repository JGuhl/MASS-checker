package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class LessOrEqualThan extends Expression {

    /**
     * This method evaluates the expression by calling the overloaded evaluate method with the appropriate arguments.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        // Note: The commented line above seems to be replaced by the line below.
        // return evaluate(e.getNext(id), e.getNext(id+1));
    }

    /**
     * This method evaluates a list of expressions to determine if the values they represent satisfy the less-than-or-equal condition.
     *
     * @param list the list of expressions to evaluate
     * @return true if all values satisfy the condition, false otherwise
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        Boolean first = true;
        float value = 0;
        for (Expression e : list) {
            count = 0;
            // Iterate through the list of operator classes
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                // Check if the current expression is a Number or an instance of the current operator class
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        float valueNow = (float) e.evaluate(this);
                        if (first) {
                            value = valueNow;
                            first = false;
                            break;
                        }
                        // Check if the current value is less than or equal to the previous value
                        if (!(value <= valueNow)) {
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
                            if (!(value <= valueNow)) {
                                return false; // Return false if the condition is not satisfied
                            }
                            value = valueNow;
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }
                    break;
                } else {
                    // If the expression isn't a Number or an instance of any operator class, throw an exception
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number or an operator class");
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method returns a string representation of the LessOrEqualThan expression.
     *
     * @return the string representation of the expression
     */
    @Override
    public String toString() {
        return "LessOrEqualThan" + "(" + super.getId() + ")";
    }
}
