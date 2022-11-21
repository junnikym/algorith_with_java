import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Skill 을 하나씩 순회하며 Board 에 더하여 구할 경우
 * 시간복잡도는 Skill, Board[], Board[][]를 순회하는 O(k*n*m)가 된다.
 * 데이터의 갯수가 커지면 커짐에 따라 속도가 느려질 수 밖에 없다.
 *
 * 따라서 누적합 (Prefix Sum) 을 활용하여 시간을 단축하여야한다.
 * (누적합을 활용한 풀이 : https://kimjingo.tistory.com/155)
 */
public class undestroyedBuilding {

    public static void main (String[] args) {
        var test = new undestroyedBuilding();
        System.out.println(test.solution(
                new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}},
                new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}
        ));
    }

    public int solution(int[][] board, int[][] skill) {
        AtomicInteger answer = new AtomicInteger();

        int[][] prefixSum = new int[board.length+1][board[0].length+1];

        Arrays.stream(skill).forEach(s-> {
            final int sign = s[0] == 1 ? -1 : 1;
            final int degree = s[5];
            final int lhsY = s[1], lhsX = s[2];
            final int rhsY = s[3], rhsX = s[4];

            prefixSum[lhsY  ][lhsX  ] += degree*sign;    // Start Point
            prefixSum[rhsY+1][rhsX+1] += degree*sign;    // End Point
            prefixSum[lhsY  ][rhsX+1] -= degree*sign;    // X: Start, Y: End Point
            prefixSum[rhsY+1][lhsX  ] -= degree*sign;    // X: End, Y: Start Point
        });

        IntStream.range(0, prefixSum.length-1).forEach(y-> {
            IntStream.range(0, prefixSum[y].length-1).forEach(x-> {
                prefixSum[y][x+1] += prefixSum[y][x];
            });
        });

        IntStream.range(0, prefixSum.length-1).forEach(y-> {
            IntStream.range(0, prefixSum[y].length-1).forEach(x-> {
                prefixSum[y+1][x] += prefixSum[y][x];
            });
        });

        IntStream.range(0, prefixSum.length-1).forEach(y-> {
            IntStream.range(0, prefixSum[y].length-1).forEach(x-> {
                board[y][x] += prefixSum[y][x];
                if(board[y][x] > 0)
                    answer.getAndIncrement();
            });
        });

        return answer.get();
    }

}
