/*
* Implement the ArrayStackOfIntegers ADT using arrays. Look at the API on the website.
*
* A stack is a collection that is based on the last-in-first-out (LIFO) policy. By tradition, 
* we name the stack insert method push() and the stack remove operation pop(). We also include a 
* method to test whether the stack is empty, as indicated in the following API.
*
* Daniel Li 
* Java 1.8.0
*
*/

public class Stack_DL {
    private Integer[] items;
    private int top;

    // Initializes memory to the stack
    public Stack_DL(int capacity) {
        items = new Integer[capacity];
        // 'top' indicates the current top index of the stack
        top = -1;
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return (top == -1);
    }

    // Checks if the stack is full
    public boolean isFull() {
        return (top == items.length - 1);
    }

    // 'Pushes' an item onto the stack from the top.
    public void push(Integer item) {
        if (isFull()) {
            System.out.println("Stack Overflow");
        } else {
            items[++top] = item;
        }
    }

    // 'Pops' an item from the top of the stack
    public Integer pop() {
        // can't pop when stack is empty
        if (top == -1) {
            System.out.println("Stack Underflow");
            return 0;
        }
        // Returning the popped item
        else {
            return items[top--];
        }
    }

    // overrides toString

    public String toString() {
        String string = "";
        for (int i = 0; i < top + 1; i++) {
            string += (items[i] + " ");
        }
        return string;
    }

    // Tester class, pushes 5 items and pops the last one
    public static void main(String[] args) {
        Stack_DL stack = new Stack_DL(10);
        System.out.println(stack.isEmpty());
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);

    }
}