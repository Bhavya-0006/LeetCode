class Solution {
    public int totalFruit(int[] fruits) {
        int maxlen = 0;
        int lastFruit = -1;
        int secondLastFruit = -1;
        int currCount = 0;
        int lastFruitStreak = 0;
        for (int f : fruits){
            if(f == lastFruit || f == secondLastFruit) currCount++;
            else {
                currCount = lastFruitStreak + 1;

            }
            if (f == lastFruit) lastFruitStreak++;
            else{
                lastFruitStreak = 1;
                secondLastFruit = lastFruit;
                lastFruit = f;
            }
            maxlen = Math.max(maxlen , currCount);
        }
        return maxlen;

    }
}