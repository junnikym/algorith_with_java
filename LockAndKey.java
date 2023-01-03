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

                if(key[r][c] == 0)
                    nKeyhole++;
            }
        }

        // large row and col
        for(int lr = 0; lr < n+m-1; lr++) {
            for(int lc = 0; lc < n+m-1; lc++) {

                boolean[] success = new boolean[] {true, true, true, true};

                // small row and col
                for(int r = 0; r < m; r++) {
                    for(int c = 0; c < m; c++) {
                        final int sr = lr-m+1+r;
                        final int sc = lc-m+1+c;

                        if(sr < 0 || sr >= n || sc < 0 || sc >= n)
                            continue;

                        for(int i = 0; i < 4; i++) {
                            if(!success[i])
                                continue;

                            if(rotatedKey[i][r][c] == lock[sr][sc])
                                success[i] = false;
                        }

                    }
                }

                for(int i = 0; i < 4; i++)
                    if(!success[i])
                        return true;

            }
        }

        return false;
    }
}