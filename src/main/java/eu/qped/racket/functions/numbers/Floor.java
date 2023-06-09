package eu.qped.racket.functions.numbers;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

public class Floor extends Expression {

    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
        //return evaluate(e.getNext(id), e.getNext(id+1));
    }

    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        float value = (float)Math.floor((float) list.get(0).evaluate(this));
        return value == -0 ? 0 : value;
    }

    @Override
    public String toString() {
        return "Floor" + "(" + super.getId() + ")";
    }
}
