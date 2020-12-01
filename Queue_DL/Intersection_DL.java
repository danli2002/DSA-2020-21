import java.util.Queue;

/**
 * Create a system to simulate vehicles at an intersection. Assume that there is
 * one lane going in each of four directions, with stoplights facing each
 * direction. Vary the arrival average of vehicles in each direction and the
 * frequency of the light changes to view the “behavior” of the intersection.
 * 
 * Daniel Li
 * 
 * Java 1.8.0
 * 
 * 11/30/2020
 */

public class Intersection_DL {
    // Creates two separate queues for the lights in each corresponding direction
    Queue_DL NSQueue = new Queue_DL(2);
    Queue_DL EWQueue = new Queue_DL(2);

    public Intersection_DL() {
        // Initial queues of the lights. The light in the North-South direction is Red
        // to start.
        NSQueue.enqueue("Red");
        NSQueue.enqueue("Green");
        EWQueue.enqueue("Green");
        EWQueue.enqueue("Red");
    }

    // Switch case to show the feedback for each situation
    public String simulateIntersection(String direction) {
        if (NSQueue.first() == "Red") {
            switch (direction) {
                case "N":
                    System.out.println("The light is currently " + NSQueue.first() + " in direction " + direction);
                    return "You can only turn right, westbound. Please yield.";
                case "E":
                    System.out.println("The light is currently " + EWQueue.first() + " in direction " + direction);
                    return "You can go westbound, northbound, or southbound.";
                case "S":
                    System.out.println("The light is currently " + NSQueue.first() + " in direction " + direction);
                    return "You can only turn right, eastbound. Please yield.";
                case "W":
                    System.out.println("The light is currently " + EWQueue.first() + " in direction " + direction);
                    return "You can go eastbound, southbound, or northbound.";
                default:
                    return "You entered an invalid direction";
            }
        } else {
            switch (direction) {
                case "N":
                    System.out.println("The light is currently " + NSQueue.first() + " in direction " + direction);
                    return "You can go southbound, eastbound, or westbound.";
                case "E":
                    System.out.println("The light is currently " + EWQueue.first() + " in direction " + direction);
                    return "You can only turn right, northbound. Please yield.";
                case "S":
                    System.out.println("The light is currently " + NSQueue.first() + " in direction " + direction);
                    return "You can go northbound, eastbound, or westbound.";
                case "W":
                    System.out.println("The light is currently " + EWQueue.first() + " in direction " + direction);
                    return "You can only turn right, southbound. Please yield.";
                default:
                    return "You entered an invalid direction";
            }
        }
    }

    // Cycles the lights from green -> red and vice versa
    public void cycleLight() {
        String prevLight = (String) NSQueue.dequeue();
        NSQueue.enqueue((prevLight));
        String prevLight1 = (String) EWQueue.dequeue();
        EWQueue.enqueue((prevLight1));
        System.out.println("Light just changed!");
    }

    // Tester class
    public static void main(String[] args) {
        Intersection_DL intersection = new Intersection_DL();
        System.out.println(intersection.simulateIntersection("N"));
        intersection.cycleLight();
        System.out.println(intersection.simulateIntersection("W"));
        intersection.cycleLight();
        System.out.println(intersection.simulateIntersection("W"));
        System.out.println(intersection.simulateIntersection("N"));
    }
}

/*
 * The light is currently Red in direction N 
 * You can only turn right, westbound. Please yield. 
 * Light just changed! 
 * The light is currently Red in direction W
 * You can only turn right, southbound. Please yield. 
 * Light just changed! 
 * The light is currently Green in direction W 
 * You can go eastbound, southbound, or northbound. 
 * The light is currently Red in direction N 
 * You can only turn right, westbound. Please yield.
 */