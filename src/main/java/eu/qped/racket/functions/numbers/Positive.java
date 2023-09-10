package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Positive extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }

    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        int count = 0;
        int value = 0;
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    value = (int)(float) list.get(0).evaluate(this);       //Because Racket only accepts Integers in even?
                } else {
                    try {
                        // Evaluate the first expression and convert it to an integer
                        value = (int)(float) Float.parseFloat((String)list.get(0).evaluate(this));       //Because Racket only accepts Integers in even?
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                }
                break;
            } else {
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isnt instance of Number");
                }
            }
        }
        return value > 0;
    }

    @Override
    public String toString() {
        return "Positive" + "(" + super.getId() + ")";
    }
}
