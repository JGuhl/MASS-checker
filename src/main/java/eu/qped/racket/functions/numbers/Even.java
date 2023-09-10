package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

// A class named "Even" that extends the "Expression" class
public class Even extends Expression {

    /**
     * This method evaluates the expression.
     *
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the other evaluate method with the rest of the expression
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates the expression given a list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return the result of the evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Create an instance of the OperatorNumbers class
        OperatorNumbers opNum = new OperatorNumbers();
        boolean resultBoolean = false;
        int count = 0;
        int value = 0;

        // Iterate through the list of classes in the OperatorNumbers class
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            // Check if the first expression in the list is an instance of Number or matches a class in the OperatorNumbers list
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    // Evaluate the first expression and convert it to an integer
                    value = (int)(float) list.get(0).evaluate(this);       //Because Racket only accepts Integers in even?
                    // Check if the value is even
                    resultBoolean = value % 2 == 0;
                } else {
                    try {
                        // Evaluate the first expression and convert it to an integer
                        value = (int)(float) Float.parseFloat((String)list.get(0).evaluate(this));       //Because Racket only accepts Integers in even?
                        // Check if the value is even
                        resultBoolean = value % 2 == 0;
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break;  // Exit the loop since we've found a valid class match
            } else {
                // Check if we've iterated through all classes without finding a match
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return resultBoolean;
    }

    /**
     * This method returns a string representation of the Even expression.
     *
     * @return a string representing the Even expression
     */
    @Override
    public String toString() {
        return "Even" + "(" + super.getId() + ")";
    }
}
