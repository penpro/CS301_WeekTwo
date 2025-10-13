//This reminds me of MUDs

public class Stats {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage:");
            System.err.println("  java Stats <n>              # reads n numbers from StdIn");
            System.err.println("  java Stats x1 x2 x3 ...     # numbers provided as args");
            return;
        }

        if (args.length == 1) {
            // Stdin mode: first arg is n, then read n numbers from StdIn
            int n = Integer.parseInt(args[0]);
            if (n <= 0) {
                System.err.println("n must be positive");
                return;
            }
            computeFromStdIn(n);
        } else {
            // Args-as-data mode: treat all args as the dataset
            computeFromArgs(args);
        }
    }

    private static void computeFromStdIn(int n) {
        int count = 0;
        double mean = 0.0;
        double m2 = 0.0;

        while (count < n) {
            double x = StdIn.readDouble();
            count++;

            double delta = x - mean;
            mean += delta / count;
            double delta2 = x - mean;
            m2 += delta * delta2;
        }

        double sampleStdDev = (count > 1) ? Math.sqrt(m2 / (count - 1)) : Double.NaN;
        System.out.printf("mean = %.6f%n", mean);
        System.out.printf("sample stddev = %.6f%n", sampleStdDev);
    }

    private static void computeFromArgs(String[] args) {
        int count = 0;
        double mean = 0.0;
        double m2 = 0.0;

        for (String s : args) {
            double x = Double.parseDouble(s);  // accepts ints or floats
            count++;

            double delta = x - mean;
            mean += delta / count;
            double delta2 = x - mean;
            m2 += delta * delta2;
        }

        if (count == 0) {
            System.err.println("No data provided");
            return;
        }

        double sampleStdDev = (count > 1) ? Math.sqrt(m2 / (count - 1)) : Double.NaN;
        System.out.printf("mean = %.6f%n", mean);
        System.out.printf("sample stddev = %.6f%n", sampleStdDev);
    }
}
