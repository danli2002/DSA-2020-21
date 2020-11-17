/*
* Implement the ArrayStackOfIntegers ADT using arrays. Look at the API on the website.
*
* A stack is a collection that is based on the last-in-first-out (LIFO) policy. By tradition, 
* we name the stack insert method push() and the stack remove operation pop(). We also include a 
* method to test whether the stack is empty, as indicated in the following API.
*
* Daniel Li 
* Java 1.8.0
* 11/16/2020
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack_DL implements Iterable {
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

    public Integer peek(){
        return items[top];
    }

    // overrides toString so it can print out in a neat manner

    public String toString() {
        String string = "";
        for (int i = 0; i < top + 1; i++) {
            string += (items[i] + " ");
        }
        return string;
    }

    // overrides iterator() and its methods so it special functions are compatible
    // with it.

    @Override
    public Iterator iterator() {
        Iterator iter = new Iterator() {
            private int currIndex = 0;

            @Override
            public boolean hasNext() {
                return currIndex < items.length && items[currIndex] != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items[currIndex++];
            }
        };
        return iter;
    }

    // Tester class, pushes 5 items and pops the last one, prints results at each
    // step
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