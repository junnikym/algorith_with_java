import java.util.HashMap;
import java.util.Map;

public class ParkingFeeCalculation {
//class Solution {

    final String MAX_TIME = "23:59";

    public int[] solution(int[] fees, String[] records) {

        final Map<String, String> in = new HashMap<>();
        final Map<String, Integer> usage = new HashMap<>();

        for(var it : records) {
            final String[] input = it.split(" ");

            final String id = input[1];
            final String time = input[0];

            if(input[2].equals("IN")) {
                in.put(id, time);
                continue;
            }

            final int min = getElapsedTime(in.get(id), time);
            putAndUpdate(usage, id, min);

            in.remove(input[1]);
        }

        for(var id : in.keySet()) {
            final int min = getElapsedTime(in.get(id), MAX_TIME);
            putAndUpdate(usage, id, min);
        }



        return usage.keySet().stream()
                .sorted()
                .mapToInt(id-> getFee(fees, usage.get(id)))
                .toArray();
    }

    private int getFee(int[] feeTable, int minutes) {
        if(minutes < feeTable[0])
            return feeTable[1];

        final double availableMinutes = (minutes - feeTable[0])/(double)feeTable[2];
        return feeTable[1] + (int) Math.ceil(availableMinutes) * feeTable[3];
    }

    private int getElapsedTime(String in, String out) {
        String[] inHM = in.split(":");
        String[] outHM = out.split(":");

        int hour = Integer.parseInt(outHM[0]) - Integer.parseInt(inHM[0]);
        int min = Integer.parseInt(outHM[1]) - Integer.parseInt(inHM[1]);

        return hour*60+min;
    }

    private void putAndUpdate(Map<String, Integer> map, String id, int val) {
        if(!map.containsKey(id)) {
            map.put(id, val);
        }
        else {
            final int previous = map.get(id);
            map.put(id, val + previous);
        }
    }

}



