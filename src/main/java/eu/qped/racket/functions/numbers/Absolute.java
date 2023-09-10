// Importing necessary classes
package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// Absolute class definition, extending the Expression class
public class Absolute extends Expression {

    /**
     * This method evaluates the Absolute expression.
     *
     * @param e the Parent Expression
     * @return the absolute value of the expression
     * @throws Exception if evaluation encounters an issue
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));  // Calls the other evaluate method with the remaining expressions
    }

    /**
     * This method evaluates the Absolute expression when given a list of expressions.
     *
     * @param list the list of expressions
     * @return the absolute value of the first expression
     * @throws Exception if evaluation encounters an issue
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        System.out.println("Hi: 1234");
        OperatorNumbers opNum = new OperatorNumbers();  // Creates an OperatorNumbers instance
        float result = 0;  // Variable to store the result
        int count = 0;  // Counter to keep track of iterations

        // Loop through the classes in opNum's arrayList
        for (Class<?> clazz : opNum.arrayList) {
            count++;

            // Check if the first expression is an instance of Number or the current class
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    result = (float) Math.abs((float) list.get(0).evaluate(this));  // Calculate the absolute value
                } else {
                    try {
                        result = Math.abs(Float.parseFloat((String) list.get(0).evaluate(this)));
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break;  // Exit the loop
            } else {
                // Check if the loop has iterated through all classes in arrayList
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return result;  // Return the calculated absolute value
    }

    /**
     * This method returns the string representation of the Absolute expression.
     *
     * @return the string representation of the expression
     */
    @Override
    public String toString() {
        return "Absolute" + "(" + super.getId() + ")";
    }
}