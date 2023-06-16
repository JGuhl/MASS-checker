package eu.qped.racket.functions.booleans;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

public class Not extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        for (Expression e : list) {
            if (e.evaluate(this).equals("false"))
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Not" + "(" + super.getId() + ")";
    }
}
