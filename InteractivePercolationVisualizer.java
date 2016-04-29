import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/****************************************************************************
 *  Compilation:  javac InteractivePercolationVisualizer.java
 *  Execution:    java InteractivePercolationVisualizer N
 *  Dependencies: PercolationVisualizer.java Percolation.java
 *                StdDraw.java StdOut.java
 *
 *  This program takes the grid size N as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ****************************************************************************/

public class InteractivePercolationVisualizer {

    public static void main(String[] args) throws IOException {
       
    	
    	// N-by-N percolation system
    	System.out.println("Insert number of blocks. Blocks will be created N*N:");
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput  = br.readLine();
		
		
        int N = Integer.parseInt(userInput);          
        if (args.length == 1) N = Integer.parseInt(args[0]);

        // repeatedly open site specified my mouse click and draw resulting system
       // StdOut.println(N);

        //StdDraw.show(0);
        Percolation perc = new Percolation(N);
        PercolationVisualizer.draw(perc, N);
        StdDraw.show(0);

        while (true) {

            // detected mouse click
            if (StdDraw.mousePressed()) {

                // screen coordinates
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                // convert to row i, column j
                int i = (int) (N - Math.floor(y));
                int j = (int) (1 + Math.floor(x));

                // open site (i, j) provided it's in bounds
                if (i >= 1 && i <= N && j >= 1 && j <= N) {
                    if (!perc.isOpen(i-1, j-1)) { 
                        StdOut.println((i-1) + " " + (j-1));
                    }
                    perc.open(i-1, j-1);
                }

                // draw N-by-N percolation system
                StdDraw.show(0);
                PercolationVisualizer.draw(perc, N);
            }
            StdDraw.show(20);
        }
    }
}
