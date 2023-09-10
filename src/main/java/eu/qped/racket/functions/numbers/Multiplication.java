package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.List;

public class Multiplication extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }


/*    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 1;
        int count = 0;

        for (Expression e : list) {
            count = 0;
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    Object evalResult = e.evaluate(this); // Evaluate the expression
                    if (evalResult instanceof Number) {
                        result *= (float) e.evaluate(this);
                    } else if (evalResult instanceof String) {
                        result *= Float.parseFloat((String) e.evaluate(this));
                        System.out.println(result);
                    } else {
                        System.out.println("Hi " + evalResult);
                        throw new Exception("Expression evaluation result is not a Number");
                    }
                    break;
                } else {
                    if (opNum.arrayList.size() == count) {
                        throw new Exception("Expression isn't an instance of Number");
                    }
                }
            }
        }

        return result;
    }*/


    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        float result = 1;
        int count = 0;
        for (Expression e : list) {
            count = 0;
            for (Class<?> clazz : opNum.arrayList) {
                count++;
                if (e instanceof Number || e instanceof Parameter || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if (e instanceof Number || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                        result *= (float) e.evaluate(this);
                    } else {
                        try {
                            result *= Float.parseFloat((String) e.evaluate(this));
                        } catch (NumberFormatException ex) {
                            throw new Exception("Expression evaluation result is not a Number");
                        }
                    }
                    break;
                } else {
                    if (opNum.arrayList.size() == count) {
                        System.out.println(e.evaluate(this) + " : " + e.getClass());
                        throw new Exception("Expression isnt instance of Number");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Multiplication" + "(" + super.getId() + ")";
    }
}
