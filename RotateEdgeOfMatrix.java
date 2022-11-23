public class RotateEdgeOfMatrix {

    public static void main (String[] args) {
        RotateEdgeOfMatrix test = new RotateEdgeOfMatrix();
        test.solution(6, 6, new int[][] {{2,2,5,4},{3,3,6,6},{5,1,6,3}});
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer  = new int[queries.length];
        int[][] board = new int[rows][columns];

        int n = 1;
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < columns; y++) {
                board[x][y] = n;
                n++;
            }
        }

        for(int seq = 0; seq < queries.length; seq++) {
            final int[] q  = queries[seq];
            final int   x1 = q[0]-1, y1 = q[1]-1, x2 = q[2]-1, y2 = q[3]-1;

            int min = Integer.MAX_VALUE;
            int lastElem = board[x2][y1];
            int nextElem = 0;

            // left
            for(int i = x2;  i > x1; i--) {
                nextElem       = board[i-1][y1];
                board[i-1][y1] = lastElem;
                lastElem       = nextElem;

                min = Math.min(min, nextElem);
            }

            // top
            for(int i = y1;  i < y2; i++) {
                nextElem       = board[x1][i+1];
                board[x1][i+1] = lastElem;
                lastElem       = nextElem;

                min = Math.min(min, nextElem);
            }

            // right
            for(int i = x1;  i < x2 ; i++) {
                nextElem       = board[i+1][y2];
                board[i+1][y2] = lastElem;
                lastElem       = nextElem;

                min = Math.min(min, nextElem);
            }

            // bottom
            for(int i = y2;  i > y1; i--) {
                nextElem       = board[x2][i-1];
                board[x2][i-1] = lastElem;
                lastElem       = nextElem;

                min = Math.min(min, nextElem);
            }

            answer[seq] = min;
        }

        return answer;
    }

}
