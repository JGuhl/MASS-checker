package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

public class Zero extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }

    /**
     * Checks whether the given Number is 0. Only Numbers are accepted as input.
     *
     * @param list List of operands
     * @return Boolean indicating if the Number is 0
     * @throws Exception If a list entry is found that is not a Number, such as a Boolean or String.
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        boolean returnBoolean = false;
        try {
            returnBoolean = ((float) (list.get(0).evaluate(this)) == 0);
        } catch (ClassCastException e) {
            String stException = "Expression isnt instance of Number/expects a float";
            throw new Exception(stException);
        }
        return returnBoolean;
    }

    @Override
    public String toString() {
        return "Zero?" + "(" + super.getId() + ")";
    }
}
