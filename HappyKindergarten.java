import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HappyKindergarten {

    public static void main (String[] args) throws IOException {

        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInput = br.readLine().split(" ");

        int nStudent = Integer.parseInt(firstLineInput[0]);
        int nGroup   = Integer.parseInt(firstLineInput[1]);

        PriorityQueue<Integer> diffOfHeights = new PriorityQueue<>(Collections.reverseOrder());
        Integer[] heights = Arrays
                .stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        for(int i = 1; i < heights.length; i++) {
            final int diff = heights[i] - heights[i-1];

            result += diff;
            diffOfHeights.add(diff);
        }

        for(int i = 0; i < nGroup-1; i++) {
            result -= diffOfHeights.poll();
        }

        System.out.println(result);
    }

}