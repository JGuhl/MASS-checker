package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.*;
import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Number;

import java.util.ArrayList;
import java.util.List;

// This class represents the "Member" expression in a Racket-like programming language.
public class Member extends Expression {

    /**
     * Evaluates the expression using the parent expression's context.
     * @param e the Parent Expression
     * @return the result of evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Evaluate with a modified context
    }

    /**
     * Evaluates the expression given a list of expressions.
     * @param list a list of expressions
     * @return the result of evaluation
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        OperatorNumbers opNum = new OperatorNumbers();
        ArrayList<Class> arrayListAll = new ArrayList<>();
        arrayListAll.addAll(opNum.arrayList);
        arrayListAll.addAll(opNum.boolArrayList);
        int count = 0;
        for (Class<?> clazz : arrayListAll) {
            count++;

            // Check if the first element of the list matches the expected types
            if (list.get(0) instanceof Parameter ||
                    list.get(0) instanceof Boolean ||
                    list.get(0) instanceof Number ||
                    clazz.isInstance(list.get(0).getParts().get(0))) {

                if(list.get(0) instanceof Parameter) { // Handle Parameter (String)
                    String s = (String) list.get(0).evaluate(this);
                    return list.get(1).evaluate(this).toString().contains(s);
                }

                if(list.get(0) instanceof Boolean ||
                        list.get(0).getParts().size() > 0 && opNum.boolArrayList.contains(list.get(0).getParts().get(0).getClass())) {
                    // Handle Boolean
                    boolean b = (boolean) list.get(0).evaluate(this);
                    return list.get(1).evaluate(this).toString().contains(String.valueOf(b));
                }

                if(list.get(0) instanceof Number ||
                        list.get(0).getParts().size() > 0 && opNum.arrayList.contains(list.get(0).getParts().get(0).getClass())) {
                    // Handle Number
                    Float f = (Float) list.get(0).evaluate(this);
                    return list.get(1).evaluate(this).toString().contains(f.toString());
                }
            } else {
                if (arrayListAll.size() == count) {
                    throw new Exception("Expression isn't an instance of Number");
                }
            }
        }
        return false; // Default return value if conditions are not met
    }

    /**
     * Generates a string representation of the expression.
     * @return a string representation
     */
    @Override
    public String toString() {
        return "Member "  + " (" + super.getId() + ")";
    }
}
