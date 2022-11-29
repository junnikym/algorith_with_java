import java.util.*;

/**
 * (오답 - 시간초과)
 *
 * 해답 : https://www.youtube.com/watch?v=Jb34jY91450
 *
 *  - 모든 갯수의 조합을 알아낼 필요는 없음; course 에 담긴 갯수를 기준으로 dfs 를 돌면 시간절약이 될 수 있음.
 *  - visited 를 사용할 경우 문자열의 순서가 바뀜;
 *    따라서 처음에 정렬을 한 의미가 없어질 뿐더러 dfs를 돌면서 정렬을 해야하거나 dfs 에서 같은 조합이 나올 수 있다.
 *    해답 영상의 9:00 과 같이 조합을 할 경우 시간절약이 된다.
 *
 */

public class MenuRenewal {

    public static void main (String[] args) {
        var test = new MenuRenewal();
        test.solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4});
    }

    Map<String, Integer> orderMap = new HashMap();
    Set<String> orderSet;
    int[] max = new int[11];

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

         for(String it : orders) {
             char[] ch = it.toCharArray();
             Arrays.sort(ch);
             comb(ch, new boolean[ch.length], "");
             orderSet.forEach(k -> {
                 if(orderMap.containsKey(k))
                     orderMap.put(k, orderMap.get(k)+1);
                 else
                    orderMap.put(k, 1);
             });
         }

        for(int c : course) {
            for (String key : orderMap.keySet()) {
                if(key.length() != c)
                    continue;

                final int n = orderMap.get(key);
                if(max[c] < n)
                    max[c] = n;
            }
        }

         for(int c : course) {
             int i = max[c];
             for(String key : orderMap.keySet()) {
                 if(key.length() != c)
                     continue;

                 final int n = orderMap.get(key);
                 if(n < 2)
                     continue;

                 if(n == i)
                     answer.add(key);
             }
         }

         Collections.sort(answer);
         return answer.toArray(String[]::new);
    }

    private void comb(char[] ch, boolean[] visited, String last) {

        if(last.length() == 0) {
            orderSet = new HashSet<>();
        }

        if(last.length() == ch.length)
            return;

        for(int i = 0; i < ch.length; i++) {
            if(visited[i])
                continue;

            visited[i] = true;

            String str = last + ch[i];
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            orderSet.add(new String(strChars));

            comb(ch, visited, str);

            visited[i] = false;
        }
    }
}
}