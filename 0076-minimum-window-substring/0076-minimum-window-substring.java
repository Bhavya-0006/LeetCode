class Solution {
    public String minWindow(String s, String t) {
        int i=0, j=0, n=s.length();
        Map<Character , Integer> mp = new HashMap<>();
        for(char ch : t.toCharArray()){
            mp.put(ch , mp.getOrDefault(ch , 0)+1);

        }
        int cnt = mp.size();
        int min = Integer.MAX_VALUE;
        int start = -1;
        while (j<n){
            char ch = s.charAt(j);
            if(mp.containsKey(ch)){
                mp.put(ch , mp.get(ch)-1);
                if(mp.get(ch)==0)cnt--;
            }
            if(cnt > 0) j++;
            else if (cnt == 0){
                while (cnt  == 0){
                    if((j-i+1)<min){
                        min = j-i+1;
                        start = i;
                    }
                    char left = s.charAt(i);
                    if(mp.containsKey(left)){
                        mp.put(left , mp.get(left)+1);
                        if(mp.get(left)==1) cnt++;
                    }
                    i++;
                }
                j++;
            }
        }
        return start == -1 ? "" : s.substring(start , start + min);
    }
}