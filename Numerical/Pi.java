import java.util.Random;

public class Pi {

    static final int ATTEMPS = 10000000;
    static final int PERIODIC_REPORTS = 10;

    public static double piFromCircle(int attempts, int periodicReports) {
        int inside = 0;
        int reportEvery = attempts/periodicReports;
        Random rand = new Random();
        for (int attempt = 0; attempt < attempts; attempt++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            if ((x * x + y * y) <= 1) {
                inside++;
            }
            // Periodic reporting
            if (attempt%reportEvery==0) {
                System.out.printf("\nAfter %d attempts:\t%d of %d points within unit circle.\tπ=%.6f",
                attempt, inside, attempt, 4.0*inside/attempt);
            }
        }
        return 4.0* ((double) inside)/((double) attempts);
    }

    public static double piFromCircle() {
        return piFromCircle(ATTEMPS, PERIODIC_REPORTS);
    }

    public static void main(String[] args) {
        System.out.println(piFromCircle());
    }
}
