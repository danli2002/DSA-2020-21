/*
* Write a program that produces pseudo-random bits by simulating a linear-feedback shift register, 
* and then use it to implement a simple form of encryption for digital pictures.
*
* Daniel Li
* Java 1.8.0
*
*/

public class LFSR_DL {
    private int tapPosition;
    private int register[];

    // creates an LFSR with the specified seed and tap
    public LFSR_DL(String seed, int tap) {
        // Initializes the array to represent the bits within the register
        register = new int[seed.length()];
        for (int i = 0; i < seed.length(); i++) {
            // Converts ASCII char datavalues to numeric; I couldn't find a better way
            register[i] = Character.getNumericValue(seed.charAt(i));
        }
        tapPosition = tap;
    }

    // returns the number of bits in the LFSR.
    public int registerLength() {
        return register.length;
    }

    // returns bit i as 0 or 1.
    public int bitAt(int i) {
        return register[i];
    }

    // returns a string representation of this LFSR
    public String toString() {
        String registerString = "";
        for (int i = 0; i < registerLength(); i++) {
            registerString += bitAt(i);
        }
        return registerString;
    }

    // simulates one step of this LFSR and return the new bit as 0 or 1
    public int step() {
        // Operation to find 'exclusive or' between the displaced value and the 'tap'
        // value
        int xor = bitAt(0) ^ bitAt(registerLength() - tapPosition);
        // Shifts values to the left
        for (int i = 0; i < registerLength() - 1; i++) {
            register[i] = register[i + 1];
        }
        // Sets the last value to the xor value
        register[registerLength() - 1] = xor;
        return xor;
    }

    // simulates k steps of this LFSR and return the k bits as a k-bit integer

    public int generate(int k) {
        // Initailizes the k-bit number to be determined
        int number = 0;
        // performs k steps of the LSFR operation
        for (int i = 0; i < k; i++) {
            step();
        }
        // determines the k-bit number of length k
        for (int j = registerLength() - k; j < registerLength(); j++) {
            number += bitAt(j);
            // don't multiply everything by two after the last bit
            if (j == registerLength() - 1) {
                continue;
            } else {
                number *= 2;
            }
        }
        return number;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        LFSR_DL lfsr = new LFSR_DL("01101000010", 9);
        System.out.println("Simulating multiple steps; returning 'xor' bit alongside new LFSR:\n");
        System.out.println("Original LFSR:");
        System.out.println(lfsr);
        System.out.println("\nSteps:");
        for (int i = 0; i < 10; i++) {
            int bit = lfsr.step();
            System.out.println(lfsr + " " + bit);
        }
        LFSR_DL lfsr2 = new LFSR_DL("01101000010", 9);
        System.out.println("\nSimulating k-steps at a time; returning LFSR along with the k-bit integer created:\n");
        System.out.println("Original LFSR:");
        System.out.println(lfsr2);
        System.out.println("\nSteps:");
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            System.out.println(lfsr2 + " " + r);
        }
    }

    /* Output:
    
    Simulating multiple steps; returning 'xor' bit alongside new LFSR:

    Original LFSR:
    01101000010

    Steps:
    11010000101 1
    10100001011 1
    01000010110 0
    10000101100 0
    00001011001 1
    00010110010 0
    00101100100 0
    01011001001 1
    10110010010 0
    01100100100 0

    Simulating k-steps at a time; returning LFSR along with the k-bit integer created

    Original LFSR:
    01101000010

    Steps:
    00001011001 25
    01100100100 4
    10010011110 30
    01111011011 27
    01101110010 18
    11001011010 26
    01101011100 28
    01110011000 24
    01100010111 23
    01011111101 29

    */
}
