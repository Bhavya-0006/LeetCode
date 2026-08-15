class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int zeroCount = 0;

        for (int num : nums) {
            xor ^= num;
            if (num == 0) {
                zeroCount++;
            }
        }

        if (xor != 0) {
            return nums.length;
        }

        return zeroCount == nums.length ? 0 : nums.length - 1;
    }
}