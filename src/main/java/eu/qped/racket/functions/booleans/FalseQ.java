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

    //@Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        int count = 0;
        for (Expression e : list) {
            for (Class<?> clazz : opNum.boolArrayList) {
                count++;
                if (e instanceof Boolean || e.getParts().size() > 0 && clazz.isInstance(e.getParts().get(0))) {
                    if(e.evaluate(this).equals("false"))
                        return true;
                    else
                        return false;
                } else {
                    if (opNum.arrayList.size() == count) {
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
