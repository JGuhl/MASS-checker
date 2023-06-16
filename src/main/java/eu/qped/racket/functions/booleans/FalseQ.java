package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.OperatorNumbers;

import java.util.List;

public class FalseQ extends Expression{

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }


    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        for (Expression e : list) {
            int count = 0;
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;
                if (e instanceof Boolean || (e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0)))) {
                    Object result = e.evaluate(this);
                    System.out.println("Heyyy 1: " + result);
                    if (!result.equals("true")) {
                        System.out.println(result.getClass());
                        return true;
                    } else {
                        System.out.println("Heyyyyyyy");
                        return false;
                    }
                } else {
                    if (opNum.boolArrayList.size() == count) {
                        throw new Exception("Expression isn't instance of Boolean");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "FalseQ" + "(" + super.getId() + ")";
    }
}
