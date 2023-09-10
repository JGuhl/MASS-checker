// Import necessary classes
package eu.qped.racket.functions.lists;
import eu.qped.racket.buildingBlocks.Expression;
import java.util.List;

// Define a class named "First" that extends the "Expression" class
public class First extends Expression {

    /**
     * This method is used to evaluate the expression.
     * It takes a parent expression 'e' and returns the result of evaluating it.
     *
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        // Call the 'evaluate' method with the rest of the expressions and return the result
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method is used to evaluate a list of expressions.
     * It takes a list of expressions 'list' and returns the evaluation result of the first expression.
     *
     * @param list the list of expressions
     * @return the evaluation result of the first expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        // Get the first expression from the list, evaluate it using this object as context,
        // convert the result to a string, remove the first 5 characters, and split the string by whitespace.
        // Return the second element of the split array.
        return list.get(0).evaluate(this).toString().substring(5).split("\\s")[1];
    }

    /**
     * This method generates a string representation of the object.
     *
     * @return a string describing the object
     */
    @Override
    public String toString() {
        // Return a string describing the "First" object, including its ID
        return "first " + " (" + super.getId() + ")";
    }
}
