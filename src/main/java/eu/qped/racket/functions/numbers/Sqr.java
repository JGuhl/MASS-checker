package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Sqr extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }

    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 0;
        int count = 0;
        float value = 0;
        for (Class<?> clazz : opNum.arrayList) {
            count++;
            if (list.get(0) instanceof Number || list.get(0) instanceof Parameter || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {

                if (list.get(0) instanceof Number || list.get(0).getParts().size() > 0 && clazz.isInstance(list.get(0).getParts().get(0))) {
                    //String expressionResult = (String) list.get(0).evaluate(this); // Assuming this is how you're getting the result
                    value = (float) list.get(0).evaluate(this);
                    //value = (float) list.get(0).evaluate(this);
                    result = value * value;
                } else {
                    try {
                        String expressionResult = (String) list.get(0).evaluate(this); // Assuming this is how you're getting the result
                        value = Float.parseFloat(expressionResult);
                        //value = (float) list.get(0).evaluate(this);
                        result = value * value;
                    } catch (NumberFormatException ex) {
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                /*if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isnt instance of Number");
                }*/
                }
                break;
            }  else {
                if (opNum.arrayList.size() == count) {
                    throw new Exception("Expression isnt instance of Number");
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Sqr" + "(" + super.getId() + ")";
    }
}
