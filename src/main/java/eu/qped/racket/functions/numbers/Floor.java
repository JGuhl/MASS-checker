package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Floor extends Expression {

    /**
     * This method evaluates the Floor expression.
     *
     * @param e the parent expression
     * @return the result of the Floor operation
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));  // Calls the overloaded evaluate method
        //return evaluate(e.getNext(id), e.getNext(id+1));  // This line seems to be commented out
    }

    /**
     * This method evaluates the Floor expression for a list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return the result of the Floor operation
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    // Calculate the exponential of the evaluation result of the first expression
                    result = (float) Math.floor((float) list.get(0).evaluate(this));  // Performs the floor operation on the first element
                } else {
                    try {
                        // Evaluate the first expression and convert it to an integer
                        result = (float) Math.floor((float) Float.parseFloat((String)list.get(0).evaluate(this)));
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break;
            } else {
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return result == -0 ? 0 : result;  // Handles the case of negative zero
    }

    /**
     * This method returns a string representation of the Floor expression.
     *
     * @return the string representation of the expression
     */
    @Override
    public String toString() {
        return "Floor" + "(" + super.getId() + ")";
    }
}
