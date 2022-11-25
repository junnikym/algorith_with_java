import java.util.*;

public class MultistageToothbrushSales {

    public static void main (String[] args) {
        MultistageToothbrushSales test = new MultistageToothbrushSales();
        test.solution(
                new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"young", "john", "tod", "emily", "mary"},
                new int[]    {12, 4, 2, 5, 10}
        );
    }

//class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        final Map<String, String>  parent = new HashMap();
        final Map<String, Integer> idxMap = new HashMap();

        for(int i=0; i<enroll.length; i++) {
            idxMap.put(enroll[i], i);
            parent.put(enroll[i], referral[i]);
        }

        for(int i=0; i<seller.length; i++) {
            String target = seller[i];

            int revenue = amount[i] * 100;
            int pRevenue = revenue / 10;

            do {
                final Integer idx = idxMap.get(target);
                answer[idx] += revenue - pRevenue;

                target   = parent.get(target);
                revenue  = pRevenue;
                pRevenue = revenue / 10;

                if(revenue < 1)
                    break;

            } while(!target.equals("-"));

        }

        return answer;
    }

}
