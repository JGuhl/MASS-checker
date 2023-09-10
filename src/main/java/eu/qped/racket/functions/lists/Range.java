package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

// This class represents a Racket function called "Range" that generates a list of values within a specified range.
public class Range extends Expression {

    /**
     * This method evaluates the Range expression based on the parent expression.
     * @param e the Parent Expression
     * @return the result of evaluating the Range expression
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Evaluate the Range with the rest of the expression
    }

    /**
     * This method evaluates the Range expression based on a list of parameters.
     * @param list a list of parameters representing start, end, and step values
     * @return a string representation of the generated list of values
     * @throws Exception if there's an evaluation error
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        String s = "";
        float startpoint = (float) list.get(0).evaluate(this); // Get the start value
        float end = (float) list.get(1).evaluate(this); // Get the end value
        float stepsize = (float) list.get(2).evaluate(this); // Get the step size

        int i = 0;
        while (startpoint < end) { // Loop through the range
            s += "(cons " + startpoint + " "; // Add current value to the result string
            i++;
            startpoint += stepsize; // Increment by step size
        }
        s += "'()"; // Add an empty list at the end
        while (i > 0) { // Close the cons pairs
            s += ")";
            i--;
        }
        return s; // Return the generated string representing the list
    }

    /**
     * This method returns a string representation of the Range expression.
     * @return a string representation of the Range expression
     */
    @Override
    public String toString() {
        return "Range "  + " (" + super.getId() + ")"; // Return a formatted string
    }
}
