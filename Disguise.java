// https://programmers.co.kr/learn/courses/30/lessons/42578?language=java

import java.util.HashMap;
import java.util.Map;

class Solution {

	public int solution(String[][] clothes) {

		int answer = 0;
		Map<String, Integer> map = new HashMap<>();

		for (var it : clothes) {
			Integer n = map.get(it[1]);
			if (n == null)
				n = Integer.valueOf(0);

			map.put(it[1], n + 1);
		}

		for (var it : map.entrySet()) {
			if (answer == 0)
				answer = it.getValue() + 1;
			else
				answer *= it.getValue() + 1;
		}

		return answer - 1;
	}

}

public class Disguise {

	public static void main (String [] args) {

		final String[][] input = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};

		Solution solution = new Solution();
		solution.solution(input);
		
	}
}
