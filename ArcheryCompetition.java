import java.util.Arrays;
import java.util.stream.IntStream;

public class ArcheryCompetition {
//class Solution {

    public static void main (String[] args) {
        ArcheryCompetition test = new ArcheryCompetition();
        int[] result = test.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});
    }

    /**
     * < 해결 >
     * ref: https://velog.io/@qodlstjd12/프로그래머스-Kakao-양궁-대회-Java
     *
     * < 시도 1 > 은 상대방 점수보다 낮은 경우는 구하지 않았다.
     * 하지만 < 시도 1 > 의 문제를 해결하기 위해 해당 조합도 필요하기 때문에
     * dfs 함수의 아래 for 문을 통해 해당 조합을 해주어야한다.
     * ```
     * for(int j = 0; j <= 10 && lion[j] <= info[j]; j++) {
     *     lion[j]++;
     *     dfs(info, cnt + 1, n);
     *     lion[j]--;
     * }
     * ```
     * * line[j] 와 info[j] 가 같다는 조건에서 for문이 작동 할 경우 lion[j] 는 info[j] 보다 1 큰 결과로 마무리된다.
     */

    static int[] res = { -1 };
    static int[] lion;
    static int max = -1000;
    public void dfs(int[] info, int cnt, int n) {

        if(cnt == n+1) {
            int apeach_point = 0;
            int lion_point = 0;
            for(int i = 0; i <= 10; i++)
            {
                if(info[i] != 0 || lion[i] != 0) {
                    if(info[i] < lion[i])
                        lion_point += 10 - i;
                    else
                        apeach_point += 10 - i;
                }
            }
            if(lion_point > apeach_point) {
                if(lion_point - apeach_point >= max)
                {
                    res = lion.clone();
                    max = lion_point - apeach_point;
                }
            }
            return ;
        }
        for(int j = 0; j <= 10 && lion[j] <= info[j]; j++) {
            lion[j]++;
            dfs(info, cnt + 1, n);
            lion[j]--;
        }
    }
    public int[] solution(int n, int[] info) {
        lion = new int[11];
        dfs(info,1,n);
        return res;
    }

    /**
     * < 오답 시도 1 >
     * 각 점수 당 상대방을 이길 수 있는 최소 화살의 갯수를 구해
     * 이를 기반으로 조합을 통해 답을 찾아감.
     *
     * 문제 :
     * 어떤 점수의 과녁에서 화살을 소비하고 상대에게 패배하였지만
     * 점수차가 가장 큰 경우가 발생할 수 있음.
     */

//    private int bastScore = 0;
//    private int[] needToArrow = null;
//
//    private boolean[] visited = new boolean[11];
//    private boolean[] bastVisited = null;
//
//    private int opponentBestScore = 0;
//
//    public int[] solution(int n, int[] info) {
//        this.needToArrow = IntStream.range(0, 11)
//                .map( idx -> {
//                    if(info[idx] != 0)
//                        opponentBestScore += (10-idx);
//
//                    return info[idx]+1;
//                })
//                .toArray();
//
//        search(info, n);
//        if(bastVisited == null)
//            return new int[] {-1};
//
//        return IntStream.range(0, 11)
//                .map( idx -> {
//                    if(bastVisited[idx])
//                      return needToArrow[idx];
//
//                    return 0;
//                })
//                .toArray();
//    }
//
//    private void search(int[] info, int restOfArrows) {
//
//        if(restOfArrows < 0)
//            return;
//
//        if(restOfArrows == 0) {
//
//            int score = 0;
//            int opponentScore = opponentBestScore;
//            for(int idx = 0;  idx < 11; idx++) {
//                if(!visited[idx])
//                    continue;
//
//                int s = (10-idx);
//                score += s;
//
//                if(info[idx] == 0)
//                    continue;
//
//                opponentScore -= s;
//            }
//
//            if(score < opponentScore)
//                return;
//
//            final int diff = Math.abs(score-opponentScore);
//
//            if(diff < bastScore)
//                return;
//
//            bastScore = diff;
//            bastVisited = visited.clone();
//            return;
//        }
//
//        IntStream.range(0, 11)
//                .forEach(idx -> {
//                    if(visited[idx])
//                        return;
//
//                    visited[idx] = true;
//                    search(info, restOfArrows-needToArrow[idx]);
//                    visited[idx] = false;
//                });
//    }

}