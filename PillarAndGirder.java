import java.util.*;

public class PillarAndGirder {

    static final int PILLAR = 0;
    static final int GIRDER = 1;

    static final int DEL = 0;
    static final int ADD = 1;

    int n;
    final boolean[][] pillarMap = new boolean[101][101];
    final boolean[][] girderMap = new boolean[101][101];

    int nAnswer = 0;

    public int[][] solution(int n, int[][] build_frame) {

        this.n = n;

        for(int[] step : build_frame) {
            if(!canUpdate(step))
                continue;

            update(step);
        }

        for(int i = n; i >= 0; i--) {
            for(int j = 0; j <= n; j++) {
                System.out.print((pillarMap[j][i] ? (girderMap[j][i] ? '├' : '│') : (girderMap[j][i] ? '─' : ' ')));
            }
            System.out.println();
        }

        List<int[]> answer = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if (pillarMap[i][j])
                    answer.add(new int[] {i, j, PILLAR});

                if (girderMap[i][j])
                    answer.add(new int[] {i, j, GIRDER});
            }
        }

        return answer.toArray(new int[0][3]);
    }

    private void update(int[] step) {

        final int x = step[0];
        final int y = step[1];
        final int a = step[2];
        final int b = step[3];

        if(b == DEL) {
            if(a == PILLAR)
                pillarMap[x][y] = false;
            if(a == GIRDER)
                girderMap[x][y] = false;
        }
        else {
            if(a == PILLAR)
                pillarMap[x][y] = true;
            if(a == GIRDER)
                girderMap[x][y] = true;
        }
    }

    private boolean canUpdate (int[] step) {

        final int x = step[0];
        final int y = step[1];
        final int a = step[2];
        final int b = step[3];

        // Del check
        if(b == DEL)
            return canDelete(x, y, a);

        // Add check
        if(a == PILLAR)
            return canAddPillar(x, y);

        if(a == GIRDER)
            return canAddGirder(x, y);

        return true;
    }

    private boolean canAddPillar(int x, int y) {
        if (y == 0)
            return true;

        if (y-1 >= 0 && pillarMap[x][y-1])
            return true;

        if (girderMap[x][y] || (x-1 >= 0 && girderMap[x-1][y]))
            return true;

        return false;
    }

    private boolean canAddGirder(int x, int y) {

        if ((y-1 >= 0) && (pillarMap[x][y-1] || pillarMap[x+1][y-1]))
            return true;

        if ((x-1 >= 0 && girderMap[x-1][y]) && girderMap[x+1][y])
            return true;

        return false;
    }

    private boolean canDelete(int x, int y, int a) {
        boolean[][] map = null;
        if(a == PILLAR)
            map = pillarMap;
        if(a == GIRDER)
            map = girderMap;

        final boolean original = map[x][y];
        map[x][y] = false;

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {

                if(pillarMap[i][j] && !canAddPillar(i, j)) {
                    map[x][y] = original;
                    return false;
                }

                if(girderMap[i][j] && !canAddGirder(i, j)) {
                    map[x][y] = original;
                    return false;
                }

            }
        }

        map[x][y] = original;
        return true;
    }

}
