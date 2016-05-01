
/****************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java StdDraw.java In.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size N of the percolation system.
 *    - Creates an N-by-N grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row i, column j) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (1, 1) in the upper left-hand corner.
 *
 ****************************************************************************/
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

public class PercolationVisualizer {
	
	 // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;
    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 100;

    // draw N-by-N percolation system
    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-.05*N, 1.05*N);
        StdDraw.setYscale(-.05*N, 1.05*N);   // leave a border to write text
        StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (perc.isFull(row-1, col-1)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row-1, col-1)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25*N, -N*.025, opened + " open sites");
        if (perc.percolates()) StdDraw.text(.75*N, -N*.025, "percolates");
        else                   StdDraw.text(.75*N, -N*.025, "does not percolate");

    }

    public static void main(String[] args) throws IOException {
    	System.out.println("Type the txt file (for example: input3.txt)");
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String file  = br.readLine();
		
		
        In in = new In(file);      // input file
        int N = in.readInt();         // N-by-N percolation system
    	
        // turn on animation mode
        StdDraw.show(0);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);
        draw(perc, N);
        StdDraw.show(DELAY);
        int time=DELAY;
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i-1, j-1);
            draw(perc, N);
            StdDraw.show(DELAY);
            time+=DELAY;
        }
        System.out.println("Time needed for complete drawing is: " + time/1000+ " secs");
    }
}
