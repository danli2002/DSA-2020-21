/*
 *
 * Program that takes argument n and makes a n^2 x n^2 visualization Hadamard
 * transformation
 *
 *
 * Daniel Li 
 * Java 1.8.0
 *
 */

public class Hadamard {
    private final int n;

    public Hadamard(int size) {
        n = size;
        // initializes the StdDraw canvas
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        for (int i = 0; i < n; i++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(i, 0, i, n);
        }
        for (int j = 0; j < n; j++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(0, j, n, j);
        }
    }

    public void hadamardIt(int top_x, int top_y, int bot_x, int bot_y, boolean fill) {
        top_y = n - top_y;
        bot_y = n - bot_y;
        // base case, essentially checks if the single square it comes down to is of
        // length 1, then fills it black.
        if (top_x + 1 == bot_x) {
            if (fill) {
                StdDraw.setPenColor(StdDraw.BLACK);
                // StdDraw.filledSquare requires the midpoint of a square and the 'radius'; I
                // probably should have used StdDraw.filledPolygon
                if((Math.sqrt(n) % 2) == 0){
                    StdDraw.filledSquare((double) (top_x + bot_x) / 2, (double) (top_y + bot_y) / 2, 0.5);
                }
                else{
                    StdDraw.filledSquare((double) (top_x + bot_x) / 2, (double) ((n - top_y) + (n - bot_y)) / 2, 0.5);
                }
            }
        } else {
            // recursively calls the function to copy the base into four quadrants, with the
            // bottom right quadrant
            // denoted by negation of the other three.

            // each quadruant is filled based on a boolean marker that determines whether or
            // not to fill
            hadamardIt(top_x, top_y, (top_x + bot_x) / 2, ((top_y) + (bot_y)) / 2, fill);
            hadamardIt((top_x + bot_x) / 2, top_y, bot_x, ((top_y) + (bot_y)) / 2, fill);
            hadamardIt(top_x, ((top_y) + (bot_y)) / 2, (top_x + bot_x) / 2, bot_y, fill);
            hadamardIt((top_x + bot_x) / 2, ((top_y) + (bot_y)) / 2, bot_x, bot_y, !fill);
        }
    }

    // method to show the Hadamard plot
    public void showHadamard() {
        hadamardIt(0, 0, n, n, true);
        StdDraw.show();
    }
}
