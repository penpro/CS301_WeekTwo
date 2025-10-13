//Usage "javac Banner" to compile, java Banner "string you want to scroll" integer(milliseconds delay between steps, lower is faster)


import java.awt.Font;

public class Banner {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Banner <string> <delay_ms>");
            return;
        }

        String s = args[0];
        int delayMs;
        try {
            delayMs = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Second argument must be an integer delay in ms");
            return;
        }
        if (s.length() == 0) {
            System.err.println("String must be nonempty");
            return;
        }

        // Canvas setup
        StdDraw.setCanvasSize(800, 200);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();

        // Use a monospaced font for a clean banner look
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 48));

        int n = s.length();
        int k = 0; // start offset
        while (true) {
            // Build the rotated string so it appears to move left-to-right.
            // Decrement k to shift the first character leftward in the source,
            // which visually moves the banner text to the right.
            String rotated = s.substring((k % n + n) % n) + s.substring(0, (k % n + n) % n);

            StdDraw.clear(StdDraw.BLACK);
            StdDraw.text(0.5, 0.5, rotated);
            StdDraw.show();

            StdDraw.pause(Math.max(0, delayMs));

            // Move one character per frame. Increase this step to move faster per frame.
            k--;
        }
    }
}
