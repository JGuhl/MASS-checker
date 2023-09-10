package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Boolean;
import eu.qped.racket.buildingBlocks.Expression;
import eu.qped.racket.buildingBlocks.Number;
import eu.qped.racket.buildingBlocks.OperatorNumbers;
import eu.qped.racket.buildingBlocks.Parameter;

import java.util.ArrayList;
import java.util.List;

public class MakeList extends Expression {

    /**
     * This method evaluates the expression by calling the overloaded evaluate method with the rest of the expression.
     *
     * @param e the Parent Expression
     * @return the result of evaluation
     * @throws Exception if evaluation encounters an exception
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates the given list of expressions to create a list.
     *
     * @param list the list of expressions to evaluate
     * @return the constructed list as a string
     * @throws Exception if evaluation encounters an exception
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {

        String s = "";  // Initialize an empty string to store the constructed list
        int counter = 0;  // Initialize a counter to keep track of parentheses

        OperatorNumbers opNum = new OperatorNumbers();
        ArrayList<Class> arrayListAll = new ArrayList<>();
        arrayListAll.addAll(opNum.arrayList);
        arrayListAll.addAll(opNum.boolArrayList);

        int count = 0;
        for (Class<?> clazz : arrayListAll) {
            count++;
            // Check if the first and second elements in the list are of valid types
            if ((list.get(0) instanceof Number || clazz.isInstance(list.get(0).getParts().get(0))) &&
                    (list.get(1) instanceof Parameter || list.get(1) instanceof Boolean || list.get(1) instanceof Number || clazz.isInstance(list.get(1).getParts().get(0)))) {

                float number = (float) list.get(0).evaluate(this);  // Evaluate the first expression as a float
                System.out.println(number);

                if (list.get(1) instanceof Boolean || list.get(1).getParts().size() > 0 && opNum.boolArrayList.contains(list.get(1).getParts().get(0).getClass())) {
                    // Handle boolean values
                    while (number > 0) {
                        boolean valueB = (boolean) list.get(1).evaluate(this);  // Evaluate the second expression as a boolean
                        s += "(cons " + valueB + " ";
                        number--;
                        counter++;
                    }
                    s += "'()";
                    while (counter > 0) {
                        s += ")";
                        counter--;
                    }
                    break;
                } else if (list.get(1) instanceof Number || list.get(1).getParts().size() > 0 && opNum.arrayList.contains(list.get(1).getParts().get(0).getClass())) {
                    // Handle numeric values
                    while (number > 0) {
                        float valueF = (float) list.get(1).evaluate(this);  // Evaluate the second expression as a float
                        s += "(cons " + valueF + " ";
                        number--;
                        counter++;
                    }
                    s += "'()";
                    while (counter > 0) {
                        s += ")";
                        counter--;
                    }
                    break;
                } else if (list.get(1) instanceof Parameter) {
                    // Handle string values
                    while (number > 0) {
                        String valueS = (String) list.get(1).evaluate(this);  // Evaluate the second expression as a string
                        s += "(cons " + valueS + " ";
                        number--;
                        counter++;
                    }
                    s += "'()";
                    while (counter > 0) {
                        s += ")";
                        counter--;
                    }
                    break;
                }
            } else {
                if (arrayListAll.size() == count) {
                    throw new Exception("Expression isn't an instance of Number/Parameter/Boolean");
                }
            }
        }
        return s;  // Return the constructed list as a string
    }

    /**
     * Returns a string representation of the MakeList expression.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "make-list " + " (" + super.getId() + ")";
    }
}