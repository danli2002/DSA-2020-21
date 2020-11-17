/*
* Design and implement a client to calculate the following postfix expression: 
* 8   4   -3   *   1   5   +   /   *
* 
* Daniel Li
* Java 1.8.0
*
*/

public class Postfix_DL {
    private Stack_DL operandStack = new Stack_DL(500);

    // handy method to process the math operations
    public int operate(int op1, int op2, String operator) {
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
        }
        // return arbitrarily big number for invalid operation? not sure how this works
        // out but it's here
        return 99999999;
    }

    public int calculate(String postfix) {
        // string containing possible digits so that digits in a postfix expression can
        // be extracted
        String operands = "-1-2-3-4-5-6-7-8-90";
        // splits postfix string by any number of whitespaces
        String items[] = postfix.split("\\s+");
        for (String item : items) {
            if (operands.contains(item)) {
                // push an integer, not a string
                operandStack.push(Integer.parseInt(item));
                // the print statements featured in this code were initially for debug purposes,
                // but they are pretty useful to see how the postfix.
                System.out.println("Just pushed " + item);
            } else {
                String operator = item;
                // pop out recent two operands and do math with them
                int op1 = operandStack.pop();
                int op2 = operandStack.pop();
                System.out.println("Just popped " + op1 + " and " + op2);
                operandStack.push(operate(op2, op1, operator));
                System.out.println("Just pushed after operation:  " + operate(op1, op2, item));
            }
        }
        // return only item left, or the result
        return operandStack.peek();
    }

    public static void main(String[] args) {
        Postfix_DL postfix = new Postfix_DL();
        System.out.println("Result is " + postfix.calculate("8   4   -3   *   1   5   +   /   *"));
    }
}
