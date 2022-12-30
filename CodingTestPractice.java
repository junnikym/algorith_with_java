import java.util.Arrays;

// https://velog.io/@0_hun/프로그래머스-코딩-테스트-공부-2022-KAKAO-TECH-INTERNSHIP-Level-3-Python
public class CodingTestPractice {

    public static void main (String[] args) {
        final CodingTestPractice test = new CodingTestPractice();
        test.solution(0, 0, new int [][] {{3, 4, 10, 10, 20}, {0, 0, 1, 2, 1}});
    }

    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        int maxAlp = 0, maxCop = 0;
        int[][] dp = null;

        for(int i = 0; i < problems.length; i++){
            maxAlp = Math.max(problems[i][0], maxAlp);
            maxCop = Math.max(problems[i][1], maxCop);
        }

        if(alp >= maxAlp && cop >= maxCop)
            return 0;

        if(alp >= maxAlp)
            alp = maxAlp;
        if(cop >= maxCop)
            cop = maxCop;

        dp = new int[maxAlp+1][maxCop+1];
        for(int i = 0; i <= maxAlp; i++)
            for(int j = 0; j <= maxCop; j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[alp][cop] = 0;

        for(int i = alp;i <= maxAlp; i++){
            for(int j = cop; j <= maxCop; j++){

                if(i < maxAlp)
                    dp[i+1][j]=Math.min(dp[i+1][j], dp[i][j]+1);

                if(j < maxCop)
                    dp[i][j+1]=Math.min(dp[i][j+1], dp[i][j]+1);

                for(int[] p : problems) {
                    print(dp);
                    System.out.println("\n");
                    if(i >= p[0] && j >= p[1]) {
                        final int newAlp = Math.min(i+p[2], maxAlp);
                        final int newCop = Math.min(j+p[3], maxCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + p[4]);
                    }
                    print(dp);
                    System.out.println("\n");
                }
            }
        }

        return dp[maxAlp][maxCop];
    }

    private void print(int[][] arr) {
        for(int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}