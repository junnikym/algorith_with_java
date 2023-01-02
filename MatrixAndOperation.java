import java.util.*;
import java.lang.Runnable;

class MatrixAndOperation {

    Deque<Integer> left=new ArrayDeque<>(), right=new ArrayDeque<>();
    LinkedList<Deque<Integer>> center;
    int r = 0, c = 0;

    final Map<String, Runnable> operationMap = Map.of(
            "Rotate"  , this::roatate,
            "ShiftRow", this::shiftRow
    );

    public int[][] solution(int[][] rc, String[] operations) {
        r = rc.length;
        c  = rc[0].length;

        center = new LinkedList();
        for(int i = 0;  i < r; i++) {
            Deque<Integer> line = new ArrayDeque();
            for(int j=1; j<c-1; j++)
                line.addLast(rc[i][j]);

            center.add(line);

            left.addLast(rc[i][0]);
            right.addLast(rc[i][c-1]);
        }

        for(String op : operations) {
            operationMap.get(op).run();
        }

        return toArray();
    }

    private void shiftRow () {
        final Deque<Integer> centerLast = center.pollLast();
        center.addFirst(centerLast);

        final Integer leftLast = left.pollLast();
        left.addFirst(leftLast);

        final Integer rightLast = right.pollLast();
        right.addFirst(rightLast);
    }

    private void roatate () {
        final Deque<Integer> top    = center.pollFirst();
        final Deque<Integer> bottom = center.pollLast();

        final Integer topLeft = left.pollFirst();
        top.addFirst(topLeft);

        final Integer topCenter = top.pollLast();
        right.addFirst(topCenter);

        final Integer bottomRight = right.pollLast();
        bottom.addLast(bottomRight);

        final Integer bottomCenter = bottom.pollFirst();
        left.addLast(bottomCenter);

        center.addFirst(top);
        center.addLast (bottom);
    }

    private int[][] toArray() {
        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            ans[i][0]   = left .pollFirst();
            ans[i][c-1] = right.pollFirst();
        }

        int i = 0;
        for (Deque<Integer> row : center) {
            for (int j = 1; j < c-1; j++) {
                ans[i][j] = row.pollFirst();
            }
            i++;
        }

        return ans;
    }
}