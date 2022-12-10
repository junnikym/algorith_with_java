import java.util.Stack;

public class ConvertBracket {

    public static void main (String[] args) {
        var test = new ConvertBracket();
        test.solution(")(");
    }

    int lastPtr = 0;

    public String solution(String p) {

        if(p.isEmpty())
            return p;

        final boolean currect = isCorrect(p);
        final String u = p.substring(0, lastPtr);
        final String v = p.substring(lastPtr);

        if(currect)
            return u+solution(v);

        String str = "("+solution(v)+")";
        for(int i = 1; i < u.length()-1; i++)
            str += p.charAt(i) == '(' ? ")" : "(";

        return str;
    }

    private boolean isCorrect(String p) {
        boolean isBalanced = true;
        int left = 0, right = 0;

        Stack<Character> brackets = new Stack<>();

        int i = -1;
        do {
            i++;
            final char ch = p.charAt(i);

            if(ch == '(') {
                left++;
                brackets.add('(');
                continue;
            }

            right++;
            if(brackets.empty())
                isBalanced = false;
            else
                brackets.pop();

        } while(left != right);

        lastPtr = i+1;
        return isBalanced;
    }

}
