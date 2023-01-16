import java.util.*;

class FailureRate {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        StageInfo[] stagesInfos = new StageInfo[N];

        for(int i = 0; i < N; i++)
            stagesInfos[i] = new StageInfo(i+1);

        for(int s : stages) {
            if(s > N)
                continue;

            stagesInfos[s-1].nPlayer++;
        }

        int restOfPlayer = stages.length;
        for(int i = 0; i < N; i++) {
            if(restOfPlayer == 0) {
                stagesInfos[i].failureRate = 0.0;
                continue;
            }
            stagesInfos[i].failureRate = (double)stagesInfos[i].nPlayer / restOfPlayer;
            restOfPlayer -= stagesInfos[i].nPlayer;
        }

        Arrays.sort(stagesInfos, (rhs, lhs)-> Double.compare(lhs.failureRate, rhs.failureRate));

        for(int i = 0; i < N; i++)
            answer[i] = stagesInfos[i].stage;


        return answer;
    }

    private static class StageInfo {

        StageInfo(int stage) {
            this.stage = stage;
        }

        int stage;
        int nPlayer = 0;
        double failureRate = 0;
    }
}