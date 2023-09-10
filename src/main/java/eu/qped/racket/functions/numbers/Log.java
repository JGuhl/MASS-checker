package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Define a class named Log that extends Expression
public class Log extends Expression {

    /**
     * This method evaluates the logarithm expression by calling the overloaded
     * evaluate method with the appropriate argument.
     *
     * @param e the Parent Expression
     * @return the result of the logarithm evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Call the overloaded evaluate method
    }

    /**
     * This method evaluates the logarithm expression based on the provided list
     * of expressions.
     *
     * @param list the list of expressions representing the arguments
     * @return the result of the logarithm evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        float value = 0;

        // Iterate through the list of classes in opNum's arrayList
        for (Class<?> clazz : opNum.arrayList) {
            count++;

            // Check if the first expression in the list is a Number or matches the class
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    // Evaluate the expression and calculate the logarithm
                    value = (float) list.get(0).evaluate(this);
                    result = (float) Math.log(value);
                } else {
                    try {
                        // Evaluate the first expression and convert it to an integer
                        result = (float) Math.log((float) Float.parseFloat((String)list.get(0).evaluate(this)));
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break;
            } else {
                // If the current class doesn't match and all classes are checked
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the Log expression.
     *
     * @return a string describing the Log expression
     */
    @Override
    public String toString() {
        return "Log" + "(" + super.getId() + ")";
    }
}
