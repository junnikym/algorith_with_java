// https://programmers.co.kr/learn/courses/30/lessons/1835?language=java

/**
 * String[] 으로 풀었을 때 -> 시간 초과 
 * Stinrg 으로 풀었을 때 -> 
 */

class Solution {

	private final String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};

	Integer answer = 0;

	public int solution(int n, String[] data) {

		search(0, "", new boolean[friends.length], data);
        
        return answer;
    }

	private void search(
		final int n, 
		final String combination, 
		final boolean[] visited,
		final String[] data
	) {
		
		if( n == friends.length ) {
			if(vaild(data, combination))
				answer++;
				
			return;
		}

		for(int i = 0; i < friends.length; i++) {

			if(visited[i])
				continue;
			
			visited[i] = true;
			search(n+1, combination + friends[i], visited, data);
			visited[i] = false;
		}

	}

	private boolean vaild (final String[] data, final String combination) {

		for(String it : data) {

			final String[] condition = it.split("");

			int lhsPos = combination.indexOf(condition[0]);
			int rhsPos = combination.indexOf(condition[2]);

			final int gap = Math.abs(lhsPos - rhsPos) - 1;
			final int comp = Integer.valueOf(condition[4]).compareTo(gap);

			switch(condition[3]) {
				case "<":
					if( comp <= 0 ) return false;
					break;

				case ">":
					if( comp >= 0 ) return false;
					break;

				case "=":
					if( comp != 0 ) return false;
					break;
			}

		}
		
		return true;
	}

}

public class TakeAGroupPicture {

	public static void main(String[] args) {

		final Solution solution = new Solution();

		final int n = 2;
		final String[] data = { "N~F=0", "R~T>2" };
		final int answer = 3648;

		System.out.println(solution.solution(n, data));
 	}

}