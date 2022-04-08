
/*

( 첫번째 시도 )
x가 0~1 사이를 지날때 (0, 0) ~ (1, h/w) 사이를 지나게 된다.
이후 1~2 사이를 지날때는 높이가 h/w 부터 시작되기 때문에
이전에 통과한 높이를 내림한 수 ~ 이번에 통과하는 높이를 올림한 수 까지를 
최대 높이에서 빼서 하나씩 더하며 계산을 하였다.

문제점 -> https://programmers.co.kr/questions/8716
6번만 실패라면 double 타입형 특성상 적은 오차가 있을 수 있습니다. 
누적되면 오차 값이 출력에 영향을 줄 수 있습니다. 
input조건이 1억 이하이므로 100000000, 99999999 를 기준으로
x=99999998 일때 오차로 answer 감소값이 2여야 하지만 1이 되버립니다.

class Solution {
	public long solution(int w, int h) {
		long answer = 0;

		final double grad = (double) h / w;

		Double pre = Double.valueOf(0);
		for (int i = 1; i <= w; i++) {
			final Double now = grad * i;
			final long gap = (long) (Math.ceil(now) - Math.floor(pre));
			pre = now;

			answer += (h - gap);
		}

		return answer;
	}
}

*/

/**
 * 풀이 
 * 문제 예시의 그림을 보면 선이 지나가는부분은 작은 단위로 나누었을때 반복된다.
 * 이유는 크기가 (12, 8)일 경우 기울기가 3/2 일 것이고 선분이 지나가는 부분이
 * x좌표가 2의 배수 일 때 마다 높이값이 정수가 나올기 때문에 2마다 반복되는 것
 * 
 * 여기서 작은 부분은 빈 공간은 최대 공약수로 얻을 수 있다.
 * (참고) https://onlydev.tistory.com/85
 */

import java.math.*;

class Solution {
	public long solution(int w, int h) {
		int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();
		
		return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
	}
}

public class IntactSquare {
	
	public static void main(String[] args) {

		final Solution solution = new Solution();
		System.out.println(solution.solution(12, 8));	//80

	}
	
}
