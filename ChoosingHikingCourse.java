import java.util.*;
import java.util.stream.Collectors;

// https://www.youtube.com/watch?v=La1DQoe3kco&t=312s
// https://github.com/skysign/WSAPT/blob/master/2022%20KAKAO%20TECH%20INTERNSHIP%20문제%204번%20등산%20코스%20정하기/src/Main.java

class ChoosingHikingCourse {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        Node answer = new Node(-1, -1, Integer.MAX_VALUE);

        Set<Integer> summitSet = Arrays.stream(summits).boxed().collect(Collectors.toSet());

        Map<Integer, List<Node>> connections = new HashMap();
        for(int[] p : paths) {
            final List<Node> entrance = connections.getOrDefault(p[0], new ArrayList());
            entrance.add(new Node(p[0], p[1], p[2]));
            connections.put(p[0], entrance);

            final List<Node> exit = connections.getOrDefault(p[1], new ArrayList());
            exit.add(new Node(p[1], p[0], p[2]));
            connections.put(p[1], exit);
        }

        int[] intensities = new int[n+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);

        PriorityQueue<Node> visitQueue = new PriorityQueue();
        Set<Integer> visitFlag = new HashSet();
        for(int g : gates) {
            visitQueue.addAll(connections.get(g));
            visitFlag.add(g);
            intensities[g] = 0;
        }

        while(!visitQueue.isEmpty()) {
            Node node = visitQueue.poll();

            if(visitFlag.contains(node.to))
                continue;

            final int distance = Math.max(node.weight, intensities[node.from]);
            if(distance < intensities[node.to])
                intensities[node.to] = distance;

            if(summitSet.contains(node.to)) {
                Node summitNode = new Node(0, node.to, distance);

                if (answer.compareTo(summitNode) > 0)
                    answer = summitNode;

                continue;
            }

            visitQueue.addAll(connections.get(node.to));
            visitFlag.add(node.to);
        }

        return new int[] {answer.to, answer.weight};
    }

    private static class Node implements Comparable<Node> {

        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from   = from;
            this.to     = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node rhs) {
            if (this.weight == rhs.weight)
                return Integer.compare(this.to, rhs.to);

            return Integer.compare(this.weight, rhs.weight);
        }

        @Override
        public String toString() {
            return "("+from+"->"+to+"):"+weight;
        }
    }
}