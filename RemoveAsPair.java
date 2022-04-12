import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/12973?language=java

class Solution {
    
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
         
        for(char c : s.toCharArray()) {
            
            if( !stack.isEmpty() && stack.peek() == c ) {
                stack.pop();
                continue;
            }
            
            stack.push(c);
        }
         
        return stack.isEmpty() ? 1 : 0;
    
    }
    
}