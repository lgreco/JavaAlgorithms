import java.util.Arrays;

public class GreatMuseumRobbery {

    public static int[][] valueOfGreatMuseumRobbery(int[] v, int[] w, int cMax) {

        // Arrays v, w must have same length
        if (v.length != w.length)
            return null;

        // Initialize the scenario table S
        int n = v.length;
        int[][] S = new int[n+1][cMax+1];

        // S[0][...] is trivial = 0 ... nothing to take
        // S[...][0] is trivial = 0 ... no capacity to take
        // Let's explore non trivial cases
        for (int i = 1; i < 1 + n; i++) {
            for (int r = 1; r < 1 + cMax; r++) {
                // Can I take the i-th item? Does it fit?
                if (w[i - 1] > r) { // -1 for 0-indexing
                    S[i][r] = S[i - 1][r];
                } else {
                    // Is it more profitable to make room for the i-th item by removing some stuff
                    // from the bag, thus reducing the value, hoping that the i-th item will make up
                    // for the loss?
                    S[i][r] = Math.max(
                            S[i - 1][r],
                            v[i - 1] + S[i - 1][r - w[i - 1]]);
                }
            }
        }
        return S;
    } // method valueOfGreatMuseumRobbery

    public static void main(String[] args) {
        // Simple test: the problem discussed in classs
        //          0   1   2   3  4  5
        //          A   B   C   D  E   F
        int[] v = { 5, 11, 10, 20, 6, 15 };
        int[] w = { 1, 5, 4, 10, 1, 4 };
        int cMax = 12;

        int[][] S = valueOfGreatMuseumRobbery(v, w, cMax);

        for (int i = 0; i < S.length; i++)
            System.out.println(Arrays.toString(S[i]));
    }
}