// 오답 수정 :
// 입력 예시 [[0, 0], [0, 0]], [[1, 0, 0], [1, 0, 0], [1, 1, 1]] 의 경우
// 답이 false 이지만 true 로 오답이 나옴; 원인은 Key와 Lock이 각 0, 1 또는 1, 0 일 경우 만 판단하였기 때문
// 따라서 Lock의 열쇠구멍의 갯수와 열쇠 구멍에 들어맞은 경우를 카운트하여 비교

public class LockAndKey {
    public boolean solution(int[][] key, int[][] lock) {
        final int n = lock.length;
        final int m = key.length;

        int[][][] rotatedKey = new int[4][m][m];
        int nKeyhole = 0;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < m; c++) {
                rotatedKey[0][r]    [c]     = key[r][c];
                rotatedKey[1][c]    [m-r-1] = key[r][c];
                rotatedKey[2][m-r-1][m-c-1] = key[r][c];
                rotatedKey[3][m-c-1][r]     = key[r][c];
            }
        }

        for(int r = 0; r < n; r++)
            for(int c = 0; c < n; c++)
                if(lock[r][c] == 0) nKeyhole++;

        final int nBlock = m*m;

        // large row and col
        for(int lr = 0; lr < n+m-1; lr++) {
            for(int lc = 0; lc < n+m-1; lc++) {

                int[] block   = new int[] {0, 0, 0, 0};
                int[] keyhole = new int[] {0, 0, 0, 0};

                // small row and col
                for(int r = 0; r < m; r++) {
                    for(int c = 0; c < m; c++) {
                        final int sr = lr-m+1+r;
                        final int sc = lc-m+1+c;

                        if(sr < 0 || sr >= n || sc < 0 || sc >= n) {
                            for(int i = 0; i < 4; i++)
                                block[i]++;

                            continue;
                        }

                        for(int i = 0; i < 4; i++) {
                            if(rotatedKey[i][r][c]+lock[sr][sc] == 1) {
                                block[i]++;

                                if(lock[sr][sc] == 0)
                                    keyhole[i]++;
                            }
                        }
                    }
                }

                for(int i = 0; i < 4; i++)
                    if(block[i] == nBlock && keyhole[i] == nKeyhole)
                        return true;

            }
        }

        return false;
    }
}