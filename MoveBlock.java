import java.util.*;

// https://www.youtube.com/watch?v=Lz8naSJIMu8
class MoveBlock {

    private static final int UP    =0;
    private static final int RIGHT =1;
    private static final int DOWN  =2;
    private static final int LEFT  =3;

    int[][] delta = {
            {-1,  0},
            { 1,  0},
            { 0, -1},
            { 0,  1},
    };

    int[][][] rotateDelta = {
            {
                    { 1,  1},
                    { 1, -1},
                    {-1, -1},
                    {-1,  1},
            },
            {
                    { 1, -1},
                    {-1, -1},
                    {-1,  1},
                    { 1,  1},
            }
    };

    int[][][] cornerDelta = {
            {
                    {-1,  1},
                    { 1,  1},
                    { 1, -1},
                    {-1, -1},
            },
            {
                    {-1, -1},
                    {-1,  1},
                    { 1,  1},
                    { 1, -1},
            }
    };

    int[][] board;
    int n;

    Queue<Cursor[]> queue = new LinkedList<>();
    boolean[][][] visited = new boolean[100][100][4];

    public int solution(int[][] board) {
        this.board = board;
        this.n = board.length;

        queue.add(new Cursor[] {
                new Cursor(0, 0, RIGHT, 0),
                new Cursor(0, 1, LEFT , 0)
        });

        visited[0][0][RIGHT] = true;
        visited[0][0][LEFT ] = true;

        Cursor[] currentCursor = new Cursor[2];
        Cursor[] newCursor     = new Cursor[2];

        while((currentCursor = queue.poll()) != null) {
            for(int j = 0; j < 4; j++) {
                for(int i = 0; i < 2; i++) {
                    newCursor[i] = new Cursor(
                            currentCursor[i].row + delta[j][0],
                            currentCursor[i].col + delta[j][1],
                            currentCursor[i].direction,
                            currentCursor[i].time + 1
                    );
                }

                if(isValid(newCursor) == false)
                    continue;

                for(int i = 0; i < 2; i++) {
                    if(newCursor[i].row == n-1 && newCursor[i].col == n-1)
                        return newCursor[i].time;

                    visited[newCursor[i].row][newCursor[i].col][newCursor[i].direction] = true;
                }

                queue.add(new Cursor[] {newCursor[0], newCursor[1]});
            }

            for(int rotate = 0; rotate < 2; rotate++) {
                for(int i = 0; i < 2; i++) {
                    int res = rotate(currentCursor, rotate, i);
                    if(res != 0)
                        return res;
                }
            }
        }

        return 0;
    }

    private boolean isValid(Cursor[] cursor) {
        for (int i = 0; i < 2; ++i) {

            if (cursor[i].row < 0   ||
                    cursor[i].row > n-1 ||
                    cursor[i].col < 0   ||
                    cursor[i].col > n-1 )
                return false;

            if (board[cursor[i].row][cursor[i].col] == 1)
                return false;

            if (visited[cursor[i].row][cursor[i].col][cursor[i].direction])
                return false;
        }

        return true;
    }

    private int rotate(Cursor[] currentCursor, int rotate, int idx) {
        Cursor[] newCursor = new Cursor[2];
        int a = idx, b = (idx+1) % 2;
        int d = currentCursor[a].direction;

        newCursor[0] = new Cursor(
                currentCursor[a].row,
                currentCursor[a].col,
                (currentCursor[a].direction + (rotate == 0 ? 1 : 3)) % 4,
                currentCursor[a].time+1
        );
        newCursor[1] = new Cursor(
                currentCursor[b].row + rotateDelta[rotate][d][0],
                currentCursor[b].col + rotateDelta[rotate][d][1],
                (currentCursor[b].direction + (rotate == 0 ? 1 : 3)) % 4,
                currentCursor[b].time+1
        );

        if(isValid(newCursor) == false)
            return 0;

        final int boardRow = currentCursor[a].row + cornerDelta[rotate][d][0];
        final int boardCol = currentCursor[a].col + cornerDelta[rotate][d][1];
        if(board[boardRow][boardCol] == 1)
            return 0;

        for(int i = 0; i < 2; i++) {
            if(newCursor[i].row == n-1 && newCursor[i].col == n-1)
                return newCursor[i].time;

            visited[newCursor[i].row][newCursor[i].col][newCursor[i].direction] = true;
        }

        queue.add(new Cursor[] {newCursor[0], newCursor[1]});

        return 0;
    }

    private static class Cursor {

        Cursor(int row, int col, int direction, int time) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.time = time;
        }

        int row, col, direction, time;
    }

}