class Solution {
    public double angleClock(int hour, int minutes) {
        hour %= 12;
        double minute_hand = minutes * 6;
        double hour_hand = hour* 30 + minutes * 0.5;
        double diff = Math.abs(hour_hand - minute_hand);
        return Math.min(diff , 360 - diff);

    }
}