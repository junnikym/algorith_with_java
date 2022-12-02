import java.util.*;
import java.util.stream.IntStream;

public class SharedTexiFare {

    public static void main (String[] args) {
        var test = new SharedTexiFare();
        test.solution(6, 5, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
    }

    /*
    플로이드 와샬 문제
    https://www.youtube.com/watch?v=9574GHxCbKc ('플로이드 와샬'이란?)
     */

    int[][] fareTable;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.fareTable = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(fareTable[i], 20000001);
            fareTable[i][i] = 0;
        }

        for(int[] f : fares) {
            fareTable[f[0]-1][f[1]-1] = f[2];
            fareTable[f[1]-1][f[0]-1] = f[2];
        }

        floyd(n);

        --s; --a; --b;
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            answer = Math.min(answer, fareTable[s][i] + fareTable[i][a] + fareTable[i][b]);
        }

        System.out.println(answer);

        return answer;
    }

    private void floyd(int n) {
        for(int x = 0; x < n; x++)
            for(int y = 0; y < n; y++)
                for(int z = 0; z < n; z++) {
                    final int fare = fareTable[y][x] + fareTable[x][z];
                    if (fare < fareTable[y][z])
                        fareTable[y][z] = fare;
                }
    }


//    public int solution(int n, int s, int a, int b, int[][] fares) {
//        int[][] node = new int[n + 1][n + 1];
//        for(int i = 1; i < n + 1; i++) {
//            for(int j = 1; j < n + 1; j++) {
//                node[i][j] = 20000001; //200 * 100000 + 1
//            }
//            node[i][i] = 0;
//        }
//
//        for(int i = 0; i < fares.length; i++) {
//            node[fares[i][0]][fares[i][1]] = fares[i][2];
//            node[fares[i][1]][fares[i][0]] = fares[i][2];
//        }
//
//        for(int k = 1; k < n + 1; k++) {
//            for(int i = 1; i < n + 1; i++) {
//                for(int j = 1; j < n + 1; j++) {
//                    if(node[i][j] > node[i][k] + node[k][j]) {
//                        node[i][j] = node[i][k] + node[k][j];
//                    }
//                }
//            }
//        }
//
//        int min = Integer.MAX_VALUE;
//        for(int i = 1; i < n + 1; i++) {
//            min = Math.min(min, node[s][i] + node[i][a] + node[i][b]);
//        }
//        return min;
//    }
}
