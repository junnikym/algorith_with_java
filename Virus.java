import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus {

    static Map<Integer, Set<Integer>> conn = new HashMap<>();
    static Set<Integer> infectedCom = new HashSet<>();

    public static void main (String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final Integer nCom = Integer.parseInt(br.readLine());
        final Integer nNet = Integer.parseInt(br.readLine());

        for(int i = 0; i < nNet; i++) {
            final int[] netConn = Arrays
                    .stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            put(netConn[0], netConn[1]);
            put(netConn[1], netConn[0]);
        }

        infection(1);
        System.out.println(infectedCom.size()-1);
    }

    public static void put(Integer from, Integer to) {
        Set<Integer> targetGroup = conn.get(from);
        if(targetGroup == null) {
            targetGroup = new HashSet<>();
            targetGroup.add(to);
            conn.put(from, targetGroup);
        }
        else {
            targetGroup.add(to);
        }
    }

    public static void infection(Integer comNum) {

        if(infectedCom.contains(comNum))
            return;

        infectedCom.add(comNum);
        conn.get(comNum).forEach(Virus::infection);
    }

}
