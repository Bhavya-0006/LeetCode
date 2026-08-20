class Solution {
    public int[] resultArray(int[] nums) {

        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        int i = 0, j = 0;

        // First two elements
        arr1[i++] = nums[0];
        arr2[j++] = nums[1];

        // Distribute remaining elements
        for (int k = 2; k < n; k++) {

            if (arr1[i - 1] > arr2[j - 1]) {
                arr1[i++] = nums[k];
            } else {
                arr2[j++] = nums[k];
            }
        }

        // Combine arr1 and arr2
        int[] ans = new int[n];
        int index = 0;

        for (int k = 0; k < i; k++) {
            ans[index++] = arr1[k];
        }

        for (int k = 0; k < j; k++) {
            ans[index++] = arr2[k];
        }

        return ans;
    }
}