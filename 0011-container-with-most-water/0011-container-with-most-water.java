class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length -1;
        int max = 0;
        while (right > left){
            int width = right - left ;
            int area = width * Math.min(height[right], height[left]);
            max = Math.max(max , area);
            if(height[left]<height[right]) left++;
            else right--;

        }
        return max;

    }
}