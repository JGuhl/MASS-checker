// Import necessary classes
package eu.qped.racket.functions.lists;
import eu.qped.racket.buildingBlocks.Expression;
import java.util.List;

// Define a class named Append that extends the Expression class
public class Append extends Expression {

    /**
     * This method evaluates the expression by calling another evaluate method.
     * @param e the Parent Expression
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Call the overloaded evaluate method
    }

    /**
     * This method evaluates the Append expression.
     * @param list a list of Expressions
     * @return the result of the evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Get the first and second expressions from the list
        Expression c1 = list.get(0);
        Expression c2 = list.get(1);

        // Evaluate the first expression and replace occurrences of "'()" with the result of evaluating the second expression
        return c1.evaluate(this).toString().replace("'()", (String) c2.evaluate(this));
    }

    /**
     * This method returns a string representation of the Append expression.
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "Append "  + " (" + super.getId() + ")"; // Return a string describing the expression
    }
}
