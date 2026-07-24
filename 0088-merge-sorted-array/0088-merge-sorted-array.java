class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int []nums3 = new int [m+n];
        for(int i=0; i<m; i++){
            nums3[i] = nums1[i];
        }
        for(int i=0; i<n; i++){
            nums3[m+i] = nums2[i];
        }
        Arrays.sort(nums3);
        for(int i=0; i<m+n; i++){
            nums1[i] = nums3[i];
        }
    }
}