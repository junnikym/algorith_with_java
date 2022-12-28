import java.util.LinkedList;
import java.util.Queue;

public class MakeTwoQueuesEqual {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int MAX_COUNT = 300000;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long q1Sum = 0;
        long q2Sum = 0;

        for(int i = 0;  i < queue1.length; i++) {
            q1.add(queue1[i]);
            q1Sum += queue1[i];
            q2.add(queue2[i]);
            q2Sum += queue2[i];
        }

        while (q1Sum != q2Sum) {
            answer++;
            if(answer > MAX_COUNT)
                return -1;

            if(q1Sum > q2Sum) {
                Integer val = q1.poll();
                q2.add(val);
                q1Sum -= val;
                q2Sum += val;
            }
            else {
                Integer val = q2.poll();
                q1.add(val);
                q2Sum -= val;
                q1Sum += val;
            }
        }

        return answer;
    }

}
