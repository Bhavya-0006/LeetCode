class Solution {
    public boolean isPerfectSquare(int num) {
        long low = 1;
        long high = num;
        while(low <= high){
            long mid = low + (high-low)/2;
            long square = mid*mid;
            if(square == num)return true;
            if(square < num) low = mid+1;
            if(square > num)high = mid-1;
        }
        return false;
    }
}