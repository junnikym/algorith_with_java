import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42888?language=java
 */

class Solution {
    public String[] solution(String[] record) {
        List<String> result = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        
        for(var it : record) {
            final String[] splitedStr = it.split(" ");
            
            switch(splitedStr[0]) {
                case "Enter":
                case "Change":
                    map.put(splitedStr[1], splitedStr[2]);
                    break;
            }
        }
        
        for(var it : record) {
            final String[] splitedStr = it.split(" ");
            final String alias = map.get(splitedStr[1]);
            
            switch(splitedStr[0]) {
                case "Enter":
                    result.add(alias+"님이 들어왔습니다.");
                    break;
                case "Leave":
                    result.add(alias+"님이 나갔습니다.");
                    break;
            }
        }
        
        return result.toArray(String[]::new);
    }
}