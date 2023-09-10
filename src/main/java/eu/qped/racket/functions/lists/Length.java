// Import necessary classes
package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

// Define a class named "Length" that extends the "Expression" class
public class Length extends Expression {

    /**
     * This method evaluates the expression and returns the length of the parent expression's rest.
     *
     * @param e the Parent Expression
     * @return the length of the parent expression's rest
     * @throws Exception if an exception occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the evaluate method on the parent expression's rest
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates the length of a list of expressions.
     *
     * @param list the list of expressions
     * @return the length of the list as a string
     * @throws Exception if an exception occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Get the first element of the list and evaluate it using this Length instance
        // Convert the evaluation result to a string, split it by "cons", and calculate the length of the split array
        return Float.toString(list.get(0).evaluate(this).toString().split("cons").length - 1);
    }

    /**
     * Returns a string representation of the Length object.
     *
     * @return a string describing the Length object
     */
    @Override
    public String toString() {
        // Return a string indicating the name of the function and the ID of the parent expression
        return "Length "  + " (" + super.getId() + ")";
    }
}
