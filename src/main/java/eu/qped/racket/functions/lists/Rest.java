package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;

public class Rest extends Expression {

    /**
     * This class represents a Racket function called "rest" which operates on lists.
     * It returns the input list without its first element.
     * It extends the Expression class.
     */

    /**
     * Evaluates the "rest" function on a given parent Expression.
     * It retrieves the rest of the parent's list by calling evaluate with the rest of the parent's ID.
     *
     * @param e the parent Expression
     * @return the rest of the parent list
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId())); // Calling evaluate with the rest of the parent's ID
    }

    /**
     * Evaluates the "rest" function on a given list of Expressions.
     * It takes the first element of the list, removes its first character (which is assumed to be '('),
     * and then returns the modified string.
     *
     * @param list the list of Expressions
     * @return the modified string
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        String output = "";
        String s = (String) list.get(0).evaluate(this); // Assuming the first element is a string
        s = s.substring(1, s.length()); // Removing the '(' character
        Boolean rest = false;
        Boolean empty = false;
        for (Character c : s.toCharArray()) {
            if (rest) {
                output += c; // Adding characters to output after encountering '(' or '\''
            } else {
                if (c == '(' || c == '\'') {
                    rest = true; // Start adding characters to output after '(' or '\''
                    output += c;
                }
            }
        }
        return output.substring(0, output.length() - 1); // Removing the last character which is assumed to be ')'
    }

    /**
     * Returns a string representation of the "rest" function.
     *
     * @return a string describing the function
     */
    @Override
    public String toString() {
        return "Rest " + " (" + super.getId() + ")";
    }
}
