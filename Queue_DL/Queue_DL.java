
/**
 * Using the QueueADT interface, implement the Queue ADT.
 * 
 * Daniel Li
 * Java 1.8.0
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue_DL implements Iterable{
    // start with set capacity
    private int capacity;
    // keep marker of the front and back elements of the queue
    private int front, back;
    private Object[] items;

    public Queue_DL(int cap) {
        capacity = cap;
        items = new Object[capacity];
        front = back = 0;
    }

    // determine if the queue is full 
    public boolean isFull() {
        return back == capacity;
    }

    // determine if the queue is empty    
    public boolean isEmpty(){
        return front == back;
    }

    // show the first element in the queue
    public Object first(){
        return items[front];
    }

    // add object to the queue
    public void enqueue(Object object) {
        // queue overflow, do not add
        if (isFull()) {
            System.out.println("Queue is full");
        
        } else {
            items[back] = object;
            // System.out.println("Just queued " + object);
            back++;
        }
        
    }

    public Object dequeue(){
        Object object = items[front];
        // shift stuff to left to simulate the queue "moving"
        for (int i = 0; i < back - 1; i++) {
            items[i] = items[i + 1];
        }
        back--;
        // System.out.println("Just dequeued " + object);
        // return the dequeued object
        return object;
    }

    // returns the number of objects currently in the queue
    public int size() {
        return back;
    }

    // creates a nice formatted string to display the queue and its elements
    public String toString() {
        String out = "";
        for (int i = 0; i < back; i++) {
            out += (items[i] + " ");
        }
        return out;
    }

    // overrides iterator
    @Override
    public Iterator iterator() {
        Iterator iter = new Iterator() {
            private int currIndex = front;

            @Override
            public boolean hasNext() {
                return currIndex < back && items[currIndex] != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items[front + 1];
            }
        };
        return iter;
    }

    // testing class by queuing and dequeuing
    public static void main(String[] args) {
        Queue_DL myQueue = new Queue_DL(500);
        myQueue.enqueue("Apple");
        System.out.println(myQueue);
        myQueue.enqueue("Orange");
        System.out.println(myQueue);
        myQueue.enqueue("Pear");
        System.out.println(myQueue);
        myQueue.enqueue("Grape");
        System.out.println(myQueue);
        System.out.println("Size of queue is " + myQueue.size());
        System.out.println(myQueue);
        myQueue.dequeue();
        System.out.println(myQueue);
        myQueue.enqueue("Pineapple");
        System.out.println(myQueue);
        
    }

}

/** output
 *  
    Just queued Apple
    Apple 
    Just queued Orange
    Apple Orange 
    Just queued Pear
    Apple Orange Pear 
    Just queued Grape
    Apple Orange Pear Grape 
    Size of queue is 4
    Apple Orange Pear Grape 
    Just dequeued Apple
    Orange Pear Grape 
    Just queued Pineapple
    Orange Pear Grape Pineapple
 */