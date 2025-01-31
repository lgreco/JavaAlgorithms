import java.util.Arrays;

public class StringAlignment {

    /** Constants for match, mismatch, and gap penalties */
    private static final int MATCH = 0;
    private static final int MISMATCH = 3;
    private static final int GAP = 1;

    /**
     * Match/mismatch score for two characters.
     * 
     * @param x char first of two characters to compare
     * @param y char second of two characters to compare
     * @return int MATCH is x==y, MISMATCH otherwise
     */
    public static int mismatch(char x, char y) {
        return (x == y) ? MATCH : MISMATCH;
    } // method mismatch

    /**
     * Compute the Needleman-Wunch scores of two strings.
     * 
     * @param X String first of two strings to align
     * @param Y String second of two strings to align
     * @return int[][] with NW scores
     */
    public static int[][] computeNWScores(String X, String Y) {
        int m = 1 + X.length(); // +1 to accomodate empty strings
        int n = 1 + Y.length(); // in the P table
        int[][] P = new int[m][n];
        // Set P(i,0) values
        for (int i = 0; i < m; i++) {
            P[i][0] = i * GAP;
        }
        // Set P(0,j) values
        for (int j = 0; j < n; j++) {
            P[0][j] = j * GAP;
        }
        // Build the rest of the scores. Both loops start from 1 instead of 0. We adjust
        // for the proper position within the strings with a .charAt(... - 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                P[i][j] = Math.min(
                        P[i - 1][j - 1] + mismatch(X.charAt(i - 1), Y.charAt(j - 1)),
                        Math.min(
                                P[i][j - 1] + GAP,
                                P[i - 1][j] + GAP));
            }
        }
        return P;
    } // method computeNWScores

    public static void main(String[] args) {
        String Y = "BICYCLE";
        String X = "CYCLE";
        int[][] P = computeNWScores(X, Y);
        for (int i = 0; i < P.length; i++) {
            System.out.println(Arrays.toString(P[i]));
        }
    } // method main
} // class StringAlighment