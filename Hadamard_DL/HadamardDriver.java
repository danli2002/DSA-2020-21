/*
* Test client for Hadamard.java
* Creates a Hadamard plot with dimensions n, where n is a power of two.
*
* Daniel Li
* Java 1.8.0
*/

public class HadamardDriver {
    public static void main(String[] args) {
        Hadamard hadamard = new Hadamard(16);
        hadamard.showHadamard();
    }
}