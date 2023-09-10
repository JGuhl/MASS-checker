package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

public class Not extends Expression {

    /**
     * This method evaluates the "Not" expression.
     * It takes a parent expression and evaluates its children recursively.
     *
     * @param e the Parent Expression
     * @return the boolean result of the "Not" operation
     * @throws Exception if there's an issue during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates the "Not" expression given a list of expressions.
     *
     * @param list the list of expressions to evaluate
     * @return the boolean result of the "Not" operation
     * @throws Exception if there's an issue during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        for (Expression e : list) {
            int count = 0;
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;
                if (e instanceof Boolean || (e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0)))) {
                    Object result = e.evaluate(this);
                    System.out.println("Heyyy 1: " + result);
                    if (!(boolean) result) {
                        System.out.println(result.getClass());
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (opNum.boolArrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Boolean");
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns a string representation of the "Not" expression.
     *
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "Not" + "(" + super.getId() + ")";
    }
}
