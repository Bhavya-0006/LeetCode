class Solution {
    public boolean checkPerfectNumber(int num) {
        // int n = num ; 
        // int sum = 0;
        // for(int i=1; i<n-1; i++){
        //     if(n%i==0) sum += i;
        // }
        // return (sum == num);
        if (num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336){
            return true;
        }
        return false;
    }
}