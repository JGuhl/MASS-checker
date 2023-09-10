// Import necessary classes
package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

// Define a class named "List" that extends the "Expression" class
public class List extends Expression {

    /**
     * This method evaluates the expression by calling another evaluate method.
     * It takes a parent Expression as a parameter and returns the evaluation result.
     *
     * @param e the Parent Expression
     * @return the evaluation result
     * @throws Exception if an exception occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates a list of expressions by constructing a string representation of a Racket list.
     * It takes a list of expressions as input and returns the constructed string.
     *
     * @param list the list of expressions to evaluate
     * @return a string representation of the evaluated Racket list
     * @throws Exception if an exception occurs during evaluation
     */
    @Override
    public Object evaluate(java.util.List<Expression> list) throws Exception {
        String s = "";
        int i = 0;

        // Iterate through each expression in the list
        for (Expression e : list) {
            s += "(cons " + e.evaluate(this) + " "; // Construct part of the string using evaluated expression
            i++;
        }

        s += "'()"; // Add an empty list at the end

        // Close the cons pairs created earlier
        while (i > 0) {
            s += ")";
            i--;
        }

        return s; // Return the constructed string representation of the list
    }

    /**
     * This method generates a string representation of the object.
     *
     * @return a string describing the object
     */
    @Override
    public String toString() {
        return "List " + " (" + super.getId() + ")";
    }
}
