public class NumberOfPrimeInKDigit {

    public static void main (String[] args) {
        NumberOfPrimeInKDigit test = new NumberOfPrimeInKDigit();
        System.out.println(test.solution(110011, 10));
    }

//class Solution {

    public int solution(int n, int k) {
        int answer = 0;

        final String s = Integer.toString(n, k);
        System.out.println(s);

        int start = 0, end = 0;
        for(start = 0; start < s.length(); start = end) {
            for(end = start + 1; end < s.length() && s.charAt(end) != '0'; end++);

            final long num = Long.parseLong(s.substring(start, end));
            if( !isPrime(num) )
                continue;

            answer++;
        }

        return answer;
    }

    private boolean isPrime(long n) {
        if(n <= 1)      return false;
        else if(n == 2) return true;

        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0)
                return false;

        return true;
    }
}