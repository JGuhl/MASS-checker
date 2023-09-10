package eu.qped.racket.functions.lists;

import eu.qped.racket.buildingBlocks.Expression;

import java.util.List;
import java.util.Stack;

public class Reverse extends Expression {

    /**
     * This method evaluates the expression by calling the overloaded evaluate method
     * with the rest of the expression.
     *
     * @param e the Parent Expression
     * @return the result of evaluation
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(Expression e) throws Exception {
        return evaluate(e.getRest(super.getId()));
    }

    /**
     * This method evaluates a list of expressions and reverses them.
     *
     * @param list the list of expressions to be reversed
     * @return the reversed expression list as a string
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Object evaluate(List<Expression> list) throws Exception {
        String output = "";
        Stack<String> inputs = new Stack<>(); // A stack to store reversed elements
        String s = (String) list.get(0).evaluate(this); // Evaluate the first expression in the list
        boolean next;
        String temp = "";
        // Split the input string by spaces and process each segment
        for (String string : s.split(" ")) {
            if (string.compareTo("(cons") == 0)
                continue;
            System.out.println(string);
            next = true;
            temp = "";
            // Process each character of the segment
            for (Character c : string.toCharArray()) {
                if (c == '\'') {
                    next = false; // Stop adding characters after encountering a single quote
                }
                if (next)
                    temp += c; // Add characters to temp if 'next' is true
            }
            if (!temp.isEmpty())
                inputs.push(temp); // Push the processed segment onto the stack
        }
        int counter = 0;
        // Reconstruct the reversed list expression
        while (!inputs.empty()) {
            output += "(cons " + inputs.pop() + " ";
            counter++;
        }
        output += "'()";
        while (counter > 0) {
            output += ")";
            counter--;
        }
        return output; // Return the reversed list expression
    }

    /**
     * Returns a string representation of the Reverse object.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Reverse "  + " (" + super.getId() + ")";
    }
}
