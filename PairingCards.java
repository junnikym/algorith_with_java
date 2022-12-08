import java.util.*;

/**
 * https://www.youtube.com/watch?v=aZfzE4jIIMU
 */
public class PairingCards {

    public static void main (String[] args) {

        var test = new PairingCards();
        test.solution(new int[][] {
                {1,0,0,3},
                {2,0,0,0},
                {0,0,0,2},
                {3,0,1,0}
        }, 1, 0);

    }

    int[][] board;

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        return permutate(new Point(r, c, 0));
    }

    int permutate(Point src) {
        int ret = Integer.MAX_VALUE;

        for(int n = 1; n <=6; n++) {
            List<Point> card = new ArrayList<>();

            for(int r = 0; r < 4; r++) {
                for(int c = 0; c < 4; c++) {
                    if(board[r][c] == n)
                        card.add(new Point(r, c, 0));
                }
            }

            if(card.isEmpty())
                continue;

            int one = bfs(src, card.get(0)) + bfs(card.get(0), card.get(1)) + 2;
            int two = bfs(src, card.get(1)) + bfs(card.get(1), card.get(0)) + 2;

            for(int i = 0;  i < 2; i++) {
                board[card.get(i).row][card.get(i).col] = 0;
            }

            ret = Math.min(ret, one + permutate(card.get(1)));
            ret = Math.min(ret, two + permutate(card.get(0)));

            for(int i = 0;  i < 2; i++) {
                board[card.get(i).row][card.get(i).col] = n;
            }
        }

        if(ret == Integer.MAX_VALUE)
            return 0;

        return ret;
    }

    static final int[][] DIRECTION = {{-1, 0},{1, 0},{0, -1},{0, 1},};

    int bfs(Point src, Point dst) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()) {
            Point curr = q.poll();
            if(curr.row == dst.row && curr.col == dst.col)
                return curr.cnt;

            for(int i=0; i<4; i++) {
                int nr = curr.row + DIRECTION[i][0], nc = curr.col + DIRECTION[i][1];
                if(nr < 0 || nr > 3 || nc < 0 || nc > 3)
                    continue;

                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.cnt + 1));
                }

                for(int j = 0; j < 2; j++) {
                    if(board[nr][nc] != 0)
                        break;

                    if(nr + DIRECTION[i][0] < 0 || nr + DIRECTION[i][0] > 3
                        || nc + DIRECTION[i][1] < 0 || nc + DIRECTION[i][1] > 3)
                        break;

                    nr += DIRECTION[i][0];
                    nc += DIRECTION[i][1];
                }

                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.cnt + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Point {

        int row, col, cnt;

        Point(int r, int c, int t) {
            row = r;
            col = c;
            cnt = t;
        }
    }

}

/*
(오답)
(이전카드) -> (다음카드) -> (짝 카드) 의 경우의 수만 고려하였지만
Ctrl + 방향 시 중간에 막힘이 있을 수 있으며,
상, 하, 좌, 우로 이동 시 가장 빠르게 도달 할 수 있는 경우의 수도 고려해야 함.
 */
//public class PairingCards {
//
//    public static void main (String[] args) {
//
//        var test = new PairingCards();
//        test.solution(new int[][] {
//                {1,0,0,3},
//                {2,0,0,0},
//                {0,0,0,2},
//                {3,0,1,0}
//        }, 1, 0);
//
//    }
//
//    int min = Integer.MAX_VALUE;
//    int[][] board;
//    Map<Integer, int[]> cardPos = new HashMap<>();
//    Set<Integer> finishedCards = new HashSet<>();
//
//    public int solution(int[][] board, int r, int c) {
//        this.board = board;
//
//        IntStream.range(0,4).forEach(y->
//                IntStream.range(0, 4).forEach(x->
//                        putWhenItExists(y, x)));
//
//        dfs(r, c, 0);
//
//        return min;
//    }
//
//    private void putWhenItExists(int y, int x) {
//        if(board[y][x] != 0)
//            put(board[y][x], y, x);
//    }
//
//    private void put(int num, int y, int x) {
//        if( !cardPos.containsKey(num) ) {
//            cardPos.put(num, new int[]{ y, x, 0, 0 });
//            return;
//        }
//
//        int[] pos = cardPos.get(num);
//        pos[2] = y; pos[3] = x;
//    }
//
//    public void dfs(int y, int x, int count) {
//        if(finishedCards.size() == cardPos.size()) {
//            min = Math.min(min, count);
//            return;
//        }
//
//        IntStream.range(1, cardPos.size()).forEach(card-> {
//            if(finishedCards.contains(card))
//                return;
//
//            int nextCount = 0;
//            final int[] pos = cardPos.get(card);
//
//            finishedCards.add(card);
//
//            nextCount = plusLeftFirst(y, x, pos, count);
//            dfs(pos[2], pos[3], nextCount);
//
//            nextCount = plusRightFirst(y, x, pos, count);
//            dfs(pos[0], pos[1], nextCount);
//
//            finishedCards.remove(card);
//        });
//    }
//
//    private int plusLeftFirst(int lhsY, int lhsX, int[] pos, int count) {
//        count = plus(lhsY, lhsX, pos[0], pos[1], count);
//        return plus(pos[0], pos[1], pos[2], pos[3], count);
//    }
//
//    private int plusRightFirst(int lhsY, int lhsX, int[] pos, int count) {
//        count = plus(lhsY, lhsX, pos[2], pos[3], count);
//        return plus(pos[2], pos[3], pos[0], pos[1], count);
//    }
//
//    private int plus(int lhsY, int lhsX, int rhsY, int rhsX, int n) {
//        return Math.abs(lhsY - rhsY) + Math.abs(lhsX - rhsX) + n;
//    }
//
//}
