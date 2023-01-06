import java.util.*;

// https://girawhale.tistory.com/110
//
// 해당 문제는 Trie 또는 이분탐색을 활용하는 문제
//
public class SearchLyrics {
    class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] child = new Trie[26];

        void insert (String str) {
            Trie node = this;
            int len = str.length();
            lenMap.put(len, lenMap.getOrDefault(len, 0) + 1);

            for (char ch : str.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null)
                    node.child[idx] = new Trie();

                node = node.child[idx];
                node.lenMap.put(len, node.lenMap.getOrDefault(len, 0) + 1);
            }
        }

        int find (String str, int i) {
            if (str.charAt(i) == '?')
                return lenMap.getOrDefault(str.length(), 0);

            int idx = str.charAt(i) - 'a';
            return child[idx] == null ? 0 : child[idx].find(str, i + 1);
        }

    }

    public int[] solution (String[] words, String[] queries) {
        Trie front = new Trie();
        Trie back = new Trie();

        for (String word : words) {
            front.insert(word);
            back.insert(reverse(word));
        }

        return Arrays.stream(queries).mapToInt(
                query -> query.charAt(0) == '?' ?
                        back.find(reverse(query), 0) :
                        front.find(query, 0)).toArray();
    }

    String reverse (String s) {
        return new StringBuilder(s).reverse().toString();
    }

}



// 오답
// - 정확성 15/18
// - 효율성 1/4
//public class SearchLyrics {
//
//    Map<String, Set<String>> wordMap = new HashMap<>();
//
//    public int[] solution(String[] words, String[] queries) {
//        int[] answer = new int[queries.length];
//
//        for(String w : words) {
//            final char[] chs = w.toCharArray();
//            for(int i = 0; i < chs.length; i++) {
//                addWord(chs.length+"_"+i+"_"+chs[i], w);
//                addWord(chs.length+"_"+i+"_?"      , w);
//            }
//        }
//
//        for(int i = 0; i < queries.length; i++) {
//            final Set<String> resultSet = new HashSet<>();
//            final String q = queries[i];
//            final char[] chs = q.toCharArray();
//
//            final String firstKey = (chs[0] == '?') ? chs.length+"_0_?" : chs.length+"_0_"+chs[0];
//            if(!wordMap.containsKey(firstKey))
//                continue;
//
//            resultSet.addAll(wordMap.get(firstKey));
//
//            for(int j = 1; j < chs.length; j++) {
//                final String key = chs.length+"_"+j+"_"+chs[j];
//                if(!wordMap.containsKey(key))
//                    continue;
//
//                final Set<String> wordSet = wordMap.get(key);
//
//                resultSet.removeIf(w-> !wordSet.contains(w));
//            }
//
//            answer[i] = resultSet.size();
//
//        }
//
//        return answer;
//    }
//
//    private void addWord (String key, String word) {
//        final Set<String> wordSet = wordMap.getOrDefault(key, new HashSet<>());
//        wordSet.add(word);
//        wordMap.put(key, wordSet);
//    }
//}