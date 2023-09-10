// Import necessary classes
package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

// Define a class named "Second" that extends the "Expression" class
public class Second extends Expression {

    /**
     * This method evaluates the second element of a list.
     *
     * @param e the parent expression
     * @return the result of evaluating the second element
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the evaluate method with the rest of the elements and return the result
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates the second element of a list.
     *
     * @param list the list containing expressions
     * @return the result of evaluating the second element
     * @throws Exception if an evaluation error occurs
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Get the first element of the list, evaluate it using this instance of "Second",
        // convert the result to a string, and extract the desired information from it
        return list.get(0).evaluate(this).toString().split("cons ")[2].split("\\s")[0];
    }

    /**
     * This method provides a string representation of the "Second" expression.
     *
     * @return a string describing the "Second" expression
     */
    @Override
    public String toString() {
        return "second "  + " (" + super.getId() + ")";
    }
}
