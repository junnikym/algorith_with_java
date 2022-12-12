import java.util.HashMap;

public class CheckPersonalityType {

    public static void main (String[] args) {
        var test = new CheckPersonalityType();
        test.solution(new String[] {"AN", "CF", "MJ", "RT", "NA"}, new int[] {5, 3, 2, 7, 5});
    }

    public String solution(String[] survey, int[] choices) {
        HashMap<String, Integer> scores = new HashMap<>();

        String[][] types = new String[][]{{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};

        for(int i = 0; i < choices.length; i++) {
            final int s = choices[i] - 4;

            String target = "";
            if(s == 0)
                continue;
            else if(s < 0)
                target = survey[i].substring(0, 1);
            else
                target = survey[i].substring(1);

            scores.put(target, scores.getOrDefault(target, 0)+Math.abs(s));
        }

        String answer = "";
        for(int i = 0; i < types.length; i++){
            answer += combTypes(scores, types[i][0], types[i][1]);
        }

        return answer;
    }

    private String combTypes(HashMap<String, Integer> scores, String lhsType, String rhsType) {
        String result = "";

        final int lhs = scores.getOrDefault(lhsType, 0);
        final int rhs = scores.getOrDefault(rhsType, 0);

        if(lhs < rhs){
            result += rhsType;
        }
        else if(lhs > rhs){
            result += lhsType;
        }
        else{
            if(lhsType.compareTo(rhsType) < 0){
                result += lhsType;
            }
            else if(lhsType.compareTo(rhsType) > 0){
                result += rhsType;
            }
        }

        return result;
    }
}