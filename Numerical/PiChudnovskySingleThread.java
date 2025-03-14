import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Computes π using the Chudnovsky algorithm.
 * 
 * The Chudnovsky formula is:
 * 
 * \begin{equation}
 * \frac{1}{\pi} = \frac{12}{C} \sum_{k=0}^{\infty} \frac{(-1)^k (6k)!
 * (545140134k + 13591409)}
 * {(3k)! (k!)^3 (640320)^{3k+3/2}}
 * \end{equation}
 * 
 */
public class PiChudnovskySingleThread {
    private static final int DIGITS = 1000; // Number of digits of π to compute
    private static final MathContext MC = new MathContext(DIGITS + 10, RoundingMode.HALF_EVEN);

    // Precompute C = 426880 * sqrt(10005), the constant that scales the series
    private static final BigDecimal C = new BigDecimal("426880").multiply(sqrt(new BigDecimal("10005"), DIGITS + 10));

    /**
     * Computes π using the Chudnovsky algorithm.
     * 
     * @param digits Number of digits of π to compute.
     * @return Computed value of π as a BigDecimal.
     */
    public static BigDecimal computePi(int digits) {
        BigDecimal sum = BigDecimal.ZERO;
        int terms = (digits / 14) + 1; // Each term contributes ~14 digits of accuracy

        for (int k = 0; k < terms; k++) {
            sum = sum.add(chudnovskyTerm(k));
        }

        // Since the sum gives 1/π, take the reciprocal:
        return C.divide(sum, MC);
    }

    /**
     * Computes the k-th term in the Chudnovsky series.
     * 
     * The term is given by:
     * 
     * \begin{equation}
     * T_k = \frac{(6k)! (545140134k + 13591409)}
     * {(3k)! (k!)^3 (-640320)^{3k}}
     * \end{equation}
     * 
     * @param k Term index.
     * @return Computed term value.
     */
    private static BigDecimal chudnovskyTerm(int k) {
        BigDecimal K = BigDecimal.valueOf(k);

        // Compute numerator: (6k)! * (545140134k + 13591409)
        BigDecimal numerator = factorial(6 * k)
                .multiply(new BigDecimal("13591409").add(new BigDecimal("545140134").multiply(K)));

        // Compute denominator: (3k)! * (k!)^3 * (-262537412640768000)^k
        BigDecimal denominator = factorial(3 * k)
                .multiply(factorial(k).pow(3))
                .multiply(BigDecimal.valueOf(-262537412640768000L).pow(k));

        return numerator.divide(denominator, MC);
    }

    /**
     * Computes the factorial of a number n (n!).
     * 
     * \begin{equation}
     * n! = 1 \times 2 \times 3 \times ... \times n
     * \end{equation}
     * 
     * @param n The number to compute the factorial of.
     * @return The factorial of n as a BigDecimal.
     */
    private static BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    /**
     * Computes the square root of a BigDecimal using Newton's Method.
     * 
     * \begin{equation}
     * x_{n+1} = \frac{1}{2} \left( x_n + \frac{S}{x_n} \right)
     * \end{equation}
     * 
     * @param value     The value to compute the square root of.
     * @param precision Number of decimal places of precision.
     * @return The square root of value as a BigDecimal.
     */
    private static BigDecimal sqrt(BigDecimal value, int precision) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue())); // Initial estimate
        BigDecimal two = BigDecimal.valueOf(2);
        MathContext mc = new MathContext(precision, RoundingMode.HALF_EVEN);

        // Iterate Newton's method for more precision
        for (int i = 0; i < precision; i++) {
            x = x.add(value.divide(x, mc)).divide(two, mc);
        }
        return x;
    }

    /**
     * Main function to compute and display π.
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        BigDecimal pi = computePi(DIGITS);
        long end = System.nanoTime();

        // Display only the first 100 digits
        System.out.println("Computed π: " + pi.toString().substring(0, DIGITS - 1) + "...");
        System.out.println("Time taken: " + (end - start) / 1e9 + " seconds");
    }
}