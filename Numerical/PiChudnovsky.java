import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class PiChudnovsky {
    private static final BigDecimal C = new BigDecimal("426880").multiply(sqrt(new BigDecimal("10005"), 100050));
    private static final MathContext MC = new MathContext(100050, RoundingMode.HALF_EVEN);

    public static BigDecimal computePi(int digits) {
        BigDecimal sum = IntStream.range(0, (digits / 14) + 1)
            .parallel()
            .mapToObj(PiChudnovsky::chudnovskyTerm)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return C.divide(sum, MC);
    }

    private static BigDecimal chudnovskyTerm(int k) {
        BigDecimal K = BigDecimal.valueOf(k);
        BigDecimal numerator = factorial(6 * k)
                .multiply(new BigDecimal(13591409).add(new BigDecimal(545140134).multiply(K)));
        BigDecimal denominator = factorial(3 * k)
                .multiply(factorial(k).pow(3))
                .multiply(BigDecimal.valueOf(-262537412640768000L).pow(k));
        return numerator.divide(denominator, MC);
    }

    private static BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    private static BigDecimal sqrt(BigDecimal value, int precision) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        BigDecimal two = BigDecimal.valueOf(2);
        MathContext mc = new MathContext(precision, RoundingMode.HALF_EVEN);
        for (int i = 0; i < precision; i++) {
            x = x.add(value.divide(x, mc)).divide(two, mc);
        }
        return x;
    }

    public static void main(String[] args) {
        int digits = 100;
        long start = System.nanoTime();
        BigDecimal pi = computePi(digits);
        long end = System.nanoTime();
        System.out.println("Computed π: " + pi.toString().substring(0, 100) + "...");
        System.out.println("Time taken: " + (end - start) / 1e9 + " seconds");
    }
}
