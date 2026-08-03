class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[] {firstoccurence(nums , target) , lastoccurence(nums,target)};
    }
    public int firstoccurence(int [] nums , int target){
        int left = 0;
        int right = nums.length -1;
        int ans = -1;
        while (left<= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                ans = mid ;
                right = mid - 1;

            }
            else if (nums[mid] < target){
                left = mid+1;
            }
            else if (nums[mid]> target){
                right = mid -1;
            }

        }
        return ans;
    }
    public int lastoccurence(int [] nums , int target){
        int left = 0;
        int right = nums.length-1;
        int ans = -1;
        while(left <= right){
            int mid = left +(right - left)/2;

            if(nums[mid] == target){
                ans = mid;
                left= mid+1;
            }

            else if (nums[mid] < target){
               left = mid +1;

            }
            else if (nums[mid] > target){
                right = mid -1;
            }

        }
        return ans;
    }
}