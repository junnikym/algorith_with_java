import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchRanking {

    public static void main (String[] args) {
        var test = new SearchRanking();
        test.solution(
                new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}
        );
    }

    /**
     * https://www.youtube.com/watch?v=vFwVvJQnC4M
     */
    public int[] solution(String[] info, String[] query) {

        int[] answer = new int[query.length];
        final Map<String, List<Integer>> conditionScores = new HashMap<>();

        Arrays.stream(info).forEach(elem-> {
            final String[] splitElem = elem.split(" ");
            final int score = Integer.parseInt(splitElem[4]);
            String[] language = {splitElem[0], "-"};
            String[] position = {splitElem[1], "-"};
            String[] career   = {splitElem[2], "-"};
            String[] soulFood = {splitElem[3], "-"};

            for(String l : language)
                for(String p : position)
                    for(String c : career)
                        for(String s : soulFood) {
                            final String[] keyArr = {l, p, c, s};
                            final String key = String.join("", keyArr);
                            final List<Integer> scoreList = conditionScores.getOrDefault(key, new ArrayList<>());
                            scoreList.add(score);
                            conditionScores.put(key, scoreList);
                        }
        });

        for(List<Integer> l : conditionScores.values())
            l.sort(null);

        for(int i = 0; i < query.length; i++) {
            final String[] splitQuery = query[i].split(" ");

            final int score = Integer.parseInt(splitQuery[7]);
            final String q = splitQuery[0]+splitQuery[2]+splitQuery[4]+splitQuery[6];

            if(!conditionScores.containsKey(q)) {
                answer[i] = 0;
                continue;
            }

            int n = 0;
            List<Integer> scoreList = conditionScores.get(q);

            int left = 0;
            int right = scoreList.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if(scoreList.get(mid) >= score)
                    right = mid;
                else
                    left = mid + 1;
            }

            answer[i] = scoreList.size() - left;
        }

        return answer;
    }

    /**
     * (오답)
     * - 효율성 테스트 4개 중 2개 오답
     * - Key 를 조합하는 것 보다 HashMap 의 Key 를 조합
     *   (
     *       info 배열 최대 크기는 50,000, query 배열 최대 크기는 100,000
     *       따라서 info 보다는 query 에서 시간을 단축 할 수 있도록 제작
     *   )
     * - 이분탐색을 통해 점수를 찾는 과정에서 시간을 줄임.
     */

//    public int[] solution(String[] info, String[] query) {
//
//        int[] answer = new int[query.length];
//
//        // Info (String[] -> HashMap)
//
//        final Map<String, List<Integer>> conditionScores = Arrays.stream(info)
//                .collect(Collectors.groupingBy(
//                        x-> {
//                            final int spliterIdx = x.lastIndexOf(" ");
//                            return x.substring(0, spliterIdx);
//                        },
//                        Collectors.mapping(
//                                x->{
//                                    final int spliterIdx = x.lastIndexOf(" ");
//                                    return Integer.parseInt(x.substring(spliterIdx+1));
//                                },
//                                Collectors.toList()
//                        )
//                ));
//
//        // Key 조합 및 조건 검색
//
//        for(int i = 0; i < query.length; i++) {
//            final String[] splitQuery = query[i].split(" ");
//            final String[] queries = new String[] {splitQuery[0], splitQuery[2], splitQuery[4], splitQuery[6]};
//            final int cutlineScore = Integer.parseInt(splitQuery[splitQuery.length-1]);
//
//            List<String> keys = new ArrayList<>();
//            combination(0, "", queries, keys);
//
//            int n = 0;
//            for(String k : keys) {
//                if(!conditionScores.containsKey(k))
//                    continue;
//
//                List<Integer> scores = conditionScores.get(k);
//                for(int s : scores) {
//                    if(s >= cutlineScore)
//                        n++;
//                }
//            }
//
//            answer[i] = n;
//        }
//
//        return answer;
//    }
//
//    private void combination(int i, String key, String[] query, List<String> result) {
//        if(i == 4) {
//            result.add(key.trim());
//            return;
//        }
//
//        if(!query[i].equals("-")) {
//            combination(i+1, key+" "+query[i], query, result);
//            return;
//        }
//
//        for(String it : queryOptions[i]) {
//            combination(i+1, key+" "+it, query, result);
//        }
//    }

}
