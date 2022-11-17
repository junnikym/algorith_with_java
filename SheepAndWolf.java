
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// <해답>
// ref :https://jangcenter.tistory.com/120

// 같은 방식의 시도를 한것으로 보이나, 로직에 오류가 있는 것 같음. (분석 필요)

class SheepAndWolf {
    // 해당 IDX의 자식은 누가 있는지
    static ArrayList<Integer>[] childs;
    static int[] Info;
    static int maxSheepCnt = 0;

    public static int solution(int[] info, int[][] edges) {
        Info = info;
        childs = new ArrayList[info.length];
        for (int[] l : edges) {
            int parent = l[0];
            int child = l[1];
            if (childs[parent] == null) {
                childs[parent] = new ArrayList<>();
            }
            childs[parent].add(child);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list);
        return maxSheepCnt;
    }

    private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
        // 늑대/양 수, 양의 최대값 최신화
        if (Info[idx] == 0) sheepCnt++;
        else wolfCnt++;

        if (wolfCnt >= sheepCnt) return;
        maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);

        // 다음 탐색 위치 갱신
        List<Integer> list = new ArrayList<>();
        list.addAll(nextPos);
        // 다음 탐색 목록중 현재 위치제외
        list.remove(Integer.valueOf(idx));
        if (childs[idx] != null) {
            for (int child : childs[idx]) {
                list.add(child);
            }
        }

        // 갈수 있는 모든 Node Dfs
        for (int next : list) {
            dfs(next, sheepCnt, wolfCnt, list);
        }
    }
}

// - 문제점 : 양이 최대 5마리가 나오는 케이스에서 결과값이 9가 나옴

//public class SheepAndWolf {
//
//    public static void main (String[] args) {
//        SheepAndWolf test = new SheepAndWolf();
//        int answer = test.solution(
//                new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
//                new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}
//        );
//
//        System.out.println("answer is " + answer);
//    }
//
//    Pointer[] pointers = null;
//    int[] info;
//
//    int max = 0;
//
//    public int solution(int[] info, int[][] edges) {
//        this.info = info;
//        pointers = new Pointer[info.length];
//
//        for(int[] e : edges) {
//            final int idx = e[0];
//            if(pointers[idx] == null)
//                pointers[idx] = new Pointer();
//
//            pointers[idx].visitable.add(e[1]);
//        }
//
//        dfs(0, pointers[0].visitable, 0, 0);
//
//        return max;
//    }
//
//    private void dfs(int idx, Set<Integer> visitable, int sheep, int wolf) {
//
//        System.out.println(visitable);
//
//        sheep += 1 - info[idx];
//        wolf  += info[idx];
//
//        if (wolf >= sheep)
//            return;
//
//        if(max < sheep)
//            max = sheep;
//
//        Set<Integer> clone = new HashSet<>(visitable);
//        clone.remove(idx);
//        if(pointers[idx] != null)
//            clone.addAll(pointers[idx].visitable);
//
//        for(Integer go: visitable) {
//            dfs(go, clone, sheep, wolf);
//        }
//
//    }
//
//    private class Pointer {
//        public Set<Integer> visitable = new HashSet<>();
//    }
//
//}