class Solution {
    public int[] validSequence(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();

        int n = s.length;
        int m = t.length;

        // dp[i] = maximum suffix length of word2 that can be matched
        // using word1 starting from i.
        int[] dp = new int[n + 1];
        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && s[i] == t[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];
        int i = 0;
        j = 0;

        // Greedily build the lexicographically smallest sequence.
        while (i < n && j < m) {
            if (s[i] == t[j]) {
                ans[j++] = i;
            } else {
                // Use the one allowed modification if the remaining suffix
                // can still be matched.
                if (dp[i + 1] >= m - 1 - j) {
                    ans[j++] = i;
                    i++;
                    break;
                }
            }
            i++;
        }

        if (j < m && i == n) {
            return new int[0];
        }

        while (i < n && j < m) {
            if (s[i] == t[j]) {
                ans[j++] = i;
            }
            i++;
        }

        return j == m ? ans : new int[0];
    }
}