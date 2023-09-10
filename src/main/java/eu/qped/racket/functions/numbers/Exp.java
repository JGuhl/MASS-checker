package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Exp extends Expression {

    /**
     * This method evaluates the expression by calling the overloaded evaluate method with a modified argument.
     * It is assumed that super.getId() returns the ID of the current instance of the Exp class.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates a list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of OperatorNumbers class to access its arrayList field
        OperatorNumbers opNum = new OperatorNumbers();

        // Initialize variables to store the result and count of iterations
        float result = 0;
        int count = 0;

        // Iterate through the array list of operator classes
        for (Class<?> clazz : opNum.arrayList) {
            count++;

            // Check if the first expression in the list is either an instance of Number
            // or an instance of a class from the operator class list
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    // Calculate the exponential of the evaluation result of the first expression
                    result = (float) Math.exp((float) list.get(0).evaluate(this));
                } else {
                    try {
                        // Evaluate the first expression and convert it to an integer
                        result = (float) Math.exp((float) Float.parseFloat((String)list.get(0).evaluate(this)));
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break; // Exit the loop since evaluation is successful
            } else {
                // Check if all operator classes have been checked, and if so, throw an exception
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }

        return result; // Return the evaluation result
    }

    /**
     * This method returns a string representation of the Exp object.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Exp" + "(" + super.getId() + ")";
    }
}
