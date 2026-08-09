class Solution {
    private int[][] dp;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffix = new int[n];
        suffix[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dfs(0, 1);
    }

    private int dfs(int i, int M) {
        if (i >= n) return 0;

        // Can take all remaining piles
        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int ans = 0;

        for (int X = 1; X <= 2 * M; X++) {
            ans = Math.max(ans,
                    suffix[i] - dfs(i + X, Math.max(M, X)));
        }

        return dp[i][M] = ans;
    }
}