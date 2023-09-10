package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Add1 extends Expression {

    /**
     * This method evaluates the expression by incrementing the value of the first
     * element in the list of expressions by 1.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if there's an issue with evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Calls the evaluate method with the rest of the expressions
    }

    /**
     * This method evaluates the list of expressions by incrementing the value of
     * the first element by 1.
     *
     * @param list the list of expressions
     * @return the result of the evaluation
     * @throws Exception if there's an issue with evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();  // Creates an OperatorNumbers instance
        float result = 0;  // Variable to store the result
        int count = 0;  // Counter to keep track of iterations

        // Loop through the classes in opNum's arrayList
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            // Check if the first expression is an instance of Number or the current class
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    result = (float) list.get(0).evaluate(this) + (float) 1;  // Calculate the absolute value
                } else {
                    try {
                        result = Float.parseFloat((String) list.get(0).evaluate(this)) + 1.0f;

                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                // If the first expression is a Number or an instance of a specific class, increment its value by 1
                break;
            } else {
                // If none of the classes match, check if we've iterated through all classes and throw an exception
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return result;
    }

    /**
     * Generates a string representation of the Add1 expression.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Add1" + "(" + super.getId() + ")";
    }
}