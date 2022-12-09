import java.util.*;

public class MinimizeSalesDecline {

    int[] sales;
    Map<Integer, List<Integer>> childs = new HashMap<>();
    int[][] cost = new int[300000][2];

    public int solution(int[] sales, int[][] links) {
        this.sales = sales;

        for(int i = 0; i < sales.length; i++) {
            cost[i][0] = 0;
            cost[i][1] = sales[i];
        }

        for(int[] l : links) {
            List<Integer> nodes = childs.getOrDefault(l[0]-1, new ArrayList<>());
            nodes.add(l[1]-1);
            childs.put(l[0]-1, nodes);
        }

        propagation(0);

        return Math.min(cost[0][0], cost[0][1]);
    }

    private void propagation(int n) {
        cost[n][0] = 0;
        cost[n][1] = sales[n];

        if(!childs.containsKey(n))
            return;

        List<Integer> nodes = childs.get(n);
        nodes.forEach(this::propagation);
        int extra = nodes.stream().reduce(Integer.MAX_VALUE, (e, c)-> backPropagation(n, c, e));
        cost[n][0] += extra;
    }

    private int backPropagation(int p, int c, int minExtra) {
        int extra = cost[c][1] - cost[c][0];
        if(cost[c][0] < cost[c][1]) {
            cost[p][0] += cost[c][0];
            cost[p][1] += cost[c][0];
        }
        else {
            cost[p][0] += cost[c][1];
            cost[p][1] += cost[c][1];
            extra = 0;
        }

        return Math.min(minExtra, extra);
    }

}
