package eu.qped.racket.buildingBlocks;


import eu.qped.racket.functions.define.CustomFunction;

import java.util.List;

public class Parameter extends CustomFunction {
    Object paraName;
    String value;

    public Parameter(Object paraName) {
        this.paraName = paraName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getParaName() {
        return this.paraName;
    }

    @Override
    public Object evaluate(Expression e) {
        return value;
    }

    public Object evaluate(List<Expression> list) {
        return null;
    }

    @Override
    public String toString() {
        return "Parameter "+ paraName + " (" + super.getId() + ")";
    }

}
