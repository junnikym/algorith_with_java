import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://school.programmers.co.kr/questions/32399
 * 이기는 플레이어는 항상 최선(== 적은 이동)으로 이기고,
 * 지는 플레이어는 항상 최선(==많은 이동)으로 져야한다.
 *
 * 해당 코드(오답)에서는 플레이어 중 한명만 최선으로 이기고 지고 있음.  
 */
public class DisappearingFoothold {

    public static void main (String[] args) {
        var test = new DisappearingFoothold();
        var min = test.solution(
                new int[][] {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
//                new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
//                new int[][] {{1, 1, 1, 1, 1}},
                new int[] {1, 0},
                new int[] {1, 2}
        );

        System.out.println(min);
    }

//class Solution {

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        dfs(board, aloc, bloc, 0);
        System.out.println(min+"//"+max);
        return (min==Integer.MAX_VALUE) ? max : min;
    }

    public void dfs(int[][] board, int[] aloc, int[] bloc, int seq) {

        IntStream.range(0, seq).forEach(i-> System.out.print("  "));
        System.out.println("a : "+Arrays.toString(aloc)+" // b : "+Arrays.toString(bloc) + " // seq : "+seq);
        Arrays.stream(board).forEach(arr-> {
            IntStream.range(0, seq).forEach(i-> System.out.print("  "));
            System.out.println(Arrays.toString(arr));
        });

        if(board[aloc[0]][aloc[1]] == 0) {
            if (seq > max) max = seq;
            return;
        }
        if(board[bloc[0]][bloc[1]] == 0) {
            if (seq < min) min = seq;
            return;
        }

        final int[] loc = (seq%2 == 0) ? aloc : bloc;
        final int[] opposite = (seq%2 == 0) ? bloc : aloc;
        final List<int[]> canGo = canGo(board, loc, opposite);

        if(canGo.size() == 0) {
            if(seq%2 == 0) { // Play B Win
                if (seq > max) max = seq;
            }
            else {            // Play A Win
                if (seq < min) min = seq;
            }

            return;
        }

        canGo.forEach(pos -> {
            board[loc[0]][loc[1]] = 0;

            if(seq%2 == 0)
                dfs(board, pos, bloc, seq+1);
            else
                dfs(board, aloc, pos, seq+1);

            board[loc[0]][loc[1]] = 1;
        });

    }

    private List<int[]> canGo(int[][] board, int[] loc, int[] opposite) {
        final int x = loc[1], y = loc[0];
        final List<int[]> canGo = new ArrayList<>();
        int[] lastStep = null;

        if(x > 0 && board[y][x-1] == 1) {
            final int[] pos = new int[]{ y, x - 1 };
            if(!equals(pos, opposite)) canGo.add(pos);
            else lastStep = opposite;
        }
        if(x < board[0].length-1 && board[y][x+1] == 1) {
            final int[] pos = new int[]{ y, x + 1 };
            if(!equals(pos, opposite)) canGo.add(pos);
            else lastStep = opposite;
        }
        if(y > 0 && board[y-1][x] == 1) {
            final int[] pos = new int[]{ y - 1, x };
            if(!equals(pos, opposite)) canGo.add(pos);
            else lastStep = opposite;
        }
        if(y < board.length-1 && board[y+1][x] == 1) {
            final int[] pos = new int[]{ y + 1, x };
            if(!equals(pos, opposite)) canGo.add(pos);
            else lastStep = opposite;
        }

        if(canGo.size() == 0 && lastStep != null)
            canGo.add(lastStep);

        return canGo;
    }

    private boolean equals(int[] lhs, int[] rhs) {
        return lhs[0]==rhs[0] && lhs[1]==rhs[1];
    }

}