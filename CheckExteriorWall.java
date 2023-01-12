import java.util.*;

public class CheckExteriorWall {

    public static void main (String[] args) {
        var test = new CheckExteriorWall();
        var result = test.solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4});
        System.out.println(result);
    }

    int min = Integer.MAX_VALUE;
    int n;
    int[] weak;
    int[] dist;

    public int solution(int n, int[] weak, int[] dist) {

        this.n = n;
        this.weak = weak;
        this.dist = dist;

        for(int i = 0; i < weak.length; i++)
            search(1, i, 0);

        if(min == Integer.MAX_VALUE)
            return -1;

        return min;
    }

    private void search(int cnt, int cur, int visited) {

        if (cnt > dist.length)
            return;

        if (cnt >= min)
            return;

        for(int i = 0; i < weak.length; i++) {
            final int next  = (cur+i) % weak.length;
            final int delta = weak[next] - weak[cur] + (next < cur ? n : 0);

            if(delta > dist[dist.length - cnt])
                break;

            visited |= 1 << next;
        }

        if(visited == (1 << weak.length) - 1) {
            min = Math.min(min, cnt);
            return;
        }

        for(int i = 0; i < weak.length; i++) {
            if((visited & (1 << i)) != 0)
                continue;

            search(cnt + 1, i, visited);
        }

    }

}


// [ 오답 ]
// 1. 반시계 방향은 굳이 구해줄 필요가 없다. 시계방향으로 도출했을때와 같은 값이 나올 수 밖에 없다.
// 2. 모든 조합을 다 만들지 않아도된다. 만약 [7, 5, 3] 을 사용 할 경우 [7, 3] 조합으로 갈 수 있는 지점은 [7, 5] 로도 갈 수 있다.

//public class CheckExteriorWall {
//
//    public static void main (String[] args) {
//        var test = new CheckExteriorWall();
//        var result = test.solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4});
//        System.out.println(result);
//    }
//
//    /*
//    출발지점 * (시계 방향, 반시계 방향)
//    오름차순 -> 큰 수 부터 탐색
//    */
//
//    int n;
//    int[] weak;
//    int[] dist;
//    boolean[] visited;
//    int nVisited;
//
//    int min = Integer.MAX_VALUE;
//
//    public int solution(int n, int[] weak, int[] dist) {
//        this.n = n;
//        this.weak = weak;
//        this.dist = dist;
//        this.visited = new boolean[weak.length];
//        this.nVisited = 0;
//
////        System.out.println(move(3, 0, false));
//
//        search(dist.length-1);
//
//        return min;
//    }
//
//    private void search(int who) {
//
//        if(who < 0 || nVisited > visited.length)
//            return;
//
//        if(nVisited == visited.length) {
//            min = Math.min(min, dist.length-(who+1));
//            System.out.println("finish :"+(who+1)+": " + Arrays.toString(visited));
//            return;
//        }
//
//        for(int i = 0; i < weak.length; i++) {
//            if(visited[i])
//                continue;
//
//            nextStep(who, move(who, i, true));
//            nextStep(who, move(who, i, false));
//        }
//
//    }
//
//    private void nextStep(int who, Set<Integer> checkPoint) {
//        nVisited += checkPoint.size();
//        search(who-1);
//        unvisitCheckPoint(checkPoint);
//        nVisited -= checkPoint.size();
//    }
//
//    private void unvisitCheckPoint(Set<Integer> checkPoint) {
//        for(int i : checkPoint) {
//            visited[i] = false;
//        }
//    }
//
//    // 거처간 거점을 List<> 형태로 출력
//    private Set<Integer> move(int who, int i, boolean isLeft) {
//        System.out.println("who : " + who + " - start - " + i + " / " + (isLeft?"left":"right"));
//        final int original = i;
//        final int length   = weak.length;
//
//        Set<Integer> checkPoint = new HashSet<>();
//        visited[i] = true;
//        checkPoint.add(i);
//
//        boolean crossed = false;
//        int loop  = 0;
//        do {
//            loop++;
//            if(isLeft) {
//                if(i-1 < 0) {
//                    crossed = true;
//                    i = length-1;
//                }
//                else {
//                    i--;
//                }
//            }
//            else {
//                if(i+1 >= length) {
//                    crossed = true;
//                    i = 0;
//                }
//                else {
//                    i++;
//                }
//            }
//
//            System.out.print("\ti : " + i);
//
//            if(visited[i]) {
//                System.out.println(" - visited : ");
//                continue;
//            }
//
//            int d = Math.abs(weak[i] - weak[original]) - (crossed ? n : 0);
//            if( d > dist[who] ) {
//                System.out.println(" - over : " + weak[i] + "-" + weak[original]);
//                break;
//            }
//
//            System.out.println(" - visit : ");
//            visited[i] = true;
//            checkPoint.add(i);
//        } while(weak[i] != original && loop < length);
//
//        System.out.println("\t\t\tend move : " + Arrays.toString(visited));
//
//        return checkPoint;
//    }
//}