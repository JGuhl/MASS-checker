package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Modulo extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }

    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        float value1 = 0;
        float value2 = 0;
        int count = 0;
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            if ((list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) || (list.get(1) instanceof Number || list.get(1) instanceof Parameter || list.get(1).getParts().size() > 0 && clazz.isInstance(list.get(1).getParts().get(0)))) {
                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    value1 = (float) list.get(0).evaluate(this);
                    value2 = (float) list.get(1).evaluate(this);
                    result = value1 % value2;
                } else {
                    try {
                        if (list.get(0).evaluate(this) instanceof String) {
                            value1 = (float) Float.parseFloat((String) list.get(0).evaluate(this));
                        } else {
                            value1 = (float) list.get(0).evaluate(this);
                        }
                        if (list.get(1).evaluate(this) instanceof String) {
                            value2 = (float) Float.parseFloat((String) list.get(1).evaluate(this));
                        } else {
                            value2 = (float) list.get(1).evaluate(this);
                        }
                        result = value1 % value2;
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
        return result == -0f ? 0 : result;      //Because java has -0 and Racket does not
    }

    @Override
    public String toString() {
        return "Modulo" + "(" + super.getId() + ")";
    }
}
