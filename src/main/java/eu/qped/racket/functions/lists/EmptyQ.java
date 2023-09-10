package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;
import java.util.List;

// This class represents a function to check if a given list is empty or not
public class EmptyQ extends Expression {

    /**
     * This method is called when evaluating this function with a parent expression.
     * It delegates the evaluation to the version of the method that takes a list as an argument.
     * @param e the parent expression
     * @return the result of evaluating with the parent expression's rest
     */
    @Override
    public Object evaluate(Expression e) {
        return evaluate(e.getRest(super.getId())); // Calls the evaluate method with the rest of the parent expression
    }

    /**
     * This method is called when evaluating this function with a list of expressions as an argument.
     * It checks if the first element of the list is an instance of the Empty class.
     * @param list the list of expressions
     * @return a string representation of whether the list is empty or not
     */
    @Override
    public Object evaluate(List<Expression> list) {
        return Boolean.toString(list.get(0).getClass().equals(new Empty().getClass())); // Compares the class of the first element with Empty class
    }

    /**
     * This method generates a string representation of the function.
     * @return a string indicating that this function checks if a list is empty
     */
    @Override
    public String toString() {
        return "Empty? "  + " (" + super.getId() + ")"; // Creates a string representation of the function
    }
}
