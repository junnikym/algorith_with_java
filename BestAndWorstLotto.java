import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BestAndWorstLotto {

    public int[] solution(int[] lottos, int[] win_nums) {
        int zero    = 0;
        int correct = 0;
        Set restNums = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());

        for(int n : lottos) {
            if(n == 0)
                zero++;
            else if(restNums.contains(n))
                correct++;
        }

        final int best  = 7-correct-zero;
        final int worst = 7-correct;

        return new int[] {
                ( best>6 ? 6 : best ),
                (worst>6 ? 6 : worst)
        };
    }

}