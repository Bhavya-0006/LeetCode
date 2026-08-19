class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        // Bitmask reserved seats for each row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Rows without reservations can always fit 2 families
        int ans = (n - map.size()) * 2;

        // Masks for valid 4-seat blocks
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int midMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        for (int reserved : map.values()) {
            boolean left = (reserved & leftMask) == 0;
            boolean right = (reserved & rightMask) == 0;
            boolean mid = (reserved & midMask) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || right || mid) {
                ans += 1;
            }
        }

        return ans;
    }
}