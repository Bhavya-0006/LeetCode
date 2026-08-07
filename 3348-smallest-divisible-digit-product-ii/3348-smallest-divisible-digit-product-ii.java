class Solution {

    // factors[digit] = {number of 2s, 3s, 5s, 7s}
    static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Required prime factors of t
        int[] required = getPrimeFactors(t);

        // If t contains a prime other than 2,3,5,7
        if (required == null) {
            return "-1";
        }

        // Minimum number of digits required to make product divisible by t
        int[] minDigits = getDigitCounts(required);

        int minLength = countDigits(minDigits);

        // If even the minimum required digits are more than num.length,
        // answer must have more digits than num.
        if (minLength > num.length()) {
            return construct(minDigits);
        }

        // Count factors contributed by all digits of num
        int[] current = new int[4];

        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';

            // 0 is not allowed in the answer
            if (digit == 0) {
                continue;
            }

            add(current, FACTORS[digit]);
        }

        // If num itself is zero-free and already divisible by t
        if (num.indexOf('0') == -1 && contains(current, required)) {
            return num;
        }

        // Find first zero
        int firstZero = num.indexOf('0');

        if (firstZero == -1) {
            firstZero = num.length();
        }

        /*
         * Start from the right.
         *
         * We try changing one digit to a bigger digit.
         * Once we make a digit bigger, the suffix can be made
         * as small as possible.
         */
        for (int i = num.length() - 1; i >= 0; i--) {

            int digit = num.charAt(i) - '0';

            // Remove this digit's factors from current prefix
            if (digit != 0) {
                subtract(current, FACTORS[digit]);
            }

            // If there is a zero after this position,
            // keeping the prefix would contain that zero.
            if (i > firstZero) {
                continue;
            }

            int space = num.length() - 1 - i;

            // Try every bigger digit
            for (int bigger = digit + 1; bigger <= 9; bigger++) {

                // What factors are still required after:
                // prefix + bigger digit?
                int[] remaining = new int[4];

                for (int j = 0; j < 4; j++) {
                    remaining[j] = Math.max(
                        0,
                        required[j]
                        - current[j]
                        - FACTORS[bigger][j]
                    );
                }

                // Convert required prime factors into actual digits
                int[] neededDigits = getDigitCounts(remaining);

                int needed = countDigits(neededDigits);

                // We have 'space' positions left
                if (needed <= space) {

                    int ones = space - needed;

                    StringBuilder ans = new StringBuilder();

                    // Original prefix
                    ans.append(num, 0, i);

                    // Bigger digit
                    ans.append(bigger);

                    // Fill with 1's
                    for (int k = 0; k < ones; k++) {
                        ans.append('1');
                    }

                    // Required digits
                    ans.append(construct(neededDigits));

                    return ans.toString();
                }
            }
        }

        /*
         * No answer of the same length.
         *
         * Therefore answer needs num.length() + 1 digits.
         */
        int[] neededDigits = getDigitCounts(required);

        int totalNeeded = countDigits(neededDigits);

        StringBuilder ans = new StringBuilder();

        // Fill remaining positions with 1
        for (int i = 0; i < num.length() + 1 - totalNeeded; i++) {
            ans.append('1');
        }

        ans.append(construct(neededDigits));

        return ans.toString();
    }

    // Factorize t into 2,3,5,7
    private int[] getPrimeFactors(long t) {

        int[] count = new int[4];

        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {

            while (t % primes[i] == 0) {
                count[i]++;
                t /= primes[i];
            }
        }

        // Some other prime factor exists
        if (t != 1) {
            return null;
        }

        return count;
    }

    /*
     * Convert required prime factors into the minimum number
     * of digits.
     *
     * Example:
     *
     * 2^3 -> 8
     * 3^2 -> 9
     * 2^2 -> 4
     * 2*3 -> 6
     */
    private int[] getDigitCounts(int[] count) {

        int[] result = new int[10];

        int twos = count[0];
        int threes = count[1];

        // 2^3 = 8
        result[8] = twos / 3;
        twos %= 3;

        // 3^2 = 9
        result[9] = threes / 2;
        threes %= 2;

        // 2^2 = 4
        result[4] = twos / 2;
        twos %= 2;

        // 2 + 3 = 6
        if (twos == 1 && threes == 1) {
            result[6] = 1;
            twos = 0;
            threes = 0;
        }

        /*
         * 3 + 4 can be represented as 2 + 6
         *
         * Example:
         * 2^2 * 3
         * = 4 * 3
         * = 2 * 6
         */
        if (twos == 0 && threes == 1 && result[4] > 0) {
            result[4]--;
            result[2]++;
            result[6]++;
            threes = 0;
        }

        result[2] += twos;
        result[3] += threes;

        // 5 and 7 cannot combine with anything
        result[5] = count[2];
        result[7] = count[3];

        return result;
    }

    // Add factors
    private void add(int[] a, int[] b) {

        for (int i = 0; i < 4; i++) {
            a[i] += b[i];
        }
    }

    // Remove factors
    private void subtract(int[] a, int[] b) {

        for (int i = 0; i < 4; i++) {
            a[i] -= b[i];
        }
    }

    // Check whether current has all required factors
    private boolean contains(int[] current, int[] required) {

        for (int i = 0; i < 4; i++) {
            if (current[i] < required[i]) {
                return false;
            }
        }

        return true;
    }

    // Count how many actual digits are required
    private int countDigits(int[] digits) {

        int count = 0;

        for (int i = 2; i <= 9; i++) {
            count += digits[i];
        }

        return count;
    }

    // Construct digits in increasing order
    private String construct(int[] digits) {

        StringBuilder sb = new StringBuilder();

        for (int digit = 2; digit <= 9; digit++) {

            for (int j = 0; j < digits[digit]; j++) {
                sb.append(digit);
            }
        }

        return sb.toString();
    }
}