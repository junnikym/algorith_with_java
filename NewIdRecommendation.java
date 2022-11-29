import java.util.Arrays;

public class NewIdRecommendation {

    public static void main (String[] args) {
        var test = new NewIdRecommendation();
        test.solution("=.=");
    }

    public String solution(String new_id) {

        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^\\w\\.\\-_]", "");
        new_id = new_id.replaceAll("\\.{2,}", ".");

        if(new_id.charAt(0) == '.')
            new_id = new_id.substring(1, new_id.length());

        if(new_id.length() == 0)
            new_id = "a";

        if(new_id.length() >= 16)
            new_id = new_id.substring(0, 15);

        if(new_id.charAt(new_id.length()-1) == '.')
            new_id = new_id.substring(0, new_id.length()-1);

        while (new_id.length() < 3) {
            new_id += String.valueOf( new_id.charAt(new_id.length()-1) );
        }

        return new_id;
    }

}
