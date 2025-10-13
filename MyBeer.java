// Ohh ok, I get it now. Just seems like bad math to demonstrate array traversal
// Update: now does both experimental simulation and hard math comparison.

import java.util.concurrent.ThreadLocalRandom;

public class MyBeer {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: java MyBeer <NumberOfStudents> [trials]");
        }

        int NumberOfStudents = Integer.parseInt(args[0]);
        int trials = (args.length >= 2) ? Integer.parseInt(args[1]) : 1000;

        System.out.println("Han - Don't ever tell me the odds... but what are the odds?");
        double experimental = simulateAnyFixedPoint(NumberOfStudents, trials);
        double theoretical = probabilityAnyFixedPoint(NumberOfStudents);
        double limitApprox = 1.0 - 1.0 / Math.E; // ~0.6321 for large n

        System.out.printf("C2PO - By experimentation? Approximately: %.6f (trials=%d)%n", experimental, trials);
        System.out.printf("C2PO - By hard math?       Exactly up to n terms: %.6f%n", theoretical);
        System.out.printf("C2PO - Large-n limit       ~1 - 1/e = %.6f%n", limitApprox);
        System.out.printf("C2PO - Absolute diff (exp - math): %.6f%n", experimental - theoretical);
    }

    // Experimental estimate:
    // Shuffle labels 1000 times (or as requested) and check if any person kept their own beer.
    private static double simulateAnyFixedPoint(int n, int trials) {
        if (n <= 0) return 0.0;
        if (n == 1) return 1.0;

        int successes = 0;
        int[] beers = new int[n];

        for (int t = 0; t < trials; t++) {
            // initialize identity
            for (int i = 0; i < n; i++) beers[i] = i;

            // Fisher-Yates shuffle
            for (int i = n - 1; i > 0; i--) {
                int j = ThreadLocalRandom.current().nextInt(i + 1); // 0..i
                int tmp = beers[i];
                beers[i] = beers[j];
                beers[j] = tmp;
            }

            // array traversal method: check for any fixed point
            boolean anyMatch = false;
            for (int i = 0; i < n; i++) {
                if (beers[i] == i) { anyMatch = true; break; }
            }
            if (anyMatch) successes++;
        }

        return (double) successes / trials;
    }

    // Hard math probability:
    // P(any fixed point) = 1 - P(no fixed points).
    // P(no fixed points) for finite n is the partial alternating sum:
    //   sum_{k=0..n} (-1)^k / k!
    // This avoids huge factorials and stays numerically stable for typical n.
    private static double probabilityAnyFixedPoint(int n) {
        if (n <= 0) return 0.0;
        if (n == 1) return 1.0;

        double pNoFixed = 0.0;
        double invFact = 1.0; // 1/0!
        int sign = +1;

        for (int k = 0; k <= n; k++) {
            // add current term
            pNoFixed += sign * invFact;

            // prepare next term: 1/(k+1)! and flip sign
            sign = -sign;
            invFact /= (k + 1 == 0 ? 1 : (k + 1)); // safe since k starts at 0
        }

        return 1.0 - pNoFixed;
    }
}
