// Import necessary packages and classes
package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

// Define a class named Empty that extends the Expression class
public class Empty extends Expression {

    /**
     * This method evaluates the Empty expression within the context of a parent expression.
     *
     * @param e the Parent Expression
     * @return the string representation of an empty list ('())
     */
    @Override
    public Object evaluate(Expression e) {
        return "'()"; // Return the string representation of an empty list
    }

    /**
     * This method evaluates the Empty expression within the context of a list of expressions.
     *
     * @param list the list of expressions
     * @return an empty string
     */
    @Override
    public Object evaluate(List<Expression> list) {
        return ""; // Return an empty string
    }

    /**
     * This method provides a string representation of the Empty object.
     *
     * @return a string containing "Empty" and the unique identifier of the object
     */
    @Override
    public String toString() {
        return "Empty "  + " (" + super.getId() + ")"; // Return a string describing the Empty object
    }
}
