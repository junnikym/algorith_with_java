
/**
 * https://programmers.co.kr/learn/courses/30/lessons/42895?language=java#
 */

class Solution {

	public int solution(int N, int number) {
		int answer = Integer.MAX_VALUE;
		answer = getMin(answer, dfs(N, number, 0, 0));

		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	private Integer dfs(
			final int N,
			final int number,
			final int count,
			final int current
	) {

		if (count > 8)
			return -1;

		if (current == number)
			return count;

		Integer min = Integer.MAX_VALUE;
		Integer rhs = 0;

		for (int i = 0; i < 8; i++) {
			rhs = rhs * 10 + N;
			min = getMin(min, dfs(N, number, count + i + 1, current + rhs));
			min = getMin(min, dfs(N, number, count + i + 1, current - rhs));
			min = getMin(min, dfs(N, number, count + i + 1, current * rhs));
			min = getMin(min, dfs(N, number, count + i + 1, current / rhs));
		}

		return min;
	}

	private Integer getMin(Integer lhs, Integer rhs) {
		if (rhs < 0)
			return lhs;
		return Math.min(lhs, rhs);
	}
}