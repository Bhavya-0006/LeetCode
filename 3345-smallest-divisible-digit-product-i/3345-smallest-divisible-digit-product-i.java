class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            if(digitProduct(n)%t == 0){
                return n;
            }
            n++;
        }
    }
    public int digitProduct(int n){
        int product = 1;
        while(n>0){
            product *= (n%10);
            n=n/10;
        }
        return product;
    }
}